package com.xuexiang.socialsharedemo.utils;

import android.app.Activity;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.xuexiang.socialsharedemo.R;
import com.xuexiang.xutil.tip.ToastUtils;

/**
 * @author XUE
 * @since 2019/4/15 14:57
 */
public final class Utils {

    public static String TEXT = "纯文本";
    public static String IMAGELOCAL = "纯图片本地";
    public static String IMAGEURL = "纯图片http";
    public static String TEXTANDIMAGE = "图文";
    public static String MULIMAGE = "多图分享";
    public static String MUSIC00 = "音乐（无标题，无内容）";
    public static String MUSIC11 = "音乐（有标题，有内容）";
    public static String MUSIC10 = "音乐（有标题，无内容）";
    public static String MUSIC01 = "音乐（无标题，有内容）";
    public static String VIDEO00 = "视频（无标题，无内容）";
    public static String VIDEO11 = "视频（有标题，有内容）";
    public static String VIDEO10 = "视频（有标题，无内容）";
    public static String VIDEO01 = "视频（无标题，有内容）";
    public static String WEB00 = "链接（无标题，无内容）";
    public static String WEB11 = "链接（有标题，有内容）";
    public static String WEB10 = "链接（有标题，无内容）";
    public static String WEB01 = "链接（无标题，有内容）";
    public static String EMOJI = "微信表情";
    public static String FILE = "文件";
    public static String MINAPP = "小程序（测试）";

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取分享弹窗
     *
     * @param activity
     * @param type
     * @return
     */
    public static ShareAction getShareAction(final Activity activity, final String type, final UMShareListener listener) {
        return new ShareAction(activity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            ToastUtils.toast("复制文本按钮", Toast.LENGTH_LONG);
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            ToastUtils.toast("复制链接按钮", Toast.LENGTH_LONG);
                        } else {
                            doShareAction(share_media, type, activity, listener);
                        }
                    }
                });
    }

    /**
     * 执行分享动作
     *
     * @param share_media
     * @param type
     * @param activity
     * @param listener
     */
    private static void doShareAction(SHARE_MEDIA share_media, String type, Activity activity, UMShareListener listener) {
        if (TEXT.equals(type)) {
            new ShareAction(activity).withText(Defaultcontent.text)
                    .setPlatform(share_media)
                    .setCallback(listener).share();
        } else if (IMAGELOCAL.equals(type)) {
            UMImage imageLocal = new UMImage(activity, R.drawable.logo);
            imageLocal.setThumb(new UMImage(activity, R.drawable.thumb));
            new ShareAction(activity).withMedia(imageLocal)
                    .setPlatform(share_media)
                    .setCallback(listener).share();
        } else if (IMAGEURL.equals(type)) {
            UMImage imageUrl = new UMImage(activity, Defaultcontent.imageurl);
            imageUrl.setThumb(new UMImage(activity, R.drawable.thumb));
            new ShareAction(activity).withMedia(imageUrl)
                    .setPlatform(share_media)
                    .setCallback(listener).share();
        } else if (WEB11.equals(type)) {
            UMWeb web = new UMWeb(Defaultcontent.url);
            web.setTitle("This is web title");
            web.setThumb(new UMImage(activity, R.drawable.thumb));
            web.setDescription("my description");
            new ShareAction(activity).withMedia(web)
                    .setPlatform(share_media)
                    .setCallback(listener).share();
        }
    }


}

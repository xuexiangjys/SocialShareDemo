package com.xuexiang.socialsharedemo.fragment;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xuexiang.socialsharedemo.utils.Utils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageSimpleListFragment;
import com.xuexiang.xutil.tip.ToastUtils;

import java.util.List;

import static com.xuexiang.socialsharedemo.utils.Utils.IMAGELOCAL;
import static com.xuexiang.socialsharedemo.utils.Utils.IMAGEURL;
import static com.xuexiang.socialsharedemo.utils.Utils.TEXT;
import static com.xuexiang.socialsharedemo.utils.Utils.WEB11;

/**
 * @author XUE
 * @since 2019/4/15 14:41
 */
@Page(name = "友盟社会化分享")
public class ShareFragment extends XPageSimpleListFragment implements UMShareListener {

    @Override
    protected List<String> initSimpleData(List<String> lists) {
        lists.add(TEXT);
        lists.add(IMAGELOCAL);
        lists.add(IMAGEURL);
        lists.add(WEB11);
        return lists;
    }

    @Override
    protected void onItemClick(int position) {
        Utils.getShareAction(getActivity(), getSimpleDataItem(position), this).open();
    }


    @Override
    public void onStart(SHARE_MEDIA share_media) {
        ToastUtils.toast(share_media.getName() + "分享中...");
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        ToastUtils.toast(share_media.getName() + "分享成功！");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        ToastUtils.toast(share_media.getName() + "分享失败:" + throwable.getMessage());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        ToastUtils.toast(share_media.getName() + "分享取消！");
    }
}

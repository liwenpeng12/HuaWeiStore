package com.yadong.huawei.ui.adapter.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.model.net.bean.AppCommentBean;
import com.yadong.huawei.ui.widget.recyclerview.wrapper.HeaderAndFooterWrapper;


/**
 *
 */

public class AppCommentTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext ;
    private AppCommentController appCommentController ;

    public AppCommentTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context ;
        appCommentController = new AppCommentController(mContext) ;
        addHeaderView(appCommentController.getContentView());
    }

    public void addDataAll(AppCommentBean appCommentBean) {
        if (appCommentController != null)
            appCommentController.setData(appCommentBean);
    }
}

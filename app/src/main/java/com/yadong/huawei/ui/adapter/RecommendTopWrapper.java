package com.yadong.huawei.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.ui.widget.banner.RecommendController;
import com.yadong.huawei.ui.widget.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 */

public class RecommendTopWrapper extends HeaderAndFooterWrapper {

    private Context mContext;
    private RecommendController mController;

    public RecommendTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.mContext = context;

        mController = new RecommendController(mContext);
        addHeaderView(mController.getContentView());
    }

    public void addDataAll(List<String> list) {
        if (mController != null) {
            mController.setData(list);
        }
    }
}

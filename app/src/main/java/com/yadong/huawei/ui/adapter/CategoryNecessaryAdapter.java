package com.yadong.huawei.ui.adapter;

import android.content.Context;

import com.yadong.huawei.R;
import com.yadong.huawei.model.net.bean.AppBean;
import com.yadong.huawei.ui.widget.recyclerview.adapter.CommonAdapter;
import com.yadong.huawei.ui.widget.recyclerview.base.ViewHolder;

/**
 *
 */

public class CategoryNecessaryAdapter extends CommonAdapter<AppBean> {

    public CategoryNecessaryAdapter(Context context) {
        super(context, R.layout.applistitem_recommend);
    }
    @Override
    protected void convert(ViewHolder holder, AppBean appBean, int position) {
        holder.setText(R.id.appTitle,appBean.getName());
        holder.setText(R.id.app_size,appBean.getSizeDesc());
        holder.setText(R.id.app_des,appBean.getMemo());
        holder.setImageUrl(R.id.appicon,appBean.getIcon());
    }
}
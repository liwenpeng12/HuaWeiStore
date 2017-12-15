package com.yadong.huawei.module.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yadong.huawei.R;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.model.remote.bean.TopBean;

import java.util.List;


/**
 *
 */

public class RankingSubAdapter extends BaseAdapter {

    private Context mContext;
    private List<TopBean.TopTopBean> topBeanList;

    public RankingSubAdapter(Context context, List<TopBean.TopTopBean> titleBeanList) {
        mContext = context;
        this.topBeanList = titleBeanList;
    }

    @Override
    public int getCount() {
        return topBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return topBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopBean.TopTopBean topBean = topBeanList.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.appdetail_subcat_title, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.appicon = (ImageView) convertView.findViewById(R.id.appicon);
        holder.ItemTitle = (TextView) convertView.findViewById(R.id.ItemTitle);

        holder.ItemTitle.setText(topBean.getName());
        Glide.with(App.getInstance()).load(topBean.getIconUrl()).into(holder.appicon);

        return convertView;
    }

    class ViewHolder {
        ImageView appicon;
        TextView ItemTitle;

    }
}
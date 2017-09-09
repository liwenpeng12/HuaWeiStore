package com.yadong.huawei.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.ui.base.BaseFragment;

import butterknife.BindView;

/**
 *
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }




}

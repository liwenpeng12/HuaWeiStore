package com.yadong.huawei.ui.fragment;

import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;

import butterknife.BindView;

/**
 *
 */

public class RecommendFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_name)
    TextView mTvName;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                SystemClock.sleep(2000);
                setCurrentState(LoadingPager.LoadResult.success);
                mTvName.setText("HELLO WORD");
            }
        }).start();
    }



}

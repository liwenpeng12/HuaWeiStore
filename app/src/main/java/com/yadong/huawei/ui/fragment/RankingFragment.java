package com.yadong.huawei.ui.fragment;

import android.os.SystemClock;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;

import butterknife.BindView;

/**
 *排行
 */

public class RankingFragment extends BaseFragment{

    @BindView(R.id.tv_name)
    TextView mTvName;


    @Override
    public int setLayout() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                SystemClock.sleep(2000);
                setCurrentState(LoadingPager.LoadResult.success);
                mTvName.setText("排行");
            }
        }).start();
    }
}

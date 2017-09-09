package com.yadong.huawei.ui.fragment;

import android.os.SystemClock;

import com.yadong.huawei.R;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;

/**
 *我的
 */

public class MineFragment extends BaseFragment{
    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
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
                setCurrentState(LoadingPager.LoadResult.empty);

            }
        }).start();
    }
}

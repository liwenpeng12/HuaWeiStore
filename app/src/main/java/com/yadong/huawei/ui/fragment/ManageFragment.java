package com.yadong.huawei.ui.fragment;

import android.os.SystemClock;

import com.yadong.huawei.R;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;

/**
 *管理
 */

public class ManageFragment extends BaseFragment {


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_manage;
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
                setCurrentState(LoadingPager.LoadResult.error);

            }
        }).start();
    }

}

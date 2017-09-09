package com.yadong.huawei.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yadong.huawei.common.app.App;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.ui.widget.LoadingPager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类fragment
 */

public abstract class BaseFragment extends Fragment {


    private App mApplication;
    private LoadingPager mLoadingPager;
    private Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLoadingPager = new LoadingPager(getContext()) {
            @Override
            public View createSuccessView() {
                return inflater.inflate(setLayout(), container, false);
            }

            @Override
            public void loadData() {
                BaseFragment.this.loadData();
            }
        };

        mUnBinder = ButterKnife.bind(this, mLoadingPager);
        return mLoadingPager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApplication = (App) getActivity().getApplication();
        AppComponent appComponent = mApplication.getAppComponent();
        initInjector(appComponent);
        initViews();
        show();
    }

    /**
     * 设置布局
     */
    public abstract int setLayout();

    /**
     * 用dagger进行注入
     */
    public abstract void initInjector(AppComponent appComponent);

    /**
     * 初始化视图控件
     */
    public abstract void initViews();




    public void show() {
        if (mLoadingPager != null) {
            mLoadingPager.show();
        }
    }

    public void setCurrentState(LoadingPager.LoadResult result) {
        if (mLoadingPager != null) {
            mLoadingPager.setCurrentState(result);
        }
    }


    public abstract void loadData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除butterKnife
        if (mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
    }
}

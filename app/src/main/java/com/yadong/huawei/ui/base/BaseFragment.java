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

/**
 * 基类fragment
 */

public abstract class BaseFragment extends Fragment {


    private App mApplication;
    private LoadingPager mLoadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mLoadingPager = new LoadingPager(getContext()) {
            @Override
            protected View createLoadedView() {
                View view = BaseFragment.this.createSuccessView();
                return view;
            }

            @Override
            protected void load() {
                BaseFragment.this.load();
            }
        };

        return mLoadingPager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mApplication = (App) getActivity().getApplication();
        AppComponent appComponent = mApplication.getAppComponent();
        initInjector(appComponent);
        initViews();
        updateViews();
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


    /**
     * 更新视图控件
     */
    public abstract void updateViews();



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


    public abstract View createSuccessView();

    public abstract void load();
}

package com.yadong.huawei.ui.base;

import android.content.Context;
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

/**
 * 基类Fragment
 */
public abstract class BaseFragment extends Fragment {


    protected Context mContext;
    protected LoadingPager mLoadingPager; //缓存Fragment view
    protected boolean mIsLoad = false;//是否加载数据

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLoadingPager == null) {
            mLoadingPager = new LoadingPager(getContext()) {
                @Override
                public View createSuccessView() {
                    return inflater.inflate(attachLayoutRes(), null);
                }

                @Override
                public void loadData() {
                    BaseFragment.this.updateViews();
                }
            };
            ButterKnife.bind(this, mLoadingPager);
            initInjector();
            initViews();
        }
        ViewGroup parent = (ViewGroup) mLoadingPager.getParent();
        if (parent != null) {
            parent.removeView(mLoadingPager);
        }
        return mLoadingPager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mLoadingPager != null && !mIsLoad) {
            mIsLoad = true;
            show();
        }
    }

    /**
     * 为什么要在setUserVisibleHint方法和onActivityCreated两处都调用获取数据进行界面更新的方法updateViews呢？
     * 原因:第一次进入界面，第一个Fragment获取数据是在onActivityCreated里面，剩余的Fragment获取数据是在setUserVisibleHint里面。
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mLoadingPager != null && !mIsLoad) {
            mIsLoad = true;
            System.out.println("setUserVisibleHint  1次");
            show();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * 绑定布局文件
     */
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews();


    /**
     * 获取 ApplicationComponent
     */
    protected AppComponent getAppComponent() {
        return App.getAppComponent();
    }

    /**
     * 进行展示,默认是展示的Loading转圈圈,然后同时去调用loadData方法去加载数据(就是上面创建loadingPager重写的那个loadData方法)
     */
    public void show() {
        if (mLoadingPager != null) {
            mLoadingPager.show();
        }
    }

    /**
     * 设置当前的状态(用于加载完页面的数据,是成功还是失败)
     */
    protected void setCurrentState(LoadingPager.LoadResult result) {
        if (mLoadingPager != null) {
            mLoadingPager.setCurrentState(result);
        }
    }

}

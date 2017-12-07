package com.yadong.huawei.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;
import com.yadong.huawei.ui.widget.LoadingPager;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 基类Fragment
 */
public abstract class BaseFragment<T extends BasePresenter> extends RxFragment
        implements BaseView {

    protected BaseActivity mContext;
    protected LoadingPager mLoadingPager; //缓存Fragment view

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) getActivity();
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
        return mLoadingPager;
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
    public void setCurrentState(LoadingPager.LoadResult result) {
        if (mLoadingPager != null) {
            mLoadingPager.setCurrentState(result);
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}

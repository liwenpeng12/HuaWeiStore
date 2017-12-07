package com.yadong.huawei.ui.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yadong.huawei.R;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.common.manager.AppActivityManager;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.presenter.base.IBaseView;

import butterknife.ButterKnife;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {


    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏

        setContentView(setLayout());

        initImmersionBar();
        initButterKnife();
        addActivityToStack();
        initInjector();
        initViews();
        updateViews();
    }

    /**
     * 设置布局
     */
    public abstract int setLayout();

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarDarkFont(true,0.1f)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.white)
                .init();
    }

    /**
     * 添加Activity进栈和初始化ButterKnife绑定控件
     */
    private void initButterKnife() {
        ButterKnife.bind(this);
    }

    /**
     * 添加进栈
     */
    private void addActivityToStack() {
        AppActivityManager.getInstance().addActivity(this);
    }

    /**
     * 用dagger进行注入
     */
    public abstract void initInjector();

    /**
     * 初始化
     */
    public abstract void initViews();

    /**
     * 更新视图控件
     */
    public abstract void updateViews();


    /**
     * 获取 ApplicationComponent
     */
    protected AppComponent getAppComponent() {
        return App.getAppComponent();
    }

    /**
     * 绑定生命周期
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    /**
     * 带打开动画的开启
     *
     * @param isHaveAnim 是否有动画
     */
    public void startActivity(Intent intent, boolean isHaveAnim) {
        startActivity(intent);
        if (isHaveAnim) {
            enterAnim();
        }
    }


    @Override
    protected void onDestroy() {
        AppActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    public void enterAnim(){
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    public void exitAnim(){
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

}

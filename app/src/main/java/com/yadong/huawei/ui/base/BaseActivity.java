package com.yadong.huawei.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.common.manager.AppActivityManager;
import com.yadong.huawei.dagger.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基类Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        App application = (App) getApplication();

        initImmersionBar();
        initButterKnife();
        addActivityToStack();
        initInjector(application.getAppComponent());
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
        mImmersionBar.init();
    }

    /**
     * 添加Activity进栈和初始化ButterKnife绑定控件
     */
    private void initButterKnife() {
        mUnBinder = ButterKnife.bind(this);
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
    public abstract void initInjector(AppComponent appComponent);

    /**
     * 初始化
     */
    public abstract void initViews();

    /**
     * 更新视图控件
     */
    public abstract void updateViews();


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    protected void openActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onDestroy() {
        AppActivityManager.getInstance().removeActivity(this);
        super.onDestroy();
        //解除butterKnife
        if (mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
        //必须调用该方法，防止内存泄漏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

}

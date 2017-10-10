package com.yadong.huawei.ui.activity;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.common.utils.Constants;
import com.yadong.huawei.common.utils.MyToast;
import com.yadong.huawei.dagger.component.DaggerAppDetailComponent;
import com.yadong.huawei.dagger.module.AppDetailModule;
import com.yadong.huawei.model.net.bean.AppDetailBean;
import com.yadong.huawei.presenter.activity.AppDetailPresenter;
import com.yadong.huawei.presenter.contract.AppDetailContract;
import com.yadong.huawei.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * App详情页面
 */
public class AppDetailActivity extends BaseActivity implements AppDetailContract.View {

    @Inject
    AppDetailPresenter mPresenter;

    private String mPackageName;//传递过来的包名参数

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;


    }

    @Override
    public void initInjector() {
        DaggerAppDetailComponent
                .builder()
                .appComponent(getAppComponent())
                .appDetailModule(new AppDetailModule(this))
                .build()
                .inject(this);
    }

    /**
     * 重写初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.tab_background)
                .init();
    }

    @Override
    public void initViews() {
        getIntentData();
    }

    private void getIntentData() {
        mPackageName = getIntent().getStringExtra(Constants.PACKAGE_NAME);

    }

    @Override
    public void updateViews() {
        mPresenter.getData(mPackageName);
    }

    @Override
    public void showLoading() {
        GlobalDialogManager.getInstance().show(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        GlobalDialogManager.getInstance().dismiss();
    }

    @Override
    public void getDataSuccess(AppDetailBean bean) {

    }

    @Override
    public void getDataFail(String message) {
        MyToast.show(this, message);
    }
}

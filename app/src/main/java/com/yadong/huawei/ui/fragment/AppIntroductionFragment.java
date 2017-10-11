package com.yadong.huawei.ui.fragment;

import com.yadong.huawei.R;
import com.yadong.huawei.dagger.component.DaggerAppIntroductionComponent;
import com.yadong.huawei.dagger.module.AppIntroductionModule;
import com.yadong.huawei.model.net.bean.AppIntroductionBean;
import com.yadong.huawei.presenter.contract.AppIntroductionContract;
import com.yadong.huawei.presenter.fragment.AppIntroductionPresenter;
import com.yadong.huawei.ui.base.BaseFragmentPro;

/**
 *
 */

public class AppIntroductionFragment extends BaseFragmentPro<AppIntroductionPresenter>
        implements AppIntroductionContract.View {


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_introduction;
    }

    @Override
    protected void initInjector() {
        DaggerAppIntroductionComponent
                .builder()
                .appComponent(getAppComponent())
                .appIntroductionModule(new AppIntroductionModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }


    @Override
    public void getDataSuccess(AppIntroductionBean bean) {

    }

    @Override
    public void getDataFail(String message) {

    }
}

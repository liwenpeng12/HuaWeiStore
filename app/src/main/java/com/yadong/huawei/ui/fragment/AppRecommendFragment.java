package com.yadong.huawei.ui.fragment;

import com.yadong.huawei.R;
import com.yadong.huawei.dagger.component.DaggerAppRecommendComponent;
import com.yadong.huawei.dagger.module.AppRecommendModule;
import com.yadong.huawei.model.net.bean.AppRecommendBean;
import com.yadong.huawei.presenter.contract.AppRecommendContract;
import com.yadong.huawei.presenter.fragment.AppRecommendPresenter;
import com.yadong.huawei.ui.activity.AppDetailActivity;
import com.yadong.huawei.ui.base.BaseFragmentPro;

/**
 *
 */

public class AppRecommendFragment extends BaseFragmentPro<AppRecommendPresenter>
        implements AppRecommendContract.View {


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_comment;
    }

    @Override
    protected void initInjector() {
        DaggerAppRecommendComponent
                .builder()
                .appComponent(getAppComponent())
                .appRecommendModule(new AppRecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
        mPresenter.getData(((AppDetailActivity)getActivity()).getAppPackageName());
    }


    @Override
    public void getDataSuccess(AppRecommendBean bean) {

    }

    @Override
    public void getDataFail(String message) {

    }
}

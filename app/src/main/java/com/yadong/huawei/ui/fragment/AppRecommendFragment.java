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
        return R.layout.fragment_app_recommend;
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
        System.out.println("获取数据   AppRecommendFragment");
        mPresenter.getData(((AppDetailActivity)getActivity()).getAppPackageName());
    }


    @Override
    public void getDataSuccess(AppRecommendBean bean) {
        mBaseLoadService.showSuccess();
    }

    @Override
    public void getDataFail(String message) {

    }
}

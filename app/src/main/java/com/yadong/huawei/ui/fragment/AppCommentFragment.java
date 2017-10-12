package com.yadong.huawei.ui.fragment;

import com.yadong.huawei.R;
import com.yadong.huawei.dagger.component.DaggerAppCommentComponent;
import com.yadong.huawei.dagger.module.AppCommentModule;
import com.yadong.huawei.model.net.bean.AppCommentBean;
import com.yadong.huawei.presenter.contract.AppCommentContract;
import com.yadong.huawei.presenter.fragment.AppCommentPresenter;
import com.yadong.huawei.ui.activity.AppDetailActivity;
import com.yadong.huawei.ui.base.BaseFragmentPro;

/**
 *
 */

public class AppCommentFragment extends BaseFragmentPro<AppCommentPresenter>
        implements AppCommentContract.View {


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_app_comment;
    }

    @Override
    protected void initInjector() {
        DaggerAppCommentComponent
                .builder()
                .appComponent(getAppComponent())
                .appCommentModule(new AppCommentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
        System.out.println("获取数据   AppCommentFragment");
        mPresenter.getData(((AppDetailActivity)getActivity()).getAppPackageName());
    }


    @Override
    public void getDataSuccess(AppCommentBean bean) {
        mBaseLoadService.showSuccess();
    }

    @Override
    public void getDataFail(String message) {

    }
}

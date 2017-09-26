package com.yadong.huawei.presenter.activity;

import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.AppDetailContract;
import com.yadong.huawei.presenter.contract.CategoryContract;

/**
 *
 */

public class AppDetailPresenter implements CategoryContract.Presenter {


    private AppDetailContract.View mView;
    private ApiService mApiService;

    public AppDetailPresenter(AppDetailContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {


    }
}

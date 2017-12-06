package com.yadong.huawei.presenter.fragment;

import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.ManageContract;

/**
 *
 */

public class ManagePresenter implements ManageContract.Presenter {

    private ManageContract.View mView;
    private ApiService mApiService;

    public ManagePresenter(ManageContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

}

package com.yadong.huawei.module.fragment.manage;

import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.request.ApiService;

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

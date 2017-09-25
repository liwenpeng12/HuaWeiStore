package com.yadong.huawei.presenter.fragment;

import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.MineContract;

/**
 *
 */

public class MinePresenter implements MineContract.Presenter {

    private MineContract.View mView;
    private ApiService mApiService;

    public MinePresenter(MineContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {

    }
}

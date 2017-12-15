package com.yadong.huawei.module.fragment.mine;

import com.yadong.huawei.utils.RetrofitUtils;
import com.yadong.huawei.model.remote.request.ApiService;

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

}

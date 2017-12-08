package com.yadong.huawei.presenter.activity;

import com.yadong.huawei.common.utils.JsonParseUtils;
import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.model.net.bean.CategoryNewBean;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.CategoryNewContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class CategoryNewPresenter implements CategoryNewContract.Presenter {


    private CategoryNewContract.View mView;
    private ApiService mApiService;

    public CategoryNewPresenter(CategoryNewContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {
        mView.showLoading();
        mApiService
                .getCategoryNewData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        CategoryNewBean detailBean = JsonParseUtils.parseCategoryNewBean(string);
                        mView.getDataSuccess(detailBean);
                        mView.hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.hideLoading();
                        mView.getDataFail(throwable.getMessage());
                    }
                });
    }
}

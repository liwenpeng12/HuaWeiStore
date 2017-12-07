package com.yadong.huawei.presenter.activity;

import com.yadong.huawei.common.utils.JsonParseUtils;
import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.model.net.bean.CategoryNecessaryBean;
import com.yadong.huawei.model.net.bean.CategorySubscribeBean;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.CategoryNecessaryContract;
import com.yadong.huawei.presenter.contract.CategorySubscribeContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class CategoryNecessaryPresenter implements CategoryNecessaryContract.Presenter {


    private CategoryNecessaryContract.View mView;
    private ApiService mApiService;

    public CategoryNecessaryPresenter(CategoryNecessaryContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData() {
        mView.showLoading();
        mApiService
                .getCategoryNecessaryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        CategoryNecessaryBean detailBean = JsonParseUtils.parseCategoryNecessaryBean(string);
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

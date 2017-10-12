package com.yadong.huawei.presenter.fragment;

import com.yadong.huawei.common.utils.JsonParseUtils;
import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.model.net.bean.AppCommentBean;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.AppCommentContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 *
 */

public class AppCommentPresenter implements AppCommentContract.Presenter {

    private AppCommentContract.View mView;
    private ApiService mApiService;

    public AppCommentPresenter(AppCommentContract.View view) {
        this.mView = view;
        mApiService = RetrofitUtils.getApiService();
    }

    @Override
    public void getData(String packageName) {
        mApiService
                .getAppCommentData(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        AppCommentBean bean = JsonParseUtils.parseAppCommentBean(string);
                        mView.getDataSuccess(bean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.getDataFail(throwable.getMessage());
                    }
                });
    }


    @Override
    public void getData() {

    }
}

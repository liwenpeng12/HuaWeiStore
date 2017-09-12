package com.yadong.huawei.presenter.fragment;

import com.yadong.huawei.common.utils.JsonParseUtils;
import com.yadong.huawei.model.net.bean.RecommendBean;
import com.yadong.huawei.model.net.request.ApiService;
import com.yadong.huawei.presenter.contract.RecommendContract;
import com.yadong.huawei.ui.widget.LoadingPager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 推荐
 *
 * P层
 */
public class RecommendPresenter implements RecommendContract.Presenter {

    private ApiService mApiService;
    private RecommendContract.View mView;

    public RecommendPresenter(RecommendContract.View view,ApiService apiService) {
        this.mView = view;
        this.mApiService=apiService;
    }

    @Override
    public void getData() {
        mApiService
                .getRecommendData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
                        mView.getDataSuccess(recommendBean);

                        mView.setCurrentState(LoadingPager.LoadResult.success);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.setCurrentState(LoadingPager.LoadResult.error);
                        mView.getDataFail(throwable.getMessage());
                    }
                });
    }

    @Override
    public void getRecommendDataMore() {
        mApiService
                .getRecommendData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.<ResponseBody>bindToLife())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
                        mView.getDataMoreSuccess(recommendBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.getDataFail(throwable.getMessage());
                    }
                });
    }
}

package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppDetailBean;
import com.yadong.huawei.model.net.bean.AppMoreRecommendBean;
import com.yadong.huawei.model.net.bean.AppRecommendBean;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 契约借口
 */
public interface AppMoreRecommendContract {

    interface View extends BaseView {
        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(AppMoreRecommendBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String type,String packageName);

    }

}

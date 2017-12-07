package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppDetailBean;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 契约借口
 */
public interface AppDetailContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(AppDetailBean bean);

        void getDataFail(String message);

    }

    interface Presenter {
        void getData(String packageName);

    }

}

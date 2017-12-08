package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.CategoryNewBean;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 契约借口
 */
public interface CategoryNewContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(CategoryNewBean bean);

        void getDataFail(String message);

    }

    interface Presenter {
        void getData();

    }

}

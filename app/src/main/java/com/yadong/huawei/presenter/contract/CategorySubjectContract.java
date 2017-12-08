package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.presenter.base.BaseView;

import java.util.List;

/**
 * 契约借口
 */
public interface CategorySubjectContract {

    interface View extends BaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(List<String> list);

        void getDataFail(String message);

    }

    interface Presenter {
        void getData();

    }

}

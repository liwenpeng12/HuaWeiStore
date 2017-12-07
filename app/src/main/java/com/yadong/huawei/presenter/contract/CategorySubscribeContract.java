package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.CategorySubscribeBean;
import com.yadong.huawei.presenter.base.IBaseView;

/**
 * 契约借口
 */
public interface CategorySubscribeContract {

    interface View extends IBaseView {

        /**
         * 显示加载动画
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        void getDataSuccess(CategorySubscribeBean bean);

        void getDataFail(String message);

    }

    interface Presenter {
        void getData();

    }

}

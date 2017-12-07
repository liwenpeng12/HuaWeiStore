package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppRecommendBean;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 契约借口
 */
public interface AppRecommendContract {

    interface View extends BaseView {
        void getDataSuccess(AppRecommendBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String packageName);

    }

}

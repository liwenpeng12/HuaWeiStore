package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppIntroductionBean;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 推荐
 * <p>
 * 契约借口
 */
public interface AppIntroductionContract {

    interface View extends BaseView {
        void getDataSuccess(AppIntroductionBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData(String packageName);

    }

}

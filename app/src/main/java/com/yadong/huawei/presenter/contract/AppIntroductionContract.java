package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppIntroductionBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;

/**
 * 推荐
 * <p>
 * 契约借口
 */
public interface AppIntroductionContract {

    interface View extends IBaseView {
        void getDataSuccess(AppIntroductionBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends IBasePresenter {
        void getData(String packageName);

    }

}

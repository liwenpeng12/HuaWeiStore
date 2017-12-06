package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppCommentBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;

/**
 * 契约借口
 */
public interface AppCommentContract {

    interface View extends IBaseView {
        void getDataSuccess(AppCommentBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends IBasePresenter {
        void getData(String packageName);

    }

}

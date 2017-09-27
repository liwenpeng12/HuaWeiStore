package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;
import com.yadong.huawei.ui.widget.loadsir.callback.Callback;

/**
 * 管理
 *
 * 契约借口
 */
public interface ManageContract {

    interface View extends IBaseView{

        /**
         * 设置当前的页面状态
         */
        void setCurrentState(Class<? extends Callback> clazz);
    }

    interface Presenter extends IBasePresenter {


    }

}

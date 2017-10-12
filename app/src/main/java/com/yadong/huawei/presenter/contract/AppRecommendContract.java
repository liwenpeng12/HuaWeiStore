package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.AppRecommendBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;
import com.yadong.huawei.ui.widget.loadsir.callback.Callback;

/**
 *
 *
 * 契约借口
 */
public interface AppRecommendContract {

    interface View extends IBaseView{

        /**
         * 设置当前的页面状态
         */
        void setCurrentState(Class<? extends Callback> clazz);

        void getDataSuccess(AppRecommendBean bean);

        void getDataFail(String message);


    }

    interface Presenter   extends IBasePresenter{
        void getData(String packageName);

    }

}

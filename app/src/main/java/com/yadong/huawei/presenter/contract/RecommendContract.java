package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.RecommendBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;

/**
 * 推荐
 *
 * 契约借口
 */
public interface RecommendContract {

    interface View extends IBaseView{

        /**
         * 设置当前的页面状态
         */
//        void setCurrentState(Class<? extends Callback> clazz);

        void getDataSuccess(RecommendBean recommendBean);

        void getDataFail(String message);

        void getDataMoreSuccess(RecommendBean recommendBean);
    }

    interface Presenter extends IBasePresenter {

        void getRecommendDataMore();
    }

}

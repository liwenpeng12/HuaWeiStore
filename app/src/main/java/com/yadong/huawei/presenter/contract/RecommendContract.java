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

        void getDataSuccess(RecommendBean recommendBean);

        void getDataFail(String message);

        void getDataMoreSuccess(RecommendBean recommendBean);
    }

    interface Presenter extends IBasePresenter {

        void getRecommendDataMore();
    }

}

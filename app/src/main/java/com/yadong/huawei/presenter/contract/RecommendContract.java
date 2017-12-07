package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.RecommendBean;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 推荐
 * <p>
 * 契约借口
 */
public interface RecommendContract {

    interface View extends BaseView {

        void getDataSuccess(RecommendBean recommendBean);

        void getDataFail(String message);

        void getDataMoreSuccess(RecommendBean recommendBean);
    }

    interface Presenter extends BasePresenter {

        void getRecommendDataMore();

        void getData();
    }

}

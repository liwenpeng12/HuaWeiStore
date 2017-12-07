package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.TopBean;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 排行
 * <p>
 * 契约借口
 */
public interface RankingContract {

    interface View extends BaseView {
        void getDataSuccess(TopBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends BasePresenter {
        void getData();
    }

}

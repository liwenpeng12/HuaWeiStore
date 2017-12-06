package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.TopBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;

/**
 * 排行
 * <p>
 * 契约借口
 */
public interface RankingContract {

    interface View extends IBaseView {
        void getDataSuccess(TopBean bean);

        void getDataFail(String message);
    }

    interface Presenter extends IBasePresenter {
        void getData();
    }

}

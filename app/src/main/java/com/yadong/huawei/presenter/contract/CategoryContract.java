package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.CategoryBean;
import com.yadong.huawei.presenter.base.BasePresenter;
import com.yadong.huawei.presenter.base.BaseView;

/**
 * 分类
 * <p>
 * 契约借口
 */
public interface CategoryContract {

    interface View extends BaseView {


        void getDataSuccess(CategoryBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends BasePresenter {

        void getData();

    }

}

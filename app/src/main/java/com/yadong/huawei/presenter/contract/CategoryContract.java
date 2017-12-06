package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.CategoryBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;

/**
 * 分类
 *
 * 契约借口
 */
public interface CategoryContract {

    interface View extends IBaseView{


        void getDataSuccess(CategoryBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends IBasePresenter {


    }

}

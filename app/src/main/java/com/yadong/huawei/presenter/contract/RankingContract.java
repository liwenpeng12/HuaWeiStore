package com.yadong.huawei.presenter.contract;

import com.yadong.huawei.model.net.bean.TopBean;
import com.yadong.huawei.presenter.base.IBasePresenter;
import com.yadong.huawei.presenter.base.IBaseView;
import com.yadong.huawei.ui.widget.LoadingPager;

/**
 * 排行
 *
 * 契约借口
 */
public interface RankingContract {

    interface View extends IBaseView{

        /**
         * 设置当前的页面状态
         * @param result 加载中,成功,失败,空
         */
        void setCurrentState(LoadingPager.LoadResult result);

        void getDataSuccess(TopBean bean);

        void getDataFail(String message);

    }

    interface Presenter extends IBasePresenter {


    }

}

package com.yadong.huawei.presenter.base;


import com.yadong.huawei.ui.widget.LoadingPager;

/**
 *
 * 基础 BaseView 接口
 */
public interface IBaseView {

    /**
     * 设置当前的页面状态
     * @param result 加载中,成功,失败,空
     */
    void setCurrentState(LoadingPager.LoadResult result);


}

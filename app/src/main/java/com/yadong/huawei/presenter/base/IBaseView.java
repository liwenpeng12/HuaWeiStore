package com.yadong.huawei.presenter.base;


import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 *
 * 基础 BaseView 接口
 */
public interface IBaseView {

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();
}

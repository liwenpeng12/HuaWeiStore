package com.yadong.huawei.dagger.module;


import com.yadong.huawei.presenter.contract.ManageContract;
import com.yadong.huawei.presenter.fragment.ManagePresenter;

import dagger.Module;
import dagger.Provides;


/**
 *
 * module
 *
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 */
@Module
public class ManageModule {

    private ManageContract.View mView;

    public ManageModule(ManageContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public ManagePresenter providePresenter(ManageContract.View view) {
        return new ManagePresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public ManageContract.View provideView() {
        return mView;
    }


}

package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.fragment.mine.MineContract;
import com.yadong.huawei.module.fragment.mine.MinePresenter;

import dagger.Module;
import dagger.Provides;


/**
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 */
@Module
public class MineModule {

    private MineContract.View mView;

    public MineModule(MineContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public MinePresenter providePresenter(MineContract.View view) {
        return new MinePresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public MineContract.View provideView() {
        return mView;
    }


}

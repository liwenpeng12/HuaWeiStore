package com.yadong.huawei.dagger.module;


import com.yadong.huawei.module.activity.tool.CategoryToolPresenter;
import com.yadong.huawei.module.activity.tool.CategoryToolContract;

import dagger.Module;
import dagger.Provides;


/**
 * module
 * <p>
 * 专门用来提供实例的类,总而言之一句话,需要什么我们就提供什么
 * <p>
 * 提供推荐页面的所有对象
 */
@Module
public class CategoryToolModule {

    private CategoryToolContract.View mView;

    public CategoryToolModule(CategoryToolContract.View view) {
        this.mView = view;
    }

    /**
     * 提供了Presenter对象
     */
    @Provides
    public CategoryToolPresenter providePresenter(CategoryToolContract.View view) {
        return new CategoryToolPresenter(view);
    }


    /**
     * 提供了View对象
     */
    @Provides
    public CategoryToolContract.View provideView() {
        return mView;
    }


}

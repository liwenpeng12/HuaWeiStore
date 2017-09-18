package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.CategoryModule;
import com.yadong.huawei.dagger.score.FragmentScope;
import com.yadong.huawei.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * 推荐
 *
 * Component 连接器
 * 连接inject和module的连接器
 *
 */

//这一句,component关联到module
@FragmentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {

    //这一句,component关联到container
    void inject(CategoryFragment fragment);

}

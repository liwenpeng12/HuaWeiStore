package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.CategoryToolModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.module.activity.tool.CategoryToolActivity;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = CategoryToolModule.class, dependencies = AppComponent.class)
public interface CategoryToolComponent {
    //这一句,component关联到container
    void inject(CategoryToolActivity activity);

}

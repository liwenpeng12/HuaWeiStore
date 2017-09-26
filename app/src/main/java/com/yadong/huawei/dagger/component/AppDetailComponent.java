package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.AppDetailModule;
import com.yadong.huawei.dagger.score.ActivityScore;
import com.yadong.huawei.ui.activity.AppDetailActivity;

import dagger.Component;

/**
 * 推荐
 *
 * Component 连接器
 * 连接inject和module的连接器
 *
 */

//这一句,component关联到module
@ActivityScore
@Component(modules = AppDetailModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {

    //这一句,component关联到container
    void inject(AppDetailActivity activity);

}

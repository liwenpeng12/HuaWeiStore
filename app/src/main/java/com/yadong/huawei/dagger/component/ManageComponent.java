package com.yadong.huawei.dagger.component;


import com.yadong.huawei.dagger.module.ManageModule;
import com.yadong.huawei.dagger.score.FragmentScope;
import com.yadong.huawei.ui.fragment.ManageFragment;

import dagger.Component;

/**
 * Component 连接器
 * 连接inject和module的连接器
 */

//这一句,component关联到module
@FragmentScope
@Component(modules = ManageModule.class, dependencies = AppComponent.class)
public interface ManageComponent {

    //这一句,component关联到container
    void inject(ManageFragment fragment);

}

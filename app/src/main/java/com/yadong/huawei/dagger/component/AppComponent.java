package com.yadong.huawei.dagger.component;

import android.app.Application;

import com.yadong.huawei.dagger.module.AppModule;
import com.yadong.huawei.dagger.module.HttpModule;
import com.yadong.huawei.model.net.request.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App级别的 Component
 * 这个Component并不需要注入,因为是最高级别的,是为了让其他的component去引用
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    //作为桥接而已
    ApiService getApiService();

    //作为桥接而已,要不然引用这个Component的那个component拿不到这个对象
    Application getMyApplication();

}

package com.yadong.huawei.common.app;

import android.app.Application;
import android.os.Handler;

import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.dagger.component.DaggerAppComponent;
import com.yadong.huawei.dagger.module.AppModule;
import com.yadong.huawei.dagger.module.HttpModule;


/**
 *
 */

public class App extends Application {
    private static App instance;
    private AppComponent mAppComponent;

    private static int mMainThreadId;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mHandler=new Handler();


        initSdk();
        initUtils();
        initInjector();
    }

    /**
     * 初始化SDK
     */
    private void initSdk() {


    }

    private void initUtils() {
        RetrofitUtils.getInstance().initOkHttp(this);
    }

    /**
     * 初始化注射器
     */
    private void initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();
    }


    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    /**
     * 返回主线程的pid
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     */
    public static Handler getHandler() {
        return mHandler;
    }

}

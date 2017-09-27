package com.yadong.huawei.common.app;

import android.app.Application;
import android.os.Handler;

import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.common.utils.CrashHandler;
import com.yadong.huawei.common.utils.RetrofitUtils;
import com.yadong.huawei.dagger.component.AppComponent;
import com.yadong.huawei.dagger.component.DaggerAppComponent;
import com.yadong.huawei.dagger.module.AppModule;
import com.yadong.huawei.ui.widget.loadsir.core.LoadSir;
import com.yadong.huawei.ui.widget.loadsir.callback.EmptyCallback;
import com.yadong.huawei.ui.widget.loadsir.callback.ErrorCallback;
import com.yadong.huawei.ui.widget.loadsir.callback.LoadingCallback;


/**
 *
 */

public class App extends Application {

    private static App instance;
    private static AppComponent mAppComponent;
    private static int mMainThreadId;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        initConfig();
        initSdk();
        initUtils();
        initInjector();
        initLoadSir();
    }



    /**
     * 初始化配置
     */
    private void initConfig() {
        instance = this;
        mHandler = new Handler();
    }

    /**
     * 初始化SDK
     */
    private void initSdk() {


    }

    private void initUtils() {
        CrashHandler.getInstance().init(this);
        RetrofitUtils.getInstance().initOkHttp(this);
        GlobalDialogManager.getInstance().init();
    }

    /**
     * 初始化注射器
     */
    private void initInjector() {
        // 这里不做注入操作，只提供一些全局单例数据
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }


    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
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

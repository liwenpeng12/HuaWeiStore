package com.yadong.huawei.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yadong.huawei.R;
import com.yadong.huawei.common.app.App;
import com.yadong.huawei.common.utils.Constants;
import com.yadong.huawei.common.utils.PreferenceUtils;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.ui.base.BaseActivity;

import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 闪屏页面
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void updateViews() {
        boolean isEnter = PreferenceUtils.getBoolean(Constants.IS_ENTER);
        if (isEnter) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    /**
     * 重写初始化沉浸式状态栏
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarDarkFont(true)
                .init();
    }

    @OnClick(R.id.bt_enter)
    public void onViewClicked() {
        initPermission();
    }

    /**
     * 检查权限
     */
    private void initPermission() {
        //如果版本>6.0才会有权限适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            new RxPermissions(this)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                PreferenceUtils.putBoolean(Constants.IS_ENTER, true);
                                ToastUtil.show(App.getInstance(), "授权SD卡成功");

                                startActivity(new Intent(App.getInstance(), MainActivity.class), true);
                                finish();

                            } else {
                                PreferenceUtils.putBoolean(Constants.IS_ENTER, false);
                                ToastUtil.show(App.getInstance(), "没有授权SD卡，可能会影响应用的使用");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Logger.i(throwable.getMessage());
                        }
                    });
        } else {
            PreferenceUtils.putBoolean(Constants.IS_ENTER, true);
            startActivity(new Intent(this, MainActivity.class),true);
            finish();
        }
    }

}

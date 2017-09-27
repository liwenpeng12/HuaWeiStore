package com.yadong.huawei.common.manager;

import android.app.FragmentManager;

import com.yadong.huawei.ui.widget.dialog.LoadingDialog;

/**
 * Dialog管理类
 */

public class GlobalDialogManager {

    private LoadingDialog mLoadingDialog;

    private GlobalDialogManager() {
    }

    public static GlobalDialogManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static GlobalDialogManager INSTANCE = new GlobalDialogManager();
    }

    public void init() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
    }

    public void show(FragmentManager manager) {
        if (mLoadingDialog != null) {
            mLoadingDialog.show(manager, "");
        }
    }

    public void dismiss() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }

    }
}

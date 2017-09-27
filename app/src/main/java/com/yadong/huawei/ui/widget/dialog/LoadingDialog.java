package com.yadong.huawei.ui.widget.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;


/**
 *
 * Android 普通加载框
 */
public class LoadingDialog extends DialogFragment /*implements DialogInterface.OnKeyListener*/ {

    /**
     * 默认点击外面无效
     */
    private boolean onTouchOutside = false;

    /**
     * 加载框提示信息 设置默认
     */
    private String hintMsg = "正在加载，请稍后...";

    /**
     * 设置是否允许点击外面取消
     */
    public LoadingDialog setOnTouchOutside(boolean onTouchOutside) {
        this.onTouchOutside = onTouchOutside;
        return this;
    }

    /**
     * 设置加载框提示信息
     */
    public LoadingDialog setHintMsg(String hintMsg) {
        if (!hintMsg.isEmpty()) {
            this.hintMsg = hintMsg;
        }
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        // 设置背景透明
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 去掉标题
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCanceledOnTouchOutside(onTouchOutside);
        View loadingView = inflater.inflate(R.layout.dialog_android_loading, container);
        TextView hintTextView = (TextView) loadingView.findViewById(R.id.tv_loading_dialog_hint);
        hintTextView.setText(hintMsg);

        hintTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalDialogManager.getInstance().dismiss();
            }
        });

        //不响应返回键
//        dialog.setOnKeyListener(this);
        return loadingView;
    }


//    @Override
//    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return true;
//        }
//        return false;
//    }
}

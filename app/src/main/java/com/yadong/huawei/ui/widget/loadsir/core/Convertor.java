package com.yadong.huawei.ui.widget.loadsir.core;


import com.yadong.huawei.ui.widget.loadsir.callback.Callback;

/**
 */
public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}

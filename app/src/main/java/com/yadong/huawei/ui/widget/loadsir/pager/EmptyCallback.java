package com.yadong.huawei.ui.widget.loadsir.pager;


import com.yadong.huawei.R;
import com.yadong.huawei.ui.widget.loadsir.callback.Callback;

/**
 */

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.loading_empty_page;
    }
}

package com.yadong.huawei.module.activity.web;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.module.base.BaseActivity;

import butterknife.BindView;

/**
 * 带进度条的Web页面
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.web_view)
    WebView mWebView;

    private String mUrl;
    private String mName;

    /**
     * 重写初始化沉浸式状态栏
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.tab_background)
                .init();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        getIntentData();
        mTitle.setText(mName);
    }

    private void getIntentData() {
        mName = getIntent().getStringExtra("name");
        mUrl = getIntent().getStringExtra("url");
    }

    @Override
    public void updateViews() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }
}

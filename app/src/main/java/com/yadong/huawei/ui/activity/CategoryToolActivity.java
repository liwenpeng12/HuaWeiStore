package com.yadong.huawei.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.common.utils.Constants;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerCategoryToolComponent;
import com.yadong.huawei.dagger.module.CategoryToolModule;
import com.yadong.huawei.model.net.bean.CategoryToolBean;
import com.yadong.huawei.presenter.activity.CategoryToolPresenter;
import com.yadong.huawei.presenter.contract.CategoryToolContract;
import com.yadong.huawei.ui.adapter.CategoryToolMultiAdapter;
import com.yadong.huawei.ui.adapter.wrapper.RecommendTopWrapper;
import com.yadong.huawei.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class CategoryToolActivity extends BaseActivity
        implements CategoryToolContract.View, CategoryToolMultiAdapter.AppItemListener {

    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    CategoryToolPresenter mPresenter;

    @Override
    public int setLayout() {
        return R.layout.activity_category_tool;
    }

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
    public void initInjector() {
        DaggerCategoryToolComponent
                .builder()
                .appComponent(getAppComponent())
                .categoryToolModule(new CategoryToolModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initViews() {
        String name = getIntent().getStringExtra("name");
        mTitle.setText(name);
    }

    @Override
    public void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void getDataSuccess(CategoryToolBean bean) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //主体adapter
        CategoryToolMultiAdapter adapter = new CategoryToolMultiAdapter(this);
        adapter.addDataAll(bean.getCategoryToolAppBeanList());
        //头部轮播图
        RecommendTopWrapper topWrapper = new RecommendTopWrapper(this,adapter) ;
        topWrapper.addDataAll(bean.getBannerList());
        mRecyclerView.setAdapter(topWrapper);
        //条目的点击事件
        adapter.setAppItemListener(this);
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(this,message);
    }

    @Override
    public void goAppDetail(String packageName) {
        Intent intent = new Intent(CategoryToolActivity.this, AppDetailActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME,packageName) ;
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        GlobalDialogManager.getInstance().show(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        GlobalDialogManager.getInstance().dismiss();
    }

    @Override
    public void finish() {
        super.finish();
        exitAnim();
    }


}

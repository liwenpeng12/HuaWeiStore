package com.yadong.huawei.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yadong.huawei.R;
import com.yadong.huawei.common.manager.GlobalDialogManager;
import com.yadong.huawei.common.utils.Constants;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerCategoryNewComponent;
import com.yadong.huawei.dagger.module.CategoryNewModule;
import com.yadong.huawei.model.net.bean.AppBean;
import com.yadong.huawei.model.net.bean.CategoryNewBean;
import com.yadong.huawei.presenter.activity.CategoryNewPresenter;
import com.yadong.huawei.presenter.contract.CategoryNewContract;
import com.yadong.huawei.ui.adapter.CategoryNewAdapter;
import com.yadong.huawei.ui.adapter.wrapper.CategoryNewTopWrapper;
import com.yadong.huawei.ui.base.BaseActivity;
import com.yadong.huawei.ui.widget.recyclerview.adapter.MultiItemTypeAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 首发
 */
public class CategoryNewActivity extends BaseActivity
        implements CategoryNewContract.View, MultiItemTypeAdapter.OnItemClickListener {


    @BindView(R.id.title_text)
    TextView mTitle;

    @BindView(R.id.iv_search)
    ImageView mImgSearch;

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @Inject
    CategoryNewPresenter mPresenter;
    private CategoryNewBean mDataModel;

    @Override
    public int setLayout() {
        return R.layout.activity_category_new;
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
        DaggerCategoryNewComponent
                .builder()
                .appComponent(getAppComponent())
                .categoryNewModule(new CategoryNewModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initViews() {
        mTitle.setText("今日首发");
    }

    @Override
    public void updateViews() {
        mPresenter.getData();
    }

    @Override
    public void getDataSuccess(CategoryNewBean bean) {
        mDataModel = bean;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategoryNewAdapter adapter = new CategoryNewAdapter(this);
        adapter.addDataAll(bean.getAppBeanList());
        CategoryNewTopWrapper categoryNewTopWrapper = new CategoryNewTopWrapper(this, adapter, bean.getHead());
        mRecyclerView.setAdapter(categoryNewTopWrapper);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(this, message);
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        AppBean appBean = mDataModel.getAppBeanList().get(position);
        Intent intent = new Intent(CategoryNewActivity.this, AppDetailActivity.class);
        intent.putExtra(Constants.PACKAGE_NAME, appBean.getPackageName());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
        return false;
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

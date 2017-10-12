package com.yadong.huawei.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerAppCommentComponent;
import com.yadong.huawei.dagger.module.AppCommentModule;
import com.yadong.huawei.model.net.bean.AppCommentBean;
import com.yadong.huawei.presenter.contract.AppCommentContract;
import com.yadong.huawei.presenter.fragment.AppCommentPresenter;
import com.yadong.huawei.ui.activity.AppDetailActivity;
import com.yadong.huawei.ui.adapter.section.AppCommentContactsSection;
import com.yadong.huawei.ui.adapter.wrapper.AppCommentTopWrapper;
import com.yadong.huawei.ui.base.BaseFragmentPro;
import com.yadong.huawei.ui.widget.loadsir.callback.ErrorCallback;
import com.yadong.huawei.ui.widget.recyclerview.section.SectionRVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * App详情页面_评论fragment
 */
public class AppCommentFragment extends BaseFragmentPro<AppCommentPresenter>
        implements AppCommentContract.View {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AppCommentBean mAppCommentBean;

    private List<AppCommentBean.CommentsBean> hotList = new ArrayList<>();
    private List<AppCommentBean.CommentsBean> list = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_app_comment;
    }

    @Override
    protected void initInjector() {
        DaggerAppCommentComponent
                .builder()
                .appComponent(getAppComponent())
                .appCommentModule(new AppCommentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
        mPresenter.getData(((AppDetailActivity) getActivity()).getAppPackageName());
    }

    @Override
    public void getDataSuccess(AppCommentBean bean) {
        this.mAppCommentBean = bean;
        showRevData();
        mBaseLoadService.showSuccess();
    }

    /**
     * 展示列表数据
     */
    private void showRevData() {
        for (AppCommentBean.CommentsBean commentsBean : mAppCommentBean.getComments()) {
            //type为1是精彩评论
            if (commentsBean.getCommentType().equals("1")) {
                hotList.add(commentsBean);
            } else {
                list.add(commentsBean);
            }
        }

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());
        if (hotList.size() > 0) {
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(), "精彩评论", hotList));
        }
        if (list.size() > 0) {
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(), "全部评论", list));
        }

        AppCommentTopWrapper appCommentTopWrapper = new AppCommentTopWrapper(getContext(), sectionAdapter);
        appCommentTopWrapper.addDataAll(mAppCommentBean);
        mRecyclerView.setAdapter(appCommentTopWrapper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(getContext(), message);
        setCurrentState(ErrorCallback.class);
    }


}

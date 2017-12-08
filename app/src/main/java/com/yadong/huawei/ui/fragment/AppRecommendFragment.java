package com.yadong.huawei.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerAppRecommendComponent;
import com.yadong.huawei.dagger.module.AppRecommendModule;
import com.yadong.huawei.model.net.bean.AppRecommendBean;
import com.yadong.huawei.presenter.contract.AppRecommendContract;
import com.yadong.huawei.presenter.fragment.AppRecommendPresenter;
import com.yadong.huawei.ui.activity.AppDetailActivity;
import com.yadong.huawei.ui.adapter.section.AppRecommendHotSection;
import com.yadong.huawei.ui.adapter.section.AppRecommendPopularSection;
import com.yadong.huawei.ui.adapter.section.AppRecommendTasteSection;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;
import com.yadong.huawei.ui.widget.recyclerview.section.SectionRVAdapter;

import butterknife.BindView;

/**
 * App详情页面_推荐fragment
 */
public class AppRecommendFragment extends BaseFragment<AppRecommendPresenter>
        implements AppRecommendContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AppRecommendBean mRecommendBean;
    private String mPackageName;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_app_recommend;
    }

    @Override
    protected void initInjector() {
        DaggerAppRecommendComponent
                .builder()
                .appComponent(getAppComponent())
                .appRecommendModule(new AppRecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {
        mPackageName = ((AppDetailActivity) getActivity()).getAppPackageName();
        mPresenter.getData(mPackageName);
    }


    @Override
    public void getDataSuccess(AppRecommendBean bean) {
        this.mRecommendBean = bean;
        showRevData();
        setCurrentState(LoadingPager.LoadResult.success);
    }

    /**
     * 展示列表数据
     */
    private void showRevData() {
        SectionRVAdapter adapter = new SectionRVAdapter(getContext());

        adapter.addSection(new AppRecommendPopularSection(getContext(), "流行应用",
                mRecommendBean.getPopularAppBeanList(), mPackageName));

        adapter.addSection(new AppRecommendTasteSection(getContext(), "兴趣相近的用户也安装了",
                mRecommendBean.getTasteAppBeanList(), mPackageName));

        adapter.addSection(new AppRecommendHotSection(getContext(), "本周热议的社区应用",
                mRecommendBean.getHotAppBeanList(), mPackageName));

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void getDataFail(String message) {
        ToastUtil.show(getContext(), message);
        setCurrentState(LoadingPager.LoadResult.error);
    }


}

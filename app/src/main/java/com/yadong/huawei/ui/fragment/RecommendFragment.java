package com.yadong.huawei.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.MyToast;
import com.yadong.huawei.dagger.component.DaggerRecommendComponent;
import com.yadong.huawei.dagger.module.RecommendModule;
import com.yadong.huawei.model.net.bean.RecommendBean;
import com.yadong.huawei.presenter.contract.RecommendContract;
import com.yadong.huawei.presenter.fragment.RecommendPresenter;
import com.yadong.huawei.ui.adapter.RecommendAdapter;
import com.yadong.huawei.ui.adapter.RecommendTopWrapper;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;
import com.yadong.huawei.ui.widget.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 推荐
 *
 * V层
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter>
        implements RecommendContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.pull_to_refresh)
    PullToRefreshView mPullToRefresh;

    private RecommendAdapter mRecommendAdapter;
    private RecommendTopWrapper mTopWrapper;
    private List<RecommendBean.RecommendAppBean> recommendAppBeanList = new ArrayList<>();//加载更多的集合数据

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initInjector() {
        DaggerRecommendComponent
                .builder()
                .appComponent(getAppComponent())
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        show();//在这调用show,是因为,页面刚进来的时候,外界的Tab并不会默认调用第一个fragment的show方法,所有需要在这手动调用下
    }

    @Override
    protected void updateViews() {
        getRevData();
    }

    /**
     * 获取数据
     */
    private void getRevData() {
        mPresenter.getData();
    }

    /**
     * 获取数据成功
     */
    @Override
    public void getDataSuccess(RecommendBean recommendBean) {
        showRevData(recommendBean);
        setLoadMore();
    }

    private void showRevData(RecommendBean recommendBean) {
        mRecommendAdapter = new RecommendAdapter(getContext());
        mRecommendAdapter.addDataAll(recommendBean.getRecommendAppBeanList());
        mTopWrapper = new RecommendTopWrapper(getContext(), mRecommendAdapter);
        mTopWrapper.addDataAll(recommendBean.getBannerList());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopWrapper);
    }

    private void setLoadMore() {
        mPullToRefresh.setPullDownEnable(false);//不允许下拉刷新

        mPullToRefresh.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                mPresenter.getRecommendDataMore();
            }
        });
    }

    /**
     * 获取数据失败
     */
    @Override
    public void getDataFail(String message) {
        MyToast.show(getContext(), message);
    }

    /**
     * 设置页面的状态
     * @param result 加载中,成功,失败,空
     */
    @Override
    public void setCurrentState(LoadingPager.LoadResult result) {
        super.setCurrentState(result);
    }



    /**
     * 获取更多数据
     * @param recommendBean
     */
    @Override
    public void getDataMoreSuccess(RecommendBean recommendBean) {
        recommendAppBeanList.addAll(recommendBean.getRecommendAppBeanList());
        mRecommendAdapter.clearData();
        mRecommendAdapter.addDataAll(recommendAppBeanList);
        mTopWrapper.notifyDataSetChanged();

        mPullToRefresh.onFinishLoading();
    }

}

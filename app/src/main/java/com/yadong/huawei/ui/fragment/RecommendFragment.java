package com.yadong.huawei.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.Constants;
import com.yadong.huawei.common.utils.MyToast;
import com.yadong.huawei.dagger.component.DaggerRecommendComponent;
import com.yadong.huawei.dagger.module.RecommendModule;
import com.yadong.huawei.model.net.bean.RecommendBean;
import com.yadong.huawei.presenter.contract.RecommendContract;
import com.yadong.huawei.presenter.fragment.RecommendPresenter;
import com.yadong.huawei.ui.activity.AppDetailActivity;
import com.yadong.huawei.ui.adapter.RecommendAdapter;
import com.yadong.huawei.ui.adapter.wrapper.RecommendTopWrapper;
import com.yadong.huawei.ui.base.BaseFragmentPro;
import com.yadong.huawei.ui.widget.recyclerview.pullrefresh.PullToRefreshView;

import butterknife.BindView;

/**
 * 推荐
 *
 * V层
 */
public class RecommendFragment extends BaseFragmentPro<RecommendPresenter>
        implements RecommendContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.pull_to_refresh)
    PullToRefreshView mPullToRefresh;

    private RecommendAdapter mRecommendAdapter;
    private RecommendTopWrapper mTopWrapper;

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
//        show();//在这调用show,是因为,页面刚进来的时候,外界的Tab并不会默认调用第一个fragment的show方法,所有需要在这手动调用下
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
        mBaseLoadService.showSuccess();

        showRevData(recommendBean);
        setLoadMoreListener();
        setItemClickListener();
    }


    /**
     * 展示条目数据
     */
    private void showRevData(RecommendBean recommendBean) {
        //多条目的Adapter
        mRecommendAdapter = new RecommendAdapter(getContext());
        mRecommendAdapter.addDataAll(recommendBean.getRecommendAppBeanList());

        //头的Adapter
        mTopWrapper = new RecommendTopWrapper(getContext(), mRecommendAdapter);
        mTopWrapper.addDataAll(recommendBean.getBannerList());

        //设置Adapter给RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopWrapper);
    }

    /**
     * 设置加载更多监听
     */
    private void setLoadMoreListener() {
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
     * 设置条目的点击事件
     */
    private void setItemClickListener() {
        mRecommendAdapter.setAppItemListener(new RecommendAdapter.AppItemListener() {
            @Override
            public void goAppDetail(String packageName) {
                Intent intent = new Intent(getContext(), AppDetailActivity.class);
                intent.putExtra(Constants.PACKAGE_NAME,packageName);
                mContext.startActivity(intent,true);
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
//    @Override
//    public void setCurrentState(LoadingPager.LoadResult result) {
//        super.setCurrentState(result);
//    }

    /**
     * 获取更多数据
     */
    @Override
    public void getDataMoreSuccess(RecommendBean recommendBean) {
        mPullToRefresh.onFinishLoading();//完成加载更多
        //把获取到的数据塞到集合中
        mRecommendAdapter.addDataAll(recommendBean.getRecommendAppBeanList());
        mTopWrapper.notifyDataSetChanged();
    }

}

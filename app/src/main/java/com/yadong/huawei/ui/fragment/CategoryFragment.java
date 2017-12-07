package com.yadong.huawei.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.dagger.component.DaggerCategoryComponent;
import com.yadong.huawei.dagger.module.CategoryModule;
import com.yadong.huawei.model.net.bean.CategoryBean;
import com.yadong.huawei.presenter.contract.CategoryContract;
import com.yadong.huawei.presenter.fragment.CategoryPresenter;
import com.yadong.huawei.ui.activity.CategoryNecessaryActivity;
import com.yadong.huawei.ui.activity.CategoryNewActivity;
import com.yadong.huawei.ui.activity.CategorySubjectActivity;
import com.yadong.huawei.ui.activity.CategorySubscribeActivity;
import com.yadong.huawei.ui.adapter.section.CategorySection;
import com.yadong.huawei.ui.adapter.wrapper.CategoryTopWrapper;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.LoadingPager;
import com.yadong.huawei.ui.widget.ViewUpSearch;
import com.yadong.huawei.ui.widget.recyclerview.section.SectionRVAdapter;

import butterknife.BindView;

/**
 * 分类
 */
public class CategoryFragment extends BaseFragment<CategoryPresenter>
        implements CategoryContract.View, CategoryTopWrapper.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.view_up_search)
    ViewUpSearch mViewUpSearch;

    private boolean mIsExpand = true;//是否展开,默认是展开的

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_catagory;
    }

    @Override
    protected void initInjector() {
        DaggerCategoryComponent
                .builder()
                .appComponent(getAppComponent())
                .categoryModule(new CategoryModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void updateViews() {
        mPresenter.getData();
    }


    @Override
    public void getDataSuccess(CategoryBean bean) {
        setCurrentState(LoadingPager.LoadResult.success);
        showRevData(bean);
        setSearchListener();
    }

    /**
     * 展示RecyclerView列表
     */
    private void showRevData(CategoryBean bean) {
        SectionRVAdapter adapter = new SectionRVAdapter(getContext());

        CategorySection categoryContactsSection = new CategorySection(getContext(),
                bean.getTitle(), bean.getCategoryDataBeanList());

        adapter.addSection(categoryContactsSection);

        CategoryTopWrapper categoryTopWrapper = new CategoryTopWrapper(getContext(), adapter);
        categoryTopWrapper.addDataAll(bean.getCategoryTopBeanList());

        mRecyclerView.setAdapter(categoryTopWrapper);

        categoryTopWrapper.setOnItemClickListener(this);

    }

    /**
     * 设置搜索框的显示,监听RecyclerView
     */
    private void setSearchListener() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = ((LinearLayoutManager) (mRecyclerView.getLayoutManager())).findFirstVisibleItemPosition();

                /**
                 * 1.是第一个条目 2.是展开状态 3.dy>0:是向上滑动
                 * 搜索框应该隐藏,标记也跟着改
                 * firstVisibleItemPosition==0 并且 mIsExpand==true 并且 dy>0
                 */
                if (firstVisibleItemPosition == 0 && mIsExpand && dy > 0) {
                    mViewUpSearch.updateShow(false);
                    mIsExpand = false;

                }
                /**
                 * 1.是第一个条目 2.没有展开 3.dy<0:是向下滑动
                 * 搜索框应显示
                 * firstVisibleItemPosition==0 并且 mIsExpand==false 并且 dy>0
                 */
                else if (firstVisibleItemPosition == 0 && !mIsExpand && dy < 0) {
                    mViewUpSearch.updateShow(true);
                    mIsExpand = true;
                }

            }
        });
    }

    @Override
    public void getDataFail(String message) {
        System.out.println("getDataFail   1ci" + message);
        setCurrentState(LoadingPager.LoadResult.error);
        ToastUtil.show(getContext(), message);
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                //预约
                startActivity(new Intent(getContext(), CategorySubscribeActivity.class));
                break;
            case 1:
                //必备
                startActivity(new Intent(getContext(), CategoryNecessaryActivity.class));
                break;

            case 2:
                //首发
                startActivity(new Intent(getContext(), CategoryNewActivity.class));
                break;
            default:
                //专题
                startActivity(new Intent(getContext(), CategorySubjectActivity.class));
                break;
        }
    }
}

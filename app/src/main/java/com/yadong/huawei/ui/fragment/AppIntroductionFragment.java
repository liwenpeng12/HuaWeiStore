package com.yadong.huawei.ui.fragment;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.ImageLoader;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.common.utils.UIUtils;
import com.yadong.huawei.dagger.component.DaggerAppIntroductionComponent;
import com.yadong.huawei.dagger.module.AppIntroductionModule;
import com.yadong.huawei.model.net.bean.AppIntroductionBean;
import com.yadong.huawei.presenter.contract.AppIntroductionContract;
import com.yadong.huawei.presenter.fragment.AppIntroductionPresenter;
import com.yadong.huawei.ui.activity.AppDetailActivity;
import com.yadong.huawei.ui.base.BaseFragmentPro;
import com.yadong.huawei.ui.widget.FlowLayout;
import com.yadong.huawei.ui.widget.FoldingTextView;
import com.yadong.huawei.ui.widget.loadsir.callback.ErrorCallback;

import java.util.List;

import butterknife.BindView;

/**
 * App详情页面_介绍fragment
 */

public class AppIntroductionFragment extends BaseFragmentPro<AppIntroductionPresenter>
        implements AppIntroductionContract.View {


    @BindView(R.id.app_detail_gallery_container)
    LinearLayout mGalleryContainer;

    @BindView(R.id.detail_appinfo_tariff)
    TextView mAppInfoTariff;

    @BindView(R.id.detail_appinfo_size)
    TextView mAppInfoSize;

    @BindView(R.id.detail_appinfo_version)
    TextView mAppInfoVersion;

    @BindView(R.id.detail_appinfo_release_date)
    TextView mAppInfoReleaseDate;

    @BindView(R.id.appdetail_appinfo_developer)
    TextView mAppInfoDeveloper;

    @BindView(R.id.detail_desc_title)
    TextView mDetailDescTitle;

    @BindView(R.id.flowLayout)
    FlowLayout mFlowLayout;

    @BindView(R.id.app_info_des_container)
    LinearLayout mAppInfoDesContainer;

    private AppIntroductionBean mIntroductionBean;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_app_introduction;
    }

    @Override
    protected void initInjector() {
        DaggerAppIntroductionComponent
                .builder()
                .appComponent(getAppComponent())
                .appIntroductionModule(new AppIntroductionModule(this))
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
    public void getDataSuccess(AppIntroductionBean bean) {
        mIntroductionBean = bean;
        setDetail();
        mBaseLoadService.showSuccess();
    }

    /**
     * 设置详情
     */
    private void setDetail() {
        setGallery();
        setInfo();
        setInfoDesc();
        setFlowLayout();
    }

    /**
     * 设置横向的图片展示
     */
    private void setGallery() {
        for (int i = 0; i < mIntroductionBean.getImageCompressList().size(); i++) {
            String url = mIntroductionBean.getImageCompressList().get(i);
            View screenView = View.inflate(getContext(), R.layout.appdetail_item_screen_image, null);
            ImageView screenImageView = (ImageView) screenView.findViewById(R.id.appdetail_screen_img_imageview);
            screenImageView.setContentDescription(screenImageView.getResources().getString(R.string.appdetail_screenshot));
            screenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            screenView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(getContext(),"老子被点击了");
                }
            });
            screenView.setTag(i);

            ImageLoader.load(getContext(),url,screenImageView,R.drawable.app_icon_default);
            mGalleryContainer.addView(screenView);
        }
    }

    /**
     * 设置App介绍的基本信息
     */
    private void setInfo() {
        mAppInfoTariff.setText(mIntroductionBean.getAppInfoBean().getTariffDesc());
        mAppInfoSize.setText(Formatter.formatFileSize(getContext(), Long.parseLong(mIntroductionBean.getAppInfoBean().getSize())));
        mAppInfoReleaseDate.setText(mIntroductionBean.getAppInfoBean().getReleaseDate());
        mAppInfoVersion.setText(mIntroductionBean.getAppInfoBean().getVersion());
        mAppInfoDeveloper.setText(mIntroductionBean.getAppInfoBean().getDeveloper());
    }

    /**
     * 设置横向的应用标签
     */
    private void setFlowLayout() {
        List<String> tagList = mIntroductionBean.getTagList();
        for (int i = 0; i < tagList.size(); i++) {
            View labView = UIUtils.inflate(R.layout.appdetail_item_label_item);
            final TextView tv = (TextView) labView.findViewById(R.id.appdetail_label_content_textview);
            tv.setText(tagList.get(i));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.show(getContext(),tv.getText());
                }
            });
            mFlowLayout.addView(labView);
        }
    }

    /**
     * 设置详细的app信息内容
     */
    private void setInfoDesc() {
        for (int i = 0; i < mIntroductionBean.getAppDetailInfoBeanList().size(); i++) {
            FoldingTextView foldingTextView = new FoldingTextView(getContext());
            foldingTextView.setTitle(mIntroductionBean.getAppDetailInfoBeanList().get(i).getTitle());
            foldingTextView.setContent(mIntroductionBean.getAppDetailInfoBeanList().get(i).getBody());
            mAppInfoDesContainer.addView(foldingTextView);
        }
    }


    @Override
    public void getDataFail(String message) {
        ToastUtil.show(getContext(),message);
        setCurrentState(ErrorCallback.class);
    }

}

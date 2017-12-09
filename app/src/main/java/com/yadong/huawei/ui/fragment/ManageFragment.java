package com.yadong.huawei.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yadong.huawei.R;
import com.yadong.huawei.common.utils.ToastUtil;
import com.yadong.huawei.common.utils.UIUtils;
import com.yadong.huawei.dagger.component.DaggerManageComponent;
import com.yadong.huawei.dagger.module.ManageModule;
import com.yadong.huawei.presenter.contract.ManageContract;
import com.yadong.huawei.presenter.fragment.ManagePresenter;
import com.yadong.huawei.ui.activity.ApkManagementActivity;
import com.yadong.huawei.ui.activity.CleanCacheActivity;
import com.yadong.huawei.ui.activity.InstallAppInfoActivity;
import com.yadong.huawei.ui.base.BaseFragment;
import com.yadong.huawei.ui.widget.EnterLayout;
import com.yadong.huawei.ui.widget.LoadingPager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 管理
 */
public class ManageFragment extends BaseFragment<ManagePresenter>
        implements ManageContract.View {

    @BindView(R.id.update_label_textview)
    TextView tvUpdateLabel;

    @BindView(R.id.update_label_subtitle)
    TextView tvUpdateLabelSubtitle;

    @BindView(R.id.install_manager_layout)
    EnterLayout installManager;

    @BindView(R.id.apk_manager_layout)
    EnterLayout apkManager;

    @BindView(R.id.system_manager_layout)
    EnterLayout systemManager;

    @BindView(R.id.connect_computer)
    EnterLayout connect;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void initInjector() {
        DaggerManageComponent
                .builder()
                .appComponent(getAppComponent())
                .manageModule(new ManageModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        tvUpdateLabel.setText(UIUtils.getString(R.string.update_manager_title));
        tvUpdateLabelSubtitle.setText(UIUtils.getString(R.string.update_manager_subtitle_version_new));
        installManager.setTitle(UIUtils.getString(R.string.install_manager_title_ex));
        installManager.setMemo(UIUtils.getString(R.string.install_manager_subtitle));
        apkManager.setTitle(UIUtils.getString(R.string.apk_management));
        apkManager.setMemo(UIUtils.getString(R.string.apkmanage_tips_modify));
        systemManager.setTitle(UIUtils.getString(R.string.system_manager_title));
        systemManager.setMemo(UIUtils.getString(R.string.system_manager_memo));
        connect.setTitle(UIUtils.getString(R.string.connect_pc));
        connect.setMemo(UIUtils.getString(R.string.manager_phone_by_pc));
    }

    @Override
    protected void updateViews() {
        setCurrentState(LoadingPager.LoadResult.success);
    }


    @OnClick({R.id.install_manager_layout, R.id.apk_manager_layout,
            R.id.system_manager_layout, R.id.connect_computer, R.id.update_manager_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //安装管理
            case R.id.install_manager_layout:
               startActivity(new Intent(getContext(),InstallAppInfoActivity.class));
                break;
             //安装包管理
            case R.id.apk_manager_layout:
                startActivity(new Intent(getContext(), ApkManagementActivity.class));
                break;

            //空间清理
            case R.id.system_manager_layout:
                startActivity(new Intent(getContext(), CleanCacheActivity.class));
                break;

            case R.id.connect_computer:
                ToastUtil.show(getContext(),"连接电脑");
                break;

            case R.id.update_manager_layout:
                ToastUtil.show(getContext(),"更新管理");
                break;
        }
    }
}

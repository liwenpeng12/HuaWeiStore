package com.yadong.huawei.common.factory;


import com.yadong.huawei.ui.base.BaseFragmentPro;
import com.yadong.huawei.ui.fragment.AppCommentFragment;
import com.yadong.huawei.ui.fragment.AppIntroductionFragment;
import com.yadong.huawei.ui.fragment.AppRecommendFragment;
import com.yadong.huawei.ui.fragment.CategoryFragment;
import com.yadong.huawei.ui.fragment.ManageFragment;
import com.yadong.huawei.ui.fragment.MineFragment;
import com.yadong.huawei.ui.fragment.RankingFragment;
import com.yadong.huawei.ui.fragment.RecommendFragment;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class FragmentFactory {

    /**
     * 推荐
     */
    public static final int TAB_RECOMMEND = 0;

    /**
     * 分类
     */
    public static final int TAB_CATEGORY = 1;

    /**
     * 排行
     */
    public static final int TAB_RANKING = 2;

    /**
     * 管理
     */
    public static final int TAB_MANAGE = 3;

    /**
     * 我的
     */
    public static final int TAB_MINE = 4;

    /**
     * App详情_介绍
     */
    public static final int TAB_APP_INTRODUCTION = 5;

    /**
     * App详情_评论
     */
    public static final int TAB_APP_COMMENT = 6;

    /**
     * App详情_推荐
     */
    public static final int TAB_APP_RECOMMEND = 7;


    private static Map<Integer, BaseFragmentPro> mFragments = new HashMap<>();

    /**
     * 创建fragment
     *
     * @param index 索引
     */
    public static BaseFragmentPro createFragment(int index) {
        BaseFragmentPro fragment = mFragments.get(index);
        // 如果之前没有创建, 创建新的Fragment
        if (fragment == null) {
            switch (index) {
                case TAB_RECOMMEND:
                    fragment = new RecommendFragment();
                    break;
                case TAB_CATEGORY:
                    fragment = new CategoryFragment();
                    break;
                case TAB_RANKING:
                    fragment = new RankingFragment();
                    break;
                case TAB_MANAGE:
                    fragment = new ManageFragment();
                    break;
                case TAB_MINE:
                    fragment = new MineFragment();
                    break;


                case TAB_APP_INTRODUCTION:
                    fragment = new AppIntroductionFragment();
                    break;
                case TAB_APP_COMMENT:
                    fragment = new AppCommentFragment();
                    break;
                case TAB_APP_RECOMMEND:
                    fragment = new AppRecommendFragment();
                    break;

            }
            // 把创建的Fragment 存起来
            mFragments.put(index, fragment);
        }
        return fragment;
    }

}

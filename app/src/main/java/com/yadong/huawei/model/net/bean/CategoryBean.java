package com.yadong.huawei.model.net.bean;

import java.util.List;

/**
 *
 */
public class CategoryBean {

    private List<CategoryTopBean> categoryTopBeanList ;//头部信息数据

    private String title ;//标题
    private List<CategoryDataBean> categoryDataBeanList ;//内容

    public CategoryBean(String title,List<CategoryTopBean> categoryTopBeanList, List<CategoryDataBean> categoryDataBeanList) {
        this.title = title ;
        this.categoryTopBeanList = categoryTopBeanList;
        this.categoryDataBeanList = categoryDataBeanList;
    }

    public String getTitle() {
        return title;
    }

    public List<CategoryTopBean> getCategoryTopBeanList() {
        return categoryTopBeanList;
    }

    public List<CategoryDataBean> getCategoryDataBeanList() {
        return categoryDataBeanList;
    }

    public static class CategoryTopBean {

        private String iconUrl ;
        private String name ;

        public CategoryTopBean(String iconUrl, String name) {
            this.iconUrl = iconUrl;
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public String getName() {
            return name;
        }
    }


    public static class CategoryDataBean {
        private String iconUrl ;
        private String name ;

        public CategoryDataBean(String iconUrl, String name) {
            this.iconUrl = iconUrl;
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public String getName() {
            return name;
        }
    }
}

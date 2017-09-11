package com.yadong.huawei.common.app;


/**
 * 全局变量的类
 */
public class Account {


    private Account() {
    }

    public static Account getInstance() {
        return SingletonHolder.INSTANCE;
    }



    private static class SingletonHolder {
        private static Account INSTANCE = new Account();
    }
}

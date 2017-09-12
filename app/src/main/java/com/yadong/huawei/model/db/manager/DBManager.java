package com.yadong.huawei.model.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yadong.huawei.model.db.bean.DaoMaster;
import com.yadong.huawei.model.db.bean.DaoSession;
import com.yadong.huawei.model.db.bean.User;
import com.yadong.huawei.model.db.bean.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 数据库的管理
 */

public class DBManager {

    private static DBManager instance;
    private final static String dbName = "test_db";

    private Context mContext;
    private DaoMaster.DevOpenHelper openHelper = null;

    private DBManager(Context context) {
        this.mContext = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName);
    }

    public static DBManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager(context);
                }
            }
        }
        return instance;
    }

    private SQLiteDatabase getWriteableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(mContext, dbName);
        }
        return openHelper.getWritableDatabase();
    }

    private SQLiteDatabase getRedadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(mContext, dbName);
        }
        return openHelper.getReadableDatabase();
    }


    public void saveUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        //存入user对象
        userDao.save(user);
    }

    public void saveUsers(List<User> users) {
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.saveInTx(users);
    }

    public void delteUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }


    public void updateUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }

    public List<User> queryUser() {
        DaoMaster daoMaster = new DaoMaster(getRedadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();
        return list;
    }

    public List<User> queryUser(int age) {
        DaoMaster daoMaster = new DaoMaster(getRedadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        //添加查询条件
        qb.where(UserDao.Properties.Age.eq(age));
        List<User> list = qb.list();
        return list;
    }

}

package com.example.desizemode;

/**
 * Created by yinsheng.wang on 2018/5/17.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }

    @Override
    public void save(String str) {
        System.out.println("----" + str + "----");
    }
}

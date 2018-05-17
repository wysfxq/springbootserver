package com.example.desizemode;

/**
 * Created by yinsheng.wang on 2018/5/17.
 */
public class UserDaoProxy implements IUserDao {
    //接收保存目标对象
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务...");
        target.save();//执行目标对象的方法
        System.out.println("提交事务...");
    }

    @Override
    public void save(String str) {
        System.out.println("开始事务...");
        System.out.println("----" + str + "----");
        System.out.println("提交事务...");
    }
}

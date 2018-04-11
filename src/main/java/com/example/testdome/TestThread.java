package com.example.testdome;

/**
 * Created by yinsheng.wang on 2018/4/4.
 */
public class TestThread implements Runnable {
    private Long id;

    public TestThread(Long id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(PublicClass.aa);
        if (id == 10) {
            System.out.println("1111111");
            return;
        }
        System.out.println("22222222");
    }
}

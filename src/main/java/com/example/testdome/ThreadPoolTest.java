package com.example.testdome;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yinsheng.wang on 2018/4/24.
 */
public class ThreadPoolTest {
    static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
       /* test1();
        test2();*/

        for (int i = 0; i < 50; i++) {
            ThreadExccute.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + ">>>>>>>>>>>>>");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
     /*   for (int i = 0; i < 50; i++) {
            ThreadExccute.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + "******************");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }*/

    }

    /*public static void test1() {
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + ">>>>>>>>>>>>>");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void test2() {
        for (int i = 0; i < 100; i++) {
            ThreadExccute.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + "******************");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }*/
}

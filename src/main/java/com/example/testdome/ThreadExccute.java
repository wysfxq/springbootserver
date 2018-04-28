package com.example.testdome;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yinsheng.wang on 2018/4/24.
 */
public class ThreadExccute {
    //public static ExecutorService instance =  Executors.newFixedThreadPool(5);
   private static ExecutorService instance = null;

    private ThreadExccute() {
    }

    public static ExecutorService getInstance() {
        if (instance == null) {
            synchronized (ExecutorService.class) {
                if (instance == null) {
                    instance = Executors.newFixedThreadPool(5);
                }
            }
        }
        return instance;
    }
}

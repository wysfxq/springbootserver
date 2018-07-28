package com.example.testdome;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yinsheng.wang on 2018/4/24.
 */
public class ThreadExccute {
    private static ExecutorService instance = null;
    private static int poolNum = 10;

    private ThreadExccute() {
    }

    public static ExecutorService getInstance() {
        if (instance == null) {
            synchronized (ExecutorService.class) {
                if (instance == null) {
                    instance = Executors.newFixedThreadPool(poolNum);
                }
            }
        }
        return instance;
    }
}

package com.example.annotation;

/**
 * Created by yinsheng.wang on 2018/5/4.
 */

public class NoBug {
    @WysWrite
    public void suanShu() {
        System.out.println("1234567890");
    }

    @WysWrite
    public void jiafa(@NotNull int a, @NotNull int b) {
        System.out.println("1+1=" + 1 + 1);
    }

    @WysWrite
    public void jiefa() {
        System.out.println("1-1=" + (1 - 1));
    }

    @WysWrite
    public void chengfa() {
        System.out.println("3 x 5=" + 3 * 5);
    }

    @WysWrite
    public void chufa() {
        System.out.println("6 / 0=" + 6 / 0);
    }

    public void ziwojieshao() {
        System.out.println("我写的程序没有 bug!");
    }
}

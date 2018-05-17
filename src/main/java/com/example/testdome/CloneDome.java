package com.example.testdome;

/**
 * Created by yinsheng.wang on 2018/5/10.
 */
public class CloneDome {
    public static void main(String[] args) throws Exception {
        //test1();
        test2();
    }

    public static void test1() {
        Persion persion1 = new Persion();
        persion1.setAge(20);
        persion1.setName("test1");
        System.out.println(persion1.getName() + "---------" + persion1.getAge());

        Persion persion2 = persion1;
        persion2.setAge(21);
        persion2.setName("test2");
        System.out.println(persion2.getName() + "---------" + persion2.getAge());
        System.out.println(persion1.getName() + "---------" + persion1.getAge());

        System.out.println(persion1);
        System.out.println(persion2);
    }

    public static void test2() throws CloneNotSupportedException {
        Persion persion1 = new Persion();
        persion1.setAge(20);
        persion1.setName("test1");
        System.out.println(persion1.getName() + "---------" + persion1.getAge());

        Persion persion2 = (Persion) persion1.clone();
     /*   persion2.setAge(21);
        persion2.setName("test2");
        System.out.println(persion2.getName() + "---------" + persion2.getAge());
        System.out.println(persion1.getName() + "---------" + persion1.getAge());*/

        System.out.println("++++++++++++++++++++++++++++++++");

        System.out.println(persion1.getName().hashCode());
        System.out.println(persion2.getName().hashCode());

        System.out.println(persion1);
        System.out.println(persion2);

    }
}

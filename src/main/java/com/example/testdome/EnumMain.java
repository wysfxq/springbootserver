package com.example.testdome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yinsheng.wang on 2018/7/25.
 */
public class EnumMain {
    public static void main(String[] args) {
        System.out.println(EnumDome.ColorStatus.RED.getColorValue());
        System.out.println(EnumDome.Color.getName(1002));
        System.out.println(EnumDome.Color.GREEN.getCode());
        System.out.println(EnumDome.Color.GREEN.getName());


        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");

        if (stringList.contains("b")) {
            System.out.println("bbbbb------>>>>>>");
        }

        Iterator<String> it = stringList.iterator();
        while (it.hasNext()) {
            if ("b".equals(it.next())) {
                it.remove();
            }
        }
        System.out.println(stringList);

        List<Persion> persionList = new ArrayList<>();
        Persion persion;
        persion = new Persion();
        persion.setName("test1");
        persion.setAge(20);
        persionList.add(persion);

        persion = new Persion();
        persion.setName("test2");
        persion.setAge(22);
        persionList.add(persion);

        List<Persion> persions = persionList.stream().filter(t -> t.getName().equals("test2")).collect(Collectors.toList());
        System.out.println(persions);

        int ppp = persionList.stream().filter(t -> t.getName().equals("test2")).mapToInt(t -> t.getAge() == null ? 0 : t.getAge()).sum();
        System.out.println(ppp);

        int aa = persionList.stream().mapToInt(p -> p.getAge() == null ? 0 : p.getAge()).sum();
        System.out.println(aa);
        Iterator<Persion> it1 = persionList.iterator();
        while (it1.hasNext()) {
            if ("test2".equals(it1.next().getName())) {
                it1.remove();
            }
        }
        System.out.println(it1);
    }
}

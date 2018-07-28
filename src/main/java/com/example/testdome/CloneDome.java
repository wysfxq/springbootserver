package com.example.testdome;

import com.example.entity.User;
import com.example.utils.DateUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yinsheng.wang on 2018/5/10.
 */
public class CloneDome {
    public static void main(String[] args) throws Exception {
      /*  Long l=100L;
        BigDecimal b=new BigDecimal(1.9);
        System.out.println(l.longValue());
        System.out.println(b.longValue());*/

       /* Long l1 = 100000000L;
        Long l2 = 965654L;
        System.out.println(l1 - l2);
        System.out.println(l1.longValue() - l2.longValue());
        Long l3 = l1 - l2;
        System.out.println(l3);
        if (l1 > l2) {
            System.out.println("l1>l2");
        } else {
            System.out.println("l1<l2");
        }
        Long l4 = 100000000L;
        Long l5 = 100000000L;
        System.out.println(l4.longValue() == l5.longValue());
        System.out.println(l4 == l5);
        System.out.println(l4 < l5);
        System.out.println(l4 > 0);*/


        //test1();
        //test2();
        //test3();
        test4();
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

    public static void test3() {
        Set<User> userSet = new HashSet<>();
        User user;
        user = new User();
        user.setId(1001L);
        user.setName("test1");
        user.setAge(20);
        userSet.add(user);
        user = new User();
        user.setId(1001L);
        user.setName("test1");
        user.setAge(20);
        userSet.add(user);
        // System.out.println(userSet);


        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1001L);
        user1.setName("test1");
        user1.setAge(20);
        userList.add(user1);

        User user2 = new User();
        user2.setId(1001L);
        user2.setName("test1");
        user2.setAge(20);
        userList.add(user2);
        List<User> userList2 = new ArrayList<>();
        userList2.addAll(userList);
        System.out.println("userList2----" + userList2.size());
        userList2.remove(user2);
        System.out.println("userList1----" + userList.size());

       /* if (userList.contains(user1)) {
            System.out.println(true);
        }
        System.out.println(userList.size());
        userList.remove(user1);
        System.out.println(userList.size());*/

    }

    public static void test4() {

       /* Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-06-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        System.out.println(now);*/
        Date date = DateUtil.parse("2005-06-09","yyyy-MM-dd");
        System.out.println(DateUtil.format(date,"yyyy年MM月dd日"));


        Map<Long, List<Persion>> mapData = new HashMap<>();

        List<Persion> persionList = new ArrayList<>();
        List<Persion> persionList2 = new ArrayList<>();
        Persion persion;
        persion = new Persion();
        persion.setAge(10);
        persion.setName("test1");
        persionList.add(persion);
        persion = new Persion();
        persion.setAge(20);
        persion.setName("test2");
        persionList.add(persion);
        mapData.put(1001L, persionList);

        persion = new Persion();
        persion.setAge(30);
        persion.setName("test3");
        persionList2.add(persion);
        persion = new Persion();
        persion.setAge(40);
        persion.setName("test4");
        persionList2.add(persion);
        mapData.put(1002L, persionList2);

        for (List<Persion> item : mapData.values()) {
            for (Persion p : item) {
                System.out.println(p.getName());
            }
        }
    }
}

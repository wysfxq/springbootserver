package com.example.testdome;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.testdome.PublicClass.aa;

/**
 * Created by yinsheng.wang on 2018/3/29.
 */
public class TestMain {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        test8();
    }

    public static void test1() {
        // 要验证的字符串
        String str = "aaa111";
        // 邮箱验证规则
        String regEx = "^[A-Za-z0-9]+$";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);
    }

    public static void test2() {
        Long aa = null;
        if (aa.equals(10L)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    public static void test3() {
       /* Map<String, String> mapData = new HashMap<>();
        mapData.put("ukid", null);
        Long aa = Long.valueOf(mapData.get("ukid"));
        System.out.println(aa);*/
        System.out.println(new Date());
        Long aaa = 1000L;
        System.out.println(Math.toIntExact(aaa));

        String str = "99.99";
        Long l = new Long(str);
        System.out.println(l);
    }

    public static void test4() {
        TestThread testMain = new TestThread(10L);
        Thread thread = new Thread(testMain);
        thread.start();
        aa = "fxq";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test5() {
        //普通map
        String jsonString1 = "{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"}";
        System.out.println(jsonString1);
        Map<String, String> stringStringMap = (Map<String, String>) JSON.parse(jsonString1);
        for (String s : stringStringMap.keySet()) {
            System.out.println(s + "==>" + stringStringMap.get(s));
        }

        System.out.println("===================================================");

        //List<Map<String,String>>
        String jsonString2 = "[{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},{\"p1\":\"v1\",\"p2\":\"v2\",\"p3\":\"v3\",\"p4\":\"v4\",\"p5\":\"v5\"}]";
        System.out.println(jsonString2);
        List<Map<String, String>> mapList = JSON.parseObject(jsonString2, new TypeReference<List<Map<String, String>>>() {
        });
        for (Map<String, String> stringObjectMap : mapList) {
            for (String s : stringObjectMap.keySet()) {
                System.out.println(s + "==>" + stringObjectMap.get(s));
            }
        }
        System.out.println("===================================================");

        //Map<String,Object> ==> Object还能够进行分解
        String jsonString3 = "{\"count\":2,\"list\":[{\"param5\":\"value5\",\"param3\":\"value3\",\"param4\":\"value4\",\"param1\":\"value1\",\"param2\":\"value2\"},{\"p1\":\"v1\",\"p2\":\"v2\",\"p3\":\"v3\",\"p4\":\"v4\",\"p5\":\"v5\"}]}";
        System.out.println(jsonString3);
        Map<String, Object> map = JSON.parseObject(jsonString3);
        System.out.println(map.get("count"));
        String tempjsonString3 = map.get("list").toString();
        System.out.println(tempjsonString3);
        List<Map<String, String>> mapList2 = JSON.parseObject(tempjsonString3, new TypeReference<List<Map<String, String>>>() {
        });
        for (Map<String, String> stringObjectMap : mapList2) {
            for (String s : stringObjectMap.keySet()) {
                System.out.println(s + "==>" + stringObjectMap.get(s));
            }
        }
        System.out.println("===================================================");

        String jsonString4 = "[{\"age\":12,\"date\":1465475917155,\"name\":\"s1\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s2\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s3\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s4\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s5\"},{\"age\":12,\"date\":1465475917175,\"name\":\"s6\"}]";
        List<Student> studentList = JSON.parseArray(jsonString4, Student.class);
        for (Student student : studentList) {
            System.out.println(student.getName());
        }
        System.out.println("===================================================");
    }

    public static void test6() {
        Student student = new Student();
        student.setAge(20);
        student.setName(null);

        System.out.println(JSON.toJSONString(student));
    }

    public static void test7() {
        List<Student> studentList = new ArrayList<>();
        Student student;
        student = new Student();
        student.setName("stu1");
        student.setAge(20);
        studentList.add(student);
        student = new Student();
        student.setName("stu2");
        student.setAge(30);
        studentList.add(student);
        student = new Student();
        student.setName("stu2");
        student.setAge(40);
        studentList.add(student);

        studentList.forEach(student1 -> {
            System.out.println(student1.getName());
        });

        List<Student> aa = studentList.stream().filter(student1 -> student1.getName().
                equals("stu2")).collect(Collectors.toList());
        Student bb = studentList.stream().filter(student1 -> student1.getName().equals("stu2")).findFirst().get();

        System.out.println(JSON.toJSONString(aa));
        System.out.println(JSON.toJSONString(bb));


        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        System.out.println(names);
    }

    public static void test8() {
        Long aa = null;
        Long bb = null;
        ObjectUtils.isEmpty(aa);
        String cc="a,b,c";
        cc.split(",");
    }
}

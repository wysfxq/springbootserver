package com.example.testdome;

import sun.management.VMManagement;

import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.Source;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.sun.tools.javac.jvm.ByteCodes.ret;
import static org.apache.zookeeper.ZooDefs.OpCode.getData;

/**
 * Created by yinsheng.wang on 2018/4/25.
 */
public class UKID {
   /* 方案一：
    如果没有并发，订单号只在一个线程内产生，那么由于程序是顺序执行的，不同订单的生成时间戳正常不同，因此用时间戳+随机数（或自增数）就可以区分各个订单。
    如果存在并发，且订单号是由一个进程中的多个线程产生的，那么只要把线程ID添加到序列号中就可以保证订单号唯一。
    如果存在并发，且订单号是由同一台主机中的多个进程产生的，那么只要把进程ID添加到序列号中就可以保证订单号唯一。
    如果存在并发，且订单号是由不同台主机产生的，那么MAC地址、IP地址或CPU序列号等能够区分主机的号码添加到序列号中就可以保证订单号唯一。

    方案二：
    时间戳+用户ID+几个随机数+乐观锁。

    方案三：
    用redis的原子递增，做好高可用集群。

    方案四（非纯数字）：
    java自带uuid。*/

    public static void main(String[] args) throws Exception {
      /*  System.out.println(getThreadId());
        System.out.println(getProcessId());
        System.out.println(getMac());*/
        getData();
        getCurrentDate();
    }

    //获取线程ID
    public static long getThreadId() {
        return Thread.currentThread().getId();
    }

    public static int getProcessId() throws Exception {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        Field jvm = runtime.getClass().getDeclaredField("jvm");
        jvm.setAccessible(true);
        VMManagement mgmt = (VMManagement) jvm.get(runtime);
        Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
        pidMethod.setAccessible(true);
        int pid = (Integer) pidMethod.invoke(mgmt);
        return pid;
    }

    public static String getMac() throws Exception {
        InetAddress ia = InetAddress.getLocalHost();
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        String macStr = DatatypeConverter.printHexBinary(mac);
        return macStr;
    }

    public static void getData() {
        Calendar now = Calendar.getInstance();
        System.out.println(now.get(Calendar.YEAR));
        System.out.println(now.get(Calendar.MONTH) + 1);
        System.out.println(now.get(Calendar.DAY_OF_MONTH));
        System.out.println(now.get(Calendar.HOUR_OF_DAY));
        System.out.println(now.get(Calendar.MINUTE));
        System.out.println(now.get(Calendar.SECOND));
        System.out.println(now.getTimeInMillis());
    }
    public static void getCurrentDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String c=sdf.format(new Date());
        long date=Long.parseLong(c);
        System.out.println(date);
    }
  /*  public static String getUkid(){

    }*/
}

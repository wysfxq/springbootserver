package com.example.utils;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class ServerUtil {
    private static final Log logger = LogFactory.getLog(ServerUtil.class);
    private static String serverIp;
    private static String processId;
    private static String serverId;
    private static String serverPort;
    private static String serverOsName;
    private static String serverMac;
    private static String serverName;

    public ServerUtil() {
    }

    private static String initProcessId() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return processId = name.split("@")[0];
    }

    public static String getServerMac() {
        return serverMac;
    }

    public static String getServerName() {
        return serverName;
    }

    public static String getServerIp() {
        return serverIp;
    }

    public static String getServerOs() {
        return serverOsName;
    }

    public static String getProcessId() {
        return processId;
    }

    public static String getServerId() {
        return serverId;
    }

    public static String getServerPort() {
        return serverPort;
    }

    private static String initServerIP() {
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
            if (StringUtil.isEmpty(serverIp) || "127.0.0.1".equals(serverIp)) {
                Enumeration e = null;

                try {
                    e = NetworkInterface.getNetworkInterfaces();

                    while (e.hasMoreElements()) {
                        NetworkInterface e1 = (NetworkInterface) e.nextElement();
                        Enumeration ips = e1.getInetAddresses();

                        while (ips.hasMoreElements()) {
                            serverIp = ((InetAddress) ips.nextElement()).getHostAddress();
                            if (!"127.0.0.1".equals(serverIp)) {
                                break;
                            }
                        }
                    }
                } catch (Exception var3) {
                    serverIp = null;
                }
            }
        } catch (UnknownHostException var4) {
            serverIp = null;
            logger.error("", var4);
        }

        return serverIp;
    }

    private static String getTomcatPort(String path) {
        String filePath = path + "server.xml";
        String rtvValue = "80";

        try {
            File e = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(e);
            NodeList nl = doc.getElementsByTagName("Connector");
            Node portNode = null;
            boolean breakFlag = false;

            for (int nm = 0; nm < nl.getLength(); ++nm) {
                NamedNodeMap j = nl.item(nm).getAttributes();

                for (int j1 = 0; j1 < j.getLength(); ++j1) {
                    if (j.item(j1).getNodeName().equalsIgnoreCase("connectionTimeout")) {
                        portNode = nl.item(nm);
                        breakFlag = true;
                        break;
                    }
                }

                if (breakFlag) {
                    break;
                }
            }

            NamedNodeMap var14 = portNode.getAttributes();

            for (int var15 = 0; var15 < var14.getLength(); ++var15) {
                if (var14.item(var15).getNodeName().equalsIgnoreCase("port")) {
                    rtvValue = var14.item(var15).getNodeValue();
                    break;
                }
            }
        } catch (Exception var13) {
            logger.error(var13.getMessage(), var13);
        }

        return rtvValue;
    }

    private static void initServerNameAndMac() throws UnknownHostException, SocketException {
        InetAddress addr = InetAddress.getLocalHost();
        serverName = addr.getHostName();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(addr);
        byte[] mac = networkInterface.getHardwareAddress();
        String macStr = "";
        Formatter formatter = new Formatter();

        for (int i = 0; i < mac.length; ++i) {
            macStr = formatter.format(Locale.getDefault(), "%02X%s", new Object[]{Byte.valueOf(mac[i]), i < mac.length - 1 ? "-" : ""}).toString();
        }

        serverMac = macStr;
    }

    private static String initServerPort() {
        String path = System.getProperty("catalina.home");
        if (StringUtil.isEmpty(path)) {
            logger.error("path is empty. server port init failure...");
            return null;
        } else {
            String os = getServerOs();
            if (os.indexOf("Windows") >= 0) {
                path = path + "/conf/";
            } else {
                path = path + "/conf/";
            }

            return serverPort = getTomcatPort(path);
        }
    }

    public static void main(String[] args) {
        System.out.println(getProcessId());
        System.out.println(getServerId());
        System.out.println(getServerIp());
        System.out.println(getServerName());
        System.out.println(getServerOs());
        System.out.println(getServerPort());
        System.out.println(getServerMac());
    }

    static {
        try {
            serverOsName = System.getProperty("os.name");
            initServerIP();
            initProcessId();
            serverId = serverIp + "_" + processId;
            initServerPort();
            initServerNameAndMac();
            logger.debug("ServerUtil初始化完成. ServerIP:" + getServerIp() + ", ProcessId:" + getProcessId() + ", ServerMac:" + getServerMac() + ", ServerOs:" + getServerOs() + ", ServerName:" + getServerName());
        } catch (Exception var1) {
            logger.error("ServerUtil初始化出错", var1);
        }

    }
}

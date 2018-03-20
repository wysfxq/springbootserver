package com.example.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by yinsheng.wang on 2018/1/17
 */
public class FtpUtil {
    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path     文件路径
     * @param ftpPath  FTP服务器所在的目录
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String url, int port, String username, String password, String path, String ftpPath) throws FileNotFoundException {
        boolean success = false;
        FileInputStream input = new FileInputStream(new File(path));
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            //如果文件夹不存在则创建
            ftp.makeDirectory(ftpPath);
            //切换到指定的目录下
            ftp.changeWorkingDirectory(ftpPath);
            String filename = StringUtil.getFileNameWithSuffix(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url       FTP服务器hostname
     * @param port      FTP服务器端口
     * @param username  FTP登录账号
     * @param password  FTP登录密码
     * @param ftpPath   FTP服务器上的相对路径
     * @param fileName  要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String username, String password, String ftpPath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(ftpPath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    public static void main(String[] args) {
        //testUpLoadFromDisk();
        //testDownFile();
    }

    //将本地文件上传到FTP服务器上
    public static void testUpLoadFromDisk() {
        try {
            boolean flag = uploadFile("192.168.72.167", 21, "wys", "wys123", "D:/test.txt", "/f2/aaa");
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testDownFile() {
        try {
            boolean flag = downFile("192.168.72.167", 21, "wys", "wys123", "/f2/aaa", "test.txt", "E:/");
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
    public static void testUpLoadFromString() {
        try {
            InputStream input = new ByteArrayInputStream("test ftp".getBytes("utf-8"));
            // boolean flag = uploadFile("127.0.0.1", 21, "test", "test", "D:/ftp", "test.txt", input);
            // System.out.println(flag);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

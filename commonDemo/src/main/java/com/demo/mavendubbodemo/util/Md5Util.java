package com.demo.mavendubbodemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 *
 * @author Talen
 * @see MessageDigest
 */
public class Md5Util {
    /**
     */
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messagedigest = null;

    /**
     *
     *
     * */
    public static String getMD5(String code)  {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = code.getBytes("UTF-8");
            byte[] results = messageDigest.digest(bytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte result : results) {
                stringBuilder.append(String.format("%02x", result));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param file
     *
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }
    public static void main(String[] args) {
        String md5 = getMD5("bookId=1127&clientInfo.osVersion=OSmac&clientInfo.clientVersion=0.1.0"+"x7L3Cmdf");
        System.out.println(md5);
        //String md5 = null;
//        try {
//            //md5 = getFileMD5String(new File("/Users/wanghailong/work/2.0.9.gzip"));
//            System.out.println(md5);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}

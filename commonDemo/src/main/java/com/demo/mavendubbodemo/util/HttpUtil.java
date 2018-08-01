package com.demo.mavendubbodemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    static final Logger logger  =  LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) throws Exception{
        String result = "";
        BufferedReader in = null;
        String charset= "UTF-8";
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常！" + e);
            logger.error(e.getMessage(),e);
            throw new ConnectException("Connection refused");
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                logger.error(e2.getMessage(),e2);
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String charset= "UTF-8";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //conn.setConnectTimeout(5000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("HttpUtil.sendPost,发送 POST 请求出现异常,[{}]",e.getMessage());
            logger.error(e.getMessage(),e);
            throw new ConnectException("Connection refused");

        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                logger.error(ex.getMessage(),ex);
            }
        }
        return result;
    }

    /**
     * POST请求，Map形式数据
     * @param url 请求地址
     * @param param 请求数据
     */
    public static String sendPost(String url, Map<String, String> param) {

        String charset = "UTF-8";
        StringBuffer buffer = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                System.out.println(entry.getKey());
                buffer.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue()))
                        .append("&");

            }
        }
        buffer.deleteCharAt(buffer.length() - 1);

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(buffer);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常！"+ e.getMessage(),e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(),ex);
            }
        }
        return result;
    }

    public static byte[] getImg(String path){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] imgByte=null;
        try {
            URL url=new URL(path);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5*1000);
            InputStream is=conn.getInputStream();

            byte[] data=new byte[5*1024*1024];
            int len=0;
            while ((len=is.read(data))!=-1){
                bos.write(data,0,len);
            }
            bos.close();
            imgByte=bos.toByteArray();
        }
        catch (Exception e){
            logger.error("",e);
        }
        finally {

        }
        return imgByte;
    }

    public static void main(String[] args) {
//        String id = ConfigHelper.ShareInstance().getVPS_appId();
//        String id=null;
//        String url="http://xytest.staff.xdf.cn/api/class";
//        Map<String,String> param = new LinkedHashMap<String, String>();
//        param.put("method","GetClassListFilterByTeacherCode");
//        param.put("teacherCode","xdf0050009192");
//        param.put("appid","5001");
//
//        String code="method=GetClassListFilterByTeacherCode&teacherCode=xdf0050009192&appId=5001&appKey=v5appkey_test";
//        System.out.println(code);
//
//        String sign=(Md5Util.getMD5(code.toString().toLowerCase())).toUpperCase();
//        param.put("sign",sign);
//        System.out.println(sign);
//        String re = sendPost(url,param);
//        System.out.println(re);
//        System.out.println(id);
        try {
            String exercise=HttpUtil.sendGet("http://path.xdf.cn/vps/java/ws/getFullExerciseInfo.ws", "exerciseCode=8420-007-010-001-001 ");
            System.out.println(exercise);
            String code_str_1 = exercise.replace("\\n", "<br/>");
            String code_str_2 = code_str_1.replace("\\t", "&nbsp;");
            System.out.println(code_str_2);
//            Map<String, List<Map<String, String>>> str = (Map<String, List<Map<String, String>>>) JSON.parse(code_str_2);
        }
        catch (Exception e){
            logger.error("",e);
        }

    }



}
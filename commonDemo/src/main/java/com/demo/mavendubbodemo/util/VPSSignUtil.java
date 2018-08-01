package com.demo.mavendubbodemo.util;

import java.security.MessageDigest;
import java.util.*;

public class VPSSignUtil {

    public static void main(String[] args){
        Map<String, String> param = new HashMap<String, String>();
        param.put("schoolId", "5");
        param.put("teacherCodes","TCTJ0030125");
        param.put("startdate", "2017-01-01");
        param.put("gradeIdList", "12");
        param.put("subjectIdList", "15");
        param.put("areaCodeList", "ARTJ57");
        String key = "WCXLQNH99HOW9NHY3AATID7BLYWO8PML";
        String sign = VPSSignUtil.createSign(key, param);
        System.out.println(sign);
        sign = VPSSignUtil.createSign(key, param);
        System.out.println(sign);
    }

    /**
     * 将参数按照参数名排序形成签名
     *
     * @param key
     *            加密秘钥
     * @param params
     *            参数列表
     * @return 签名
     */
    public static String createSign(String key, Map<String, String> params) {
        try {
            Set<String> paramNameSet = params.keySet();
            List<String> paramNames = new ArrayList<String>();
            for (String paramName : paramNameSet) {
                paramNames.add(paramName);
            }

            orderParams(paramNames);

            StringBuffer sb = new StringBuffer();
            for (String paramName : paramNames) {
                String paramsValue = params.get(paramName);
                sb.append(paramName).append("=").append(paramsValue).append("&");
            }
            if (sb.charAt(sb.length() - 1) == '&') {
                sb.deleteCharAt(sb.length() - 1);
            }
            String paramWithVpsCode = sb.toString() + key;

            return md5(paramWithVpsCode);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public final static String md5(String s) {

        try {
            byte[] strTemp = s.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            return Hex.encodeHexString(md).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 排序参数
     *
     * @param params
     */
    private static void orderParams(List<String> params) {
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
}



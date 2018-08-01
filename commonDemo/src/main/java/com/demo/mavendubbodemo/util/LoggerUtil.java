package com.demo.mavendubbodemo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanghailong on 15/12/1.
 */
public class LoggerUtil {
    /** 线程编号修饰符 */
    private static final char THREAD_RIGHT_TAG = ']';

    /** 线程编号修饰符 */
    private static final char THREAD_LEFT_TAG  = '[';

    /**
     * 禁用构造函数
     */
    private LoggerUtil() {
        // 禁用构造函数
    }

    public static Logger getWebLog(){
       return LoggerFactory.getLogger("web");
    }
    /**
     * 生成<font color="blue">调试</font>级别日志<br>
     * 可处理任意多个输入参数，并避免在日志级别不够时字符串拼接带来的资源浪费
     *
     * @param logger    日志logger
     * @param obj       需要记录的对象信息
     */
    public static void debug(Logger logger, Object... obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(obj));
        }
    }

    /**
     * 生成<font color="blue">通知</font>级别日志<br>
     * 可处理任意多个输入参数，并避免在日志级别不够时字符串拼接带来的资源浪费
     *
     * @param logger        日志logger
     * @param obj           参数信息
     */
    public static void info(Logger logger, Object... obj) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(obj));
        }
    }

    /**
     * 生成<font color="brown">警告</font>级别日志<br>
     * 可处理任意多个输入参数，并避免在日志级别不够时字符串拼接带来的资源浪费
     *
     * @param logger        日志logger
     * @param obj           参数信息
     */
    public static void warn(Logger logger, Object... obj) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(obj));
        }
    }

    public static void error(Logger logger, Throwable ex, Object... obj){
        if(logger.isErrorEnabled()){
            logger.error(getLogString(obj)+" ex:",ex);
        }
    }
    public static void error(Logger logger, Object... obj){
        if(logger.isErrorEnabled()){
            logger.error(getLogString(obj));
        }
    }

    /**
     * 生成输出到日志的字符串
     *
     * @param obj 任意个要输出到日志的参数
     * @return      日志结果集
     */
    public static String getLogString(Object... obj) {
        StringBuilder log = new StringBuilder();
        log.append(THREAD_LEFT_TAG).append(Thread.currentThread().getId()).append(THREAD_RIGHT_TAG).
                append(DateUtil.getFormatNowTime(DateUtil.YYYY_MM_DD_HH_MM_SS_PATTERN))
                .append(THREAD_RIGHT_TAG);
        for (Object o : obj) {
            log.append(o);
        }
        return log.toString();
    }
}

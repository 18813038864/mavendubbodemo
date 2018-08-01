/**
 * Program  : ConfigUtil.java
 * Author   : zengtao
 * Create   : 2008-9-22 ����09:44:52
 *
 * Copyright 2006 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with Embedded Internet Solutions Inc.
 *
 */

package com.demo.mavendubbodemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 
 * @author   zengtao
 * @version  1.0.0
 * @2008-9-22 ����09:44:52
 */
public class ConfigUtil {

	static Properties properties=new Properties();
	Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	private static ConfigUtil _instance = null;

	public static ConfigUtil ShareInstance() {
		if(_instance == null){
			_instance = new ConfigUtil();
		}
		return _instance;
	}

	public ConfigUtil() {

		try {
			InputStreamReader is= new InputStreamReader(ConfigUtil.class.getResourceAsStream("/config.properties"),"UTF-8");
			properties.load(is);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public static  String getProperty(String key){
		String value= properties.getProperty(key);
		if(value==null){
			value= properties.getProperty(key);
		}
		return value;
	}
	
	public static int getIntProperty(String key){
		String value=getProperty(key);
		if(value==null)return 0;
		return Integer.parseInt(value.trim());
	}

}


package com.demo.mavendubbodemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(){
		return  "login";
	}

	/**
	 * 登录入口
	 * @return
	 */
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(String name,String passwd){

		logger.info("name:"+name+",passwd:"+passwd);
		return  "success";
	}
}

package com.wangshuo.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.User;
import com.wangshuo.cms.service.UserService;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	
	public String selects(String name,Integer page,Integer pageSize) {
	

		userService.selects(name, page, pageSize);
		return name;
		
		
	}
	
}

package com.wangshuo.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.User;
import com.wangshuo.cms.service.UserService;
import com.wangshuo.cms.util.PageUtil;

@RequestMapping("user")
@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("selects")
	public String selects(@RequestParam(defaultValue = "")String username,
			              @RequestParam(defaultValue = "1")Integer page,
			              @RequestParam(defaultValue = "3")Integer pageSize,Model model) {
	  
		PageInfo<User> info = userService.selects(username, page, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/user/selects?username="+username, pageSize);
		model.addAttribute("users", info.getList());
		model.addAttribute("username", username);
		model.addAttribute("pages", pages);
		
		
		return "admin/users";
		
		
	}
	
}

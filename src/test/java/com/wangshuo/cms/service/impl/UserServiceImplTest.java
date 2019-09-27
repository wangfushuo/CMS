package com.wangshuo.cms.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.User;
import com.wangshuo.cms.service.UserService;


public class UserServiceImplTest extends JunitParent{

	@Resource
	private UserService userService;
	
	@Test
	public void testSelects() {
		
		PageInfo<User> info = userService.selects(null, 0, 3)	;
		List<User> list = info.getList();
		
		System.out.println(list);	
	}

	@Test
	public void testInsertSelective() {
	}

	@Test
	public void testSelectByPrimaryKey() {
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
	}

}

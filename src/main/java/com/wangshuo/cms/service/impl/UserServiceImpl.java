package com.wangshuo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.dao.UserMapper;
import com.wangshuo.cms.domain.User;
import com.wangshuo.cms.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public PageInfo<User> selects(String name,Integer page,Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<User> list = userMapper.selects(name);
		 
		return new PageInfo<>(list);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(record);
	}

}

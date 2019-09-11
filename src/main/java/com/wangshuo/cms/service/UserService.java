package com.wangshuo.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.User;

public interface UserService {

	 
	    PageInfo<User> selects(String name,Integer page,Integer pageSize);    
	
	    int insertSelective(User record);

	    User selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(User record);

	   
	    
	
	
}

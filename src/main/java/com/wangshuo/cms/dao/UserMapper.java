package com.wangshuo.cms.dao;

import java.util.List;

import com.wangshuo.cms.domain.User;

public interface UserMapper {


	 List<User> selects(String name);
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    
}
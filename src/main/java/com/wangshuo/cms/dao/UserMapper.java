package com.wangshuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangshuo.cms.domain.User;

public interface UserMapper {


	 List<User> selects(@Param("username")String username);
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    
}
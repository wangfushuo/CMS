package com.wangshuo.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.Article;

public interface ArticleService {

    PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);
    
    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
	
	
}

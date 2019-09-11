package com.wangshuo.cms.dao;

import java.util.List;

import com.wangshuo.cms.domain.Article;

public interface ArticleMapper {
	
	
	List<Article> selects(Article article);
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

}
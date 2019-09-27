package com.wangshuo.cms.dao;

import java.util.Date;
import java.util.List;

import com.wangshuo.cms.domain.Article;

public interface ArticleMapper {
	
	
	/**
	 * 上篇
	 * @Title: selectPre 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article selectPre(Article  article);
	
	/**
	 * 下篇
	 * @Title: selectPre 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article selectNext(Article  article);
		
	/**
	 * 
	 * @Title: selects
	 * @Description: 文章列表查询
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selects(Article article);

	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	/**
	 * 
	 * @Title: selectByPrimaryKey
	 * @Description: 根据ID 查询单个文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article selectByPrimaryKey(Integer id);
	

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);
}
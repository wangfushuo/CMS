package com.wangshuo.cms.service;

import com.wangshuo.cms.domain.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
	
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
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);

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
	
}

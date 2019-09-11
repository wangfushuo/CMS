package com.wangshuo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.dao.ArticleMapper;
import com.wangshuo.cms.domain.Article;
import com.wangshuo.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{

	@Resource
	private ArticleMapper articleMapper;
	
	@Override
	public PageInfo<Article> selects(Article article,Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> list = articleMapper.selects(article);
		
		return new PageInfo<>(list);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.insert(record);
	}

	@Override
	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Article record) {
		// TODO Auto-generated method stub
		return articleMapper.updateByPrimaryKey(record);
	}

}

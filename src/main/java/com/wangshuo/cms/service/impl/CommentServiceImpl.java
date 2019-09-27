package com.wangshuo.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.dao.CommentMapper;
import com.wangshuo.cms.domain.Comment;
import com.wangshuo.cms.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentMapper commentMapper;

	@Override
	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.insert(comment);
	}



	@Override
	public PageInfo<Comment> selects(Integer articleId,Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list = commentMapper.selects(articleId);
		
		return new PageInfo<Comment>(list);
	}



	@Override
	public PageInfo<Comment> selectsByUserId(Integer userId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list = commentMapper.selectsByUserId(userId);
		
		return new PageInfo<Comment>(list);
	}

	
	
	
	
}

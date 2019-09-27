package com.wangshuo.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangshuo.cms.domain.Comment;

public interface CommentService {

	
	/**
	 * 
	 * @Title: selectsByUserId 
	 * @Description: 根据用户查询我的评论
	 * @param userId
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Comment>  selectsByUserId(Integer userId,Integer page ,Integer pageSize);
	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Comment>
	 */
		PageInfo<Comment> selects(Integer articleId,Integer page ,Integer pageSize);
		/**
		 * 增加评论
		 * @Title: insert 
		 * @Description: TODO
		 * @return
		 * @return: int
		 */
		int insert(Comment comment);
	
	
}

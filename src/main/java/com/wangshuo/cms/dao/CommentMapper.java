package com.wangshuo.cms.dao;

import java.util.List;

import com.wangshuo.cms.domain.Comment;
/**
 * 
 * @ClassName: CommentMapper 
 * @Description: 评论
 * @author: charles
 * @date: 2019年9月21日 上午8:30:13
 */
public interface CommentMapper {
/**
 * 
 * @Title: selects 
 * @Description: TODO
 * @return
 * @return: List<Comment>
 */
	List<Comment> selects(Integer articleId);
	/**
	 * 
	 * @Title: selectsByUserId 
	 * @Description: 根据用户查询我的评论
	 * @param userId
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> selectsByUserId(Integer userId);
	/**
	 * 增加评论
	 * @Title: insert 
	 * @Description: TODO
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);//
}

package com.wangshuo.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: AdminInterceptor 
 * @Description: 管理员后台拦截器
 * @author: 王硕
 * @date: 2019年9月18日 下午2:55:45
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断是否已经登录
		
		HttpSession session = request.getSession();
		//从session获取登录的对象
		Object object = session.getAttribute("admin");
		if(null!=object)//用户已经登录
			return true;//放行
		//没有登录.重定向到登录页面
		response.sendRedirect("/passport/login");
		
		return false;
	}

}

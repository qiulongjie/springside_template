package com.common.template.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 配置左侧菜单选中索引过滤器
 * @author qiulongjie
 *
 */
public class ActiveIndexFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		String act_index = request.getParameter("act_index");
		act_index = act_index==null?"0_1":act_index;
		//System.out.println("ActiveIndexFilter" + act_index);
		request.setAttribute("act_index",act_index);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}

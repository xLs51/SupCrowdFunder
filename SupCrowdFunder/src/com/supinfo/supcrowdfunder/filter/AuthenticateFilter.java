package com.supinfo.supcrowdfunder.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns="/auth/*")
public class AuthenticateFilter implements Filter
{
	@Override
	public void destroy() 
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		// Get the mail of the user logged
		HttpSession session = ((HttpServletRequest)request).getSession();
		String mail = (String) session.getAttribute("username");
		
		// If the mail exist, the user is logged
		if(mail != null)
			chain.doFilter(request, response);
		else
			((HttpServletResponse)response).sendRedirect("/SupCrowdFunder/");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException 
	{

	}
}
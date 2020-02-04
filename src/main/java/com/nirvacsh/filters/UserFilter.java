package com.nirvacsh.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/insert")
public class UserFilter implements Filter {

     public UserFilter() {
    	 
    }

     public void destroy() {
    	 
     }
     
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		
		if(name.length() > 3  && name.length() < 20) {
			chain.doFilter(request, response);
		} else {
			out.print("Wrong nick! Ur nickname MUST be longer than 3 char(not more than 20, u know?)");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}

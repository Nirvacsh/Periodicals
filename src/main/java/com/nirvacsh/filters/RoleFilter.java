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
public class RoleFilter implements Filter {


    public RoleFilter() {
      
    }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String role = request.getParameter("role");
		
		if(role == "admin" || role == "user") {
			chain.doFilter(request, response);
		} else {
			out.print("Role can be only admin OR user, easier than ENUM");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

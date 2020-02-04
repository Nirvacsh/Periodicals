package com.nirvacsh.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class PassWordFilter implements Filter {
		 public PassWordFilter() {
		    	 
		 }
	     public void destroy() {
	    	 
	     }
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			PrintWriter out = response.getWriter();
			String password = request.getParameter("password").toString();
			
			if(password.length() > 3  && password.length() < 20) {
				chain.doFilter(request, response);
			} else {
				out.print("Ur pass is too small! Ur password MUST be longer than 3 char(not more than 20, u know?)");
			}
		}
		public void init(FilterConfig fConfig) throws ServletException {

		}

}

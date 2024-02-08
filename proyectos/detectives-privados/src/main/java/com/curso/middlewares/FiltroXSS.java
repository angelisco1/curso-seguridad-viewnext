package com.curso.middlewares;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.curso.utils.XSSRequestWrapper;

@WebFilter("/signup")
public class FiltroXSS implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		XSSRequestWrapper xssReq = new XSSRequestWrapper(req);
		System.out.println("[+] Filtro XSS: " + xssReq);
		
		chain.doFilter(xssReq, response);
		
	}

}

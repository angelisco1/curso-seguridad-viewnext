package com.curso.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext context = sce.getServletContext();
		SessionCookieConfig scc = context.getSessionCookieConfig();
		
//		scc.setHttpOnly(false);
		scc.setPath("/");
	}

	
	
}

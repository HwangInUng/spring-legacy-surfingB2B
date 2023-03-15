package com.edu.surfing.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UploadPathListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String savePath = context.getInitParameter("savePath");
		savePath = context.getRealPath(savePath);
		context.setAttribute("savePath", savePath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}

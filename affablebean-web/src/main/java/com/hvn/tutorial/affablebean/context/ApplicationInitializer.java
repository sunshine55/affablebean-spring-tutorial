package com.hvn.tutorial.affablebean.context;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// load application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationContext.class, SpringDataContext.class, SpringSecurityContext.class, ThymeleafContext.class);

		// context loader listener
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");

		// filter with Spring Security
		servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
				.addMappingForUrlPatterns(null, false, "/*");
	}

}

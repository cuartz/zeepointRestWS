package com.zeepoint.config;

import java.nio.charset.StandardCharsets;
import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;  
  import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;  
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;  
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
  
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer//AbstractAnnotationConfigDispatcherServletInitializer 
        {
//	public void onStartup(ServletContext servletContext) throws ServletException {  
//        //AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
//        //ctx.register(AppConfig.class);  
//        ctx.setServletContext(servletContext);    
//        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
//        dynamic.addMapping("/");  
//        dynamic.setLoadOnStartup(1);  
//   }  
        
    /**
     *
     * @param registration
     */
            
          @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setInitParameter("dispatchOptionsRequest", "true");
    registration.setAsyncSupported(true);
  }
  
    @Override
  protected Class< ?>[] getRootConfigClasses() {
    return new Class< ?>[] { AppConfig.class, WebSocketConfig.class, HibernateAppConfig.class, SecurityConfig.class, JmsConfig.class};//RabbitMQConfig.class};//, MobileSecurityConfig.class  };
  }

  @Override
  protected Class< ?>[] getServletConfigClasses() {
    return new Class< ?>[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
    return new Filter[] { characterEncodingFilter };
  }
}

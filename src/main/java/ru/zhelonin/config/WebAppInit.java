package ru.zhelonin.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebConfig.class};
  }

  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}

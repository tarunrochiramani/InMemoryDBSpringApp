package com.tr.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class InMemoryRestInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {RestConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/rest" };
    }

    @Override
    protected String getServletName() {
        return "restDispatcher";
    }
}

package com.cpf.price.infrastructure.template;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.InputStream;

@Component
public class WebAppResourceProvider implements ResourceProvider, ServletContextAware {

    private ServletContext servletContext;

    private final String basePath = "/WEB-INF/templates/";

    @Override
    public void setServletContext(javax.servlet.ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public InputStream getResource(String path) {

        if (servletContext == null) {
            throw new RuntimeException("failed to get resource, you're attempting to load a web resource but you don't seem to be in a web context");
        }

        InputStream resource = servletContext.getResourceAsStream(basePath + path);

        if (resource == null) {
            throw new RuntimeException("failed to get web resource from path " + basePath + path);
        }

        return resource;
    }
}

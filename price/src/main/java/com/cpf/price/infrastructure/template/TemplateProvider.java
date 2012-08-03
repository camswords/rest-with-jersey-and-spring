package com.cpf.price.infrastructure.template;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class TemplateProvider {

    private final ResourceProvider resourceProvider;

    @Autowired
    public TemplateProvider(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    public Template getTemplate(String templateName) {

        try {
            Configuration configuration = new Configuration();
            InputStream resource = resourceProvider.getResource(templateName + ".ftl");
            freemarker.template.Template template = new freemarker.template.Template(templateName, new InputStreamReader(resource), configuration, "UTF-8");
            return new Template(template);

        } catch (IOException e) {
            throw new RuntimeException("failed to load template", e);
        }
    }
}

package com.cpf.price.infrastructure.template;

import java.io.StringWriter;
import java.util.HashMap;

public class Template {

    private final freemarker.template.Template template;
    private final HashMap<String, Object> model;

    public Template(freemarker.template.Template template) {
        this.template = template;
        this.model = new HashMap<String, Object>();
    }

    public Template put(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String render() {
        try {
            StringWriter output = new StringWriter();
            template.process(model, output);
            return output.toString();

        } catch (Exception e) {
            throw new RuntimeException("failed to render template with name " + template.getName(), e);
        }
    }
}

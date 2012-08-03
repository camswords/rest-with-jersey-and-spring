package com.cpf.price.infrastructure.template;

import java.io.InputStream;

public interface ResourceProvider {

    InputStream getResource(String path);

}

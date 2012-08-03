package com.cpf.broker.infrastructure;

import java.io.InputStream;

public interface ResourceProvider {

    InputStream getResource(String path);

}

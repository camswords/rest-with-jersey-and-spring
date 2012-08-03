package com.cpf.price.infrastructure.rest;

import com.google.common.io.CharStreams;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class RestClient {

    public HttpResponse get(String url) {
        try {
            Client client = Client.create();
            client.setConnectTimeout(20000);
            client.setReadTimeout(20000);

            ClientResponse response = client.resource(url)
                                            .accept(MediaType.APPLICATION_XML)
                                            .get(ClientResponse.class);

            String contentReceived = CharStreams.toString(new InputStreamReader(response.getEntityInputStream(), "UTF-8"));
            return new HttpResponse(response.getStatus(), contentReceived);

        } catch (IOException e) {
            throw new RuntimeException("failed to get url " + url, e);
        } catch (ClientHandlerException e) {
            throw new RuntimeException("failed to connect to server hosting url " + url, e);
        }
    }
}

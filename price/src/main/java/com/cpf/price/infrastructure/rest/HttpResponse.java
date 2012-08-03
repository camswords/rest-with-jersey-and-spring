package com.cpf.price.infrastructure.rest;

public class HttpResponse {

    private final Integer statusCode;
    private final String body;

    public HttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Boolean isNotOk() {
        return !isOk();
    }

    public boolean isOk() {
        return statusCode >= 200 && statusCode < 300;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}

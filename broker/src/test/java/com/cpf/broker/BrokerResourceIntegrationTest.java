package com.cpf.broker;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

public class BrokerResourceIntegrationTest {

    @Test
    public void shouldReturnBroker() {
        expect()
            .statusCode(200)
            .body(equalTo(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<broker>\n" +
                    "    <id>mybrokerid</id>\n" +
                    "    <interestRate>3.15</interestRate>\n" +
                    "</broker>"))
            .when()
            .get("http://localhost:8081/broker/mybrokerid");
    }

    @Test
    public void shouldReturn404WhenBrokerIdIsNotFound() {
        expect()
            .statusCode(404)
            .when()
            .get("http://localhost:8081/broker/notfound");
    }
}

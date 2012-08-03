package com.cpf.price;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class QuoteIntegrationTest {

    @Test
    public void shouldReturnAQuote() {
        expect()
            .statusCode(200)
            .body(containsString("<premium>1000.00</premium>"))
            .body(containsString("<interestRate>3.15</interestRate>"))
            .body(containsString("<interest>31.50</interest>"))
        .when()
            .get("http://localhost:8080/broker/1234/quote");
    }
}

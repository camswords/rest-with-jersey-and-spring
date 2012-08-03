package com.cpf.price.repository;

import com.cpf.price.infrastructure.rest.HttpResponse;
import com.cpf.price.infrastructure.rest.RestClient;
import com.cpf.price.infrastructure.xml.XPath;
import com.cpf.price.model.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BrokerRepository {

    private final RestClient restClient;
    private String brokerUriTemplate;

    @Autowired
    public BrokerRepository(RestClient restClient) {
        this.restClient = restClient;

        /* Note: Ideally we shouldn't be using URI templates. See Rest in Practice for a detailed explanation */
        this.brokerUriTemplate = "http://localhost:8081/broker/%ID%";
    }

    public Broker getBrokerIdentifiedBy(String id) {

        HttpResponse response = restClient.get(brokerUriTemplate.replaceAll("%ID%", id));

        if (response.isNotOk()) {
            String message = "failed to get broker identified by " + id + ". Broker service returned status code " + response.getStatusCode();
            throw new RuntimeException(message);
        }

        XPath xPath = new XPath(response.getBody());
        String brokerId = xPath.getString("/broker/id");
        BigDecimal interestRate = xPath.getBigDecimal("/broker/interestRate");

        return new Broker(brokerId, interestRate);
    }
}

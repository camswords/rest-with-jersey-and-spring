package com.cpf.broker.repository;

import com.cpf.broker.model.Broker;
import org.springframework.stereotype.Component;

@Component
public class BrokerRepository {

    public Boolean hasBrokerIdentifiedBy(String id) {
        return !"notfound".equals(id);
    }

    public Broker getBrokerIdentifiedBy(String id) {
        return new Broker(id);
    }
}

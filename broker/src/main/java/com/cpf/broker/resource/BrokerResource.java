package com.cpf.broker.resource;

import com.cpf.broker.infrastructure.Template;
import com.cpf.broker.infrastructure.TemplateProvider;
import com.cpf.broker.model.Broker;
import com.cpf.broker.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/broker/{id}")
public class BrokerResource {

    private final BrokerRepository repository;
    private final TemplateProvider templateProvider;

    @Autowired
    public BrokerResource(BrokerRepository repository, TemplateProvider templateProvider) {
        this.repository = repository;
        this.templateProvider = templateProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getBroker(@PathParam("id") String id) {

        if (!repository.hasBrokerIdentifiedBy(id)) {
            return Response.status(404).build();
        }

        Broker broker = repository.getBrokerIdentifiedBy(id);

        Template template = templateProvider.getTemplate("broker");
        template.put("id", broker.getId());
        template.put("interestRate", new PercentageView(broker.getInterestRate()).render());

        return Response.ok().entity(template.render()).build();
    }
}

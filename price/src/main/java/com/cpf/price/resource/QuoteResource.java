package com.cpf.price.resource;

import com.cpf.price.infrastructure.template.Template;
import com.cpf.price.infrastructure.template.TemplateProvider;
import com.cpf.price.model.Broker;
import com.cpf.price.model.Quote;
import com.cpf.price.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;



/*

Note that this should never be a GET.

Instead, it should look like this:

POST /quote     (passing broker id as business message)

Response:
 (status code)   302 Redirect
 (header)        Location: http://localhost:8080/quote/12345    (note 12345 would be the unique identifier of the quote you have just created)
 body is empty


The client could then do a

GET /quote/12345

to get the information about the quote.


This change would mean that the GET is idempotent and safe. This means it could be cached with a HTTP reverse proxy.
For more information read Rest in Practice
 */



@Component
@Path("/broker/{id}/quote")
public class QuoteResource {

    private final BrokerRepository repository;
    private final TemplateProvider templateProvider;

    @Autowired
    public QuoteResource(BrokerRepository repository, TemplateProvider templateProvider) {
        this.repository = repository;
        this.templateProvider = templateProvider;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getQuote(@PathParam("id") String id) {

        Broker broker = repository.getBrokerIdentifiedBy(id);
        Quote quote = new Quote(new BigDecimal("1000.00"), broker.getInterestRate());

        Template template = templateProvider.getTemplate("quote");
        template.put("id", quote.getId());
        template.put("premium", new MoneyView(quote.getPremium()).render());
        template.put("interestRate", new PercentageView(broker.getInterestRate()).render());
        template.put("interest", new MoneyView(quote.getInterest()).render());

        return Response.ok().entity(template.render()).build();
    }
}

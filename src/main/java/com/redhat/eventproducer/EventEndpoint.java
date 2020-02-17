package com.redhat.eventproducer;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/events")
@ApplicationScoped

public class EventEndpoint {


    @POST
    @Path("/txn-event/{custId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void postCase(String json,@javax.ws.rs.PathParam("custId") String customerId) {

        try {

            CustomerEvents customerEvents = new CustomerEvents();
            customerEvents.setCustId(customerId);
            customerEvents.setEvent(json);
            EventProducer.events.add(customerEvents);
            System.out.println("events"+EventProducer.events);
            EventProducer.generate();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.redhat.eventproducer;

import java.util.*;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

@Path("/events")
@ApplicationScoped
public class EventProducer {

    @POST
    @Path("/txn-event/{custId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void postCase(String json,@javax.ws.rs.PathParam("custId") String customerId) {

        try {
            events = new ArrayList<>();
           CustomerEvents customerEvents = new CustomerEvents();
            customerEvents.setCustId(customerId);
            customerEvents.setEvent(json);
            events.add(customerEvents);
            System.out.println("events"+events);
            generate();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final Logger LOG = Logger.getLogger(EventProducer.class);

    private List<CustomerEvents> events =new ArrayList<>();



    @Outgoing("event-input-stream")
    public Flowable<KafkaMessage<String, String>> generate() {
        try {
            System.out.println("inside generate" + events);
            List<KafkaMessage<String, String>> jsonVal = events.stream()
                    .map(s -> KafkaMessage.of(
                            s.getCustId(),
                            s.getEvent()))
                    .collect(Collectors.toList());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Flowable.fromIterable(jsonVal);


    }




}

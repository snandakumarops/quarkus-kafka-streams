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


@ApplicationScoped
public class EventProducer {



    private static final Logger LOG = Logger.getLogger(EventProducer.class);

    public static List<CustomerEvents> events =new ArrayList<>();



    @Outgoing("event-input-stream")
    public static Flowable<KafkaMessage<String, String>> generate() {
        System.out.println("inside generate"+events);
        List<KafkaMessage<String, String>> jsonVal = events.stream()
                .map(s -> KafkaMessage.of(
                        s.getCustId(),
                        s.getEvent()))
                .collect(Collectors.toList());

        return Flowable.fromIterable(jsonVal);


    }




}

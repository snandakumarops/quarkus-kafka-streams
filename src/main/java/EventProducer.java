package org.acme.quarkus.sample.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;

/**
 * A bean producing random temperature data every second.
 * The values are written to a Kafka topic (temperature-values).
 * Another topic contains the name of weather stations (weather-stations).
 * The Kafka configuration is specified in the application configuration.
 */
@ApplicationScoped
public class EventProducer {

    private static final Logger LOG = Logger.getLogger(ValuesGenerator.class);



    @Outgoing("event-input-stream")
    public Flowable<KafkaMessage<String, String>> generate() {


                    return KafkaMessage.of("CUST894320", "{'eventValue': 'AIRLINES', 'eventSource': 'WEBSITE'}");

    }




}

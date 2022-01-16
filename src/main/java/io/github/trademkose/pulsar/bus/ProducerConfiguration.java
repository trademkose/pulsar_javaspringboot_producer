package io.github.trademkose.pulsar.bus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.trademkose.pulsar.bus.msg.Product;
import io.github.trademkose.pulsar.constant.Serialization;
import io.github.trademkose.pulsar.producer.ProducerFactory;

@Configuration
public class ProducerConfiguration {

    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
        		.addProducer("${my.topic.producer_topic}",Product.class, Serialization.JSON);
       
    }
}

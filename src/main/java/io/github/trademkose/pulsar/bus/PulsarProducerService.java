package io.github.trademkose.pulsar.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.trademkose.pulsar.bus.msg.Product;
import io.github.trademkose.pulsar.producer.PulsarTemplate;

@Service
public class PulsarProducerService { 

	@Autowired
	private PulsarTemplate<Product> producerForProduct;

	private static final Logger LOGGER = LoggerFactory.getLogger(PulsarProducerService.class);

	public void createNewProduct(Product new_product) throws Exception {
		
		producerForProduct.send("topic-new-product", new_product);
		LOGGER.info("Producer sends this data to topic-new-product : " + new_product.toString());
	}
}

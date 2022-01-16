package io.github.trademkose.pulsar.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.trademkose.pulsar.bus.PulsarProducerService;
import io.github.trademkose.pulsar.bus.msg.Product;

@RestController
@RequestMapping(path = "/api/1.0")
public class HttpHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpHandler.class);	
	@Autowired
	private PulsarProducerService mycustomservice;
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<?> createNewProduct(@RequestBody Product new_product) throws IOException {

		LOGGER.info("[createNewProduct]: Data Received: {}", new_product.toString());
		try {
			mycustomservice.createNewProduct(new_product);
		} catch (Exception e) {
			LOGGER.error("[createNewProduct]: Exception : {}", e.toString());
			e.printStackTrace();
			return new ResponseEntity<>("{\"Success\": false,\"Message\": \"Exception."+e.toString()+ "\"}", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("{\"Success\": true,\"Message\": \"Message has been sent to Pulsar.\"}", HttpStatus.CREATED);
	}
}

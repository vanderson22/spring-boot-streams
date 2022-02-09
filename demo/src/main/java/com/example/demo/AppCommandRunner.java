package com.example.demo;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.repositories.CustomerRepo;
import com.example.demo.repositories.OrderRepo;
import com.example.demo.repositories.ProductRepository;

@Component
public class AppCommandRunner implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(AppCommandRunner.class);


	@Autowired
	private CustomerRepo customerRepos;

	@Autowired
	private OrderRepo orderRepos;

	@Autowired
	private ProductRepository productRepos;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		log.info("Customers:");
		customerRepos.findAll().forEach(c -> log.info(c.toString()));

		log.info("Orders:");
		orderRepos.findAll().forEach(o -> log.info(o.toString()));

		log.info("Products:");
		productRepos.findAll().forEach(p -> log.info(p.toString()));
	}

}

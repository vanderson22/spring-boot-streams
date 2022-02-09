package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repositories.CustomerRepo;
import com.example.demo.repositories.OrderRepo;
import com.example.demo.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

//@SpringBootTest
@Slf4j
@DataJpaTest
class DemoApplicationTests {

	Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private ProductRepository productRepo;

	 
	
	
	@Test
	@DisplayName("Obtain a list of product with category = \"Books\" and price > 100")
	public void exercise1() {
		 
		    List<Product> list = productRepo
		              .findAll()
		    		  .stream()
		    		  .filter( p -> p.getCategory().equals("Books")  )
		    		  .filter(p -> p.getPrice() > 100)
		    		  .collect(Collectors.toList());
		    
		    list.forEach( p ->  log.info(p.toString()));
		
		  
	}
	
	@Test
	@DisplayName("Exercise 2 — Obtain a list of order with products belong to category “Baby”")
	public void exercise2() {
		 
		    List<Order> list = orderRepo
		              .findAll()
		    		  .stream()
		    		  .filter( o ->  
		    		            o.getProducts().stream().anyMatch( p -> p.getCategory().equalsIgnoreCase("Baby") )
		    		    ) 
		    		  
		    		  .collect(Collectors.toList());
		    
		    list.forEach( p ->   p.getProducts().forEach( x ->  log.info(x.toString() ) ) );
		
		  
	}
	
	
	@Test
	@DisplayName("Exercise 3 — Obtain a list of product with category = “Toys” and then apply 10% discount")
	public void exercise3() {
		

	    List<Product> list = productRepo
	              .findAll()
	    		  .stream()
	    		  .filter( p -> p.getCategory().equals("Toys")  )
	    		  .map(x -> {x.setPrice(  x.getPrice() - x.getPrice() * 0.10  ) ; return x ;  }) 
	    		  .collect(Collectors.toList());
	    
	    list.forEach( p ->  log.info(p.toString()));
	}
	
	@Test
	@DisplayName("Exercise 4 — btain a list of products ordered by customer of tier 2 between 01-Feb-2021 and 01-Apr-2021")
	public void exercise4() {
		
		List<Order> list = orderRepo
	              .findAll()
	    		  .stream()
	    		  .filter(o->o.getCustomer().getTier().equals(2))
	    		  .filter(o-> {
	    			    return  o.getDeliveryDate().isAfter(LocalDate.of(2021, 1, 01) )  &&   o.getDeliveryDate().isBefore(LocalDate.of(2021, 4, 02) )  ; 
	    			    } )
	    		  
//	    		  o.getDeliveryDate().isbe(LocalDate.of(2021, 2, 01) ) &&   ; })
	    		  .collect(Collectors.toList());
	    		  
		       list.forEach( p ->  log.info(p.getDeliveryDate().toString()  ) );
		       
		       
		       
			}	
	}
 

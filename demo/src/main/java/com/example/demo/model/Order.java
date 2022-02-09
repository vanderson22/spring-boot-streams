package com.example.demo.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//@Data - removido por causa do hashCode failSAFE
@NoArgsConstructor
@Entity
@Table(name = "product_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private LocalDate orderDate;
  private LocalDate deliveryDate;
  private String status;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  @ManyToMany
  @JoinTable(
      name = "order_product_relationship",
      joinColumns = { @JoinColumn(name = "order_id") },
      inverseJoinColumns = { @JoinColumn(name = "product_id") }
  )
  @ToString.Exclude
  Set<Product> products;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public LocalDate getOrderDate() {
	return orderDate;
}

public void setOrderDate(LocalDate orderDate) {
	this.orderDate = orderDate;
}

public LocalDate getDeliveryDate() {
	return deliveryDate;
}

public void setDeliveryDate(LocalDate deliveryDate) {
	this.deliveryDate = deliveryDate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Customer getCustomer() {
	return customer;
}

public void setCustomer(Customer customer) {
	this.customer = customer;
}

public Set<Product> getProducts() {
	return products;
}

public void setProducts(Set<Product> products) {
	this.products = products;
}
    
  
  
}

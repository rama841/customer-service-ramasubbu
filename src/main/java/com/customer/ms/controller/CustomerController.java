package com.customer.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.ms.dao.CustomerDAO;
import com.customer.ms.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Greetings from SpringBoot 1.0";
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomers() {
		List<Customer> list = customerDAO.getAllCustomers();
		return list;
		
	}
	
	@RequestMapping(value = "/customer/{cusId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Customer getCustomer(@PathVariable("cusId") String cusId) {
		Customer cust = customerDAO.getCustomer(cusId);
		return cust;
		
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer cust = customerDAO.addCustomer(customer);
		return cust;
	}
	
	
	@RequestMapping(value = "/customer/{cusId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("cusId") String cusId) {
		Customer c = customerDAO.getCustomer(cusId);
		c.setCustId(cusId);
		c.setCustName(customer.getCustName());
		c.setAddress(customer.getAddress());
		Customer cust = customerDAO.updateCustomer(c);
		return cust;
	}

	
	@RequestMapping(value = "/customer/{cusId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public Customer deleteCustomer(@PathVariable("cusId") String cusId) {
		Customer cust = customerDAO.deleteCustomer(cusId);
		return cust;
	}
	

	
}

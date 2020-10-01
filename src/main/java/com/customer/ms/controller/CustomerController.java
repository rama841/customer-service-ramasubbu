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
import com.customer.ms.model.CustomerM;
import com.customer.ms.repository.CustomerRepository;
import com.customer.ms.service.CustomerService;


@RestController
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private CustomerService customerService;
	
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
	
	@RequestMapping(value = "/mongoCustomers", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<CustomerM> getMongoCustomers() {
		List<CustomerM> list = customerService.findAll();
		return list;
	}
	
	@RequestMapping(value = "/mongoCustomer/{cusId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerM getMongoCustomer(@PathVariable("cusId") String cusId) {
		CustomerM cust = customerService.findById(cusId);
		return cust;
		
	}
	
	@RequestMapping(value = "/mongoCustomer", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerM addMongoCustomer(@RequestBody CustomerM customerM) {
		CustomerM cust = customerService.addCustomer(customerM);
		return cust;
	}
	
	@RequestMapping(value = "/mongoCustomer/{cusId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
	public CustomerM updateMongoCustomer(@RequestBody CustomerM customerM, @PathVariable("cusId") String cusId) {
		CustomerM c = customerService.findById(cusId);
		c.setCusId(customerM.getCusId());
		c.setCusName(customerM.getCusName());
		c.setAddress(customerM.getAddress());
		CustomerM custM = customerService.updateCustomer(c);
		return custM;
	}

	
	@RequestMapping(value = "/mongoCustomer/{cusId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteMongoCustomer(@PathVariable("cusId") String cusId) {
		customerService.deleteCustomer(cusId);
	}
	
}

package com.customer.ms.dao;

import java.util.*;
import com.customer.ms.model.*;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

	private static final Map<String, Customer> cusMap = new HashMap<String, Customer>();
	
	static {
		 initCust();
	}
	
	private static void initCust() {
		
		Customer cust1 = new Customer("C01", "Smith", "NY");
		Customer cust2 = new Customer("C02", "John", "CT");
		Customer cust3 = new Customer("C03", "Ethan", "CA");
		
		cusMap.put(cust1.getCustId(), cust1);
		cusMap.put(cust2.getCustId(), cust2);
		cusMap.put(cust3.getCustId(), cust3);
		
	}

	public Customer getCustomer(String cusId) {
		
		return cusMap.get(cusId);
		
	}
	
	public List<Customer> getAllCustomers() {
		
		Collection<Customer> c = cusMap.values();
		List<Customer> list = new ArrayList<Customer>();
		list.addAll(c);
		return list;
		
	}
	
	public Customer addCustomer(Customer cus) {
		cusMap.put(cus.getCustId(), cus);
		return cus;
	}
	
	public Customer updateCustomer(Customer cus) {
		cusMap.put(cus.getCustId(), cus);
		return cus;
	}

	public Customer deleteCustomer(String cusId) {
		Customer cus = cusMap.get(cusId);
		cusMap.remove(cusId);
		return cus;
	}
	
	

}

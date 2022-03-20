package com.greatlearning.customerMgmt.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.greatlearning.customerMgmt.entity.Customer;

@Service
public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int id);

	public void save(Customer theCustomer);

	public void deleteById(int theId);
}

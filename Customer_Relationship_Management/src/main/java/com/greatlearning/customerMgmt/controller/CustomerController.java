package com.greatlearning.customerMgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.customerMgmt.entity.Customer;
import com.greatlearning.customerMgmt.service.CustomerService;

@Controller
@RequestMapping("/crm")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		List<Customer> customers = customerService.findAll();
		theModel.addAttribute("Customers", customers);
		return "list-Customers";
	}

	@RequestMapping("/add")
	public String showFormForAdd(Model theModel) {
		Customer customer = new Customer();
		theModel.addAttribute("Customer", customer);
		return "Customer-form";

	}

	@RequestMapping("/update")
	public String showFormForUpdate(@RequestParam("id") int id, Model theModel) {
		Customer theCustomer = customerService.findById(id);
		theModel.addAttribute("Customer", theCustomer);
		return "Customer-form";

	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName, @RequestParam("email") String email) {
		System.out.println("in save contoller " + id);
		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
			System.out.println(theCustomer);
		} else {
			theCustomer = new Customer(firstName, lastName, email);
		}
		// save the student
		customerService.save(theCustomer);

		return "redirect:/crm/list";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

		// delete the student
		customerService.deleteById(theId);

		// redirect to /student/list
		return "redirect:/crm/list";

	}

}

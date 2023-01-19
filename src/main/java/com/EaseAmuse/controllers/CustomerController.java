package com.EaseAmuse.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;
import com.EaseAmuse.services.CustomerServices;



@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private CustomerServices customerServices;
	
	@PostMapping("/customer")
	public ResponseEntity<CustomerOutputDto> registerCustomer(@Valid @RequestBody CustomerInputDto customerDTO){
		
		return new ResponseEntity<CustomerOutputDto>(this.customerServices.registerCustomer(customerDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerOutputDto> getCustomerById(@PathVariable("customerId") Integer customerId){
		
		return new ResponseEntity<CustomerOutputDto>(this.customerServices.getCustomerById(customerId), HttpStatus.FOUND);
	}
	
	@PutMapping("/customer/{customerId}")
	public ResponseEntity<CustomerOutputDto> updateCustomer(@Valid @RequestBody CustomerInputDto customerInputDTO, @PathVariable("customerId") Integer customerId){
		
		return new ResponseEntity<CustomerOutputDto>(this.customerServices.updateCustomer(customerId, customerInputDTO), HttpStatus.OK);
	}
	
	@DeleteMapping("customer/{customerId}")
	public ResponseEntity<CustomerOutputDto> deleteCustomer(@Valid @RequestBody CustomerInputDto customerInputDTO, @PathVariable("customerId") Integer customerId){
		
		return new ResponseEntity<CustomerOutputDto>(this.customerServices.deleteCustomer(customerId), HttpStatus.OK);
	}
	
	@GetMapping("customer")
	public ResponseEntity<List<CustomerOutputDto>> getCustomerDetails(){
		
		
		return new ResponseEntity<List<CustomerOutputDto>>(this.customerServices.getCustomersDetails(), HttpStatus.OK);
		
	}
}

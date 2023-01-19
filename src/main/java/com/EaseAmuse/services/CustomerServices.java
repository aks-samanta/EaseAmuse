package com.EaseAmuse.services;

import java.util.List;

import com.EaseAmuse.exceptions.CustomerException;
import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;

public interface CustomerServices {
	
	CustomerOutputDto registerCustomer(CustomerInputDto customerDTO);
	
	CustomerOutputDto getCustomerById(Integer customerId) throws CustomerException;
	
	CustomerOutputDto updateCustomer(Integer customerId, CustomerInputDto customerDTO) throws CustomerException;
	
	CustomerOutputDto deleteCustomer(Integer customerId) throws CustomerException;
	
	List<CustomerOutputDto> getCustomersDetails() throws CustomerException;
	
	
	

}

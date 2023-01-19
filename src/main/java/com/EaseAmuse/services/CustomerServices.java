package com.EaseAmuse.services;

import com.EaseAmuse.exceptions.CustomerException;
import com.EaseAmuse.payloads.CustomerInputDTO;
import com.EaseAmuse.payloads.CustomerOutputDTO;

public interface CustomerServices {

	
	CustomerOutputDTO registerCustomer(CustomerInputDTO customerDTO);

	CustomerOutputDTO getCustomerById(Integer customerId) throws CustomerException;
	
	CustomerOutputDTO updateCustomer(Integer customerId, CustomerInputDTO customerDTO) throws CustomerException;
	
	CustomerOutputDTO deleteCustomer(Integer customerId) throws CustomerException;
}

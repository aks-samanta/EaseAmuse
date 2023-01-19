package com.EaseAmuse.services;

import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;
import com.EaseAmuse.exceptions.CustomerException;

public interface CustomerServices {

	CustomerOutputDto registerCustomer(CustomerInputDto customerDTO);

	CustomerOutputDto getCustomerById(Integer customerId) throws CustomerException;

	CustomerOutputDto updateCustomer(Integer customerId, CustomerInputDto customerDTO) throws CustomerException;

	CustomerOutputDto deleteCustomer(Integer customerId) throws CustomerException;
}

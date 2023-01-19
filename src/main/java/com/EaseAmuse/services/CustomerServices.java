package com.EaseAmuse.services;

import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;

public interface CustomerServices {

	
	CustomerOutputDto registerCustomer(CustomerInputDto customerDTO);

	CustomerOutputDto getCustomerById(Integer customerId);

}

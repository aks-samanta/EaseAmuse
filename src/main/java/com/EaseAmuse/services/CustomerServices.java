package com.EaseAmuse.services;

import com.EaseAmuse.payloads.CustomerInputDTO;
import com.EaseAmuse.payloads.CustomerOutputDTO;

public interface CustomerServices {

	CustomerOutputDTO registerCustomer(CustomerInputDTO customerDTO);

	CustomerOutputDTO getCustomerById(Integer customerId);

}

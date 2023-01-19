package com.EaseAmuse.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.CustomerException;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.payloads.CustomerInputDTO;
import com.EaseAmuse.payloads.CustomerOutputDTO;
import com.EaseAmuse.repositories.CustomerRepo;

@Service
public class CustomerServicesImpl implements CustomerServices{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public CustomerOutputDTO registerCustomer(CustomerInputDTO customerDTO) {
		
		Customer customer =	this.modelMapper.map(customerDTO, Customer.class);
		
		Customer savedCustomer =  this.customerRepo.save(customer);
		
		return this.modelMapper.map(savedCustomer, CustomerOutputDTO.class);
	}

	@Override
	public CustomerOutputDTO getCustomerById(Integer customerId) {
		
		Customer foundCustomer =  this.customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("cutomer Not ound"));
		
		return this.modelMapper.map(foundCustomer, CustomerOutputDTO.class);
		
	}

	@Override
	public CustomerOutputDTO updateCustomer(Integer customerId, CustomerInputDTO customerDTO) throws CustomerException {
		// TODO Auto-generated method stub
		Customer foundCustomer =  this.customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("customer not found"));
		
		foundCustomer.setCustomerName(customerDTO.getCustomerName());
		foundCustomer.setEmail(customerDTO.getEmail());
		foundCustomer.setMobile(customerDTO.getMobile());
		foundCustomer.setPassword(customerDTO.getPassword());
		
		Customer updatedCustomer = this.customerRepo.save(foundCustomer);
		
		return this.modelMapper.map(updatedCustomer, CustomerOutputDTO.class);
	}

	@Override
	public CustomerOutputDTO deleteCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	

}

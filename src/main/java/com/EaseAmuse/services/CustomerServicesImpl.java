package com.EaseAmuse.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.CustomerException;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;
import com.EaseAmuse.repositories.CustomerRepo;

@Service
public class CustomerServicesImpl implements CustomerServices {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public CustomerOutputDto registerCustomer(CustomerInputDto customerDTO) {

		Customer customer = this.modelMapper.map(customerDTO, Customer.class);

		Customer savedCustomer = this.customerRepo.save(customer);

		return this.modelMapper.map(savedCustomer, CustomerOutputDto.class);
	}

	@Override
	public CustomerOutputDto getCustomerById(Integer customerId) {

		Customer foundCustomer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("cutomer Not ound"));

		return this.modelMapper.map(foundCustomer, CustomerOutputDto.class);

	}

	@Override
	public CustomerOutputDto updateCustomer(Integer customerId, CustomerInputDto customerDTO) throws CustomerException {
		// TODO Auto-generated method stub
		Customer foundCustomer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("customer not found"));

		foundCustomer.setName(customerDTO.getCustomerName());
		foundCustomer.setEmail(customerDTO.getEmail());
		foundCustomer.setMobile(customerDTO.getMobile());
		foundCustomer.setPassword(customerDTO.getPassword());

		Customer updatedCustomer = this.customerRepo.save(foundCustomer);

		return this.modelMapper.map(updatedCustomer, CustomerOutputDto.class);

	}

	@Override
	public CustomerOutputDto deleteCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		Customer foundCustomer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerException("customer not found"));

		this.customerRepo.delete(foundCustomer);
		return this.modelMapper.map(foundCustomer, CustomerOutputDto.class);
	}

	@Override
	public List<CustomerOutputDto> getCustomersDetails() throws CustomerException {
		// TODO Auto-generated method stub
		List<Customer> lc = this.customerRepo.findAll();

		if (lc.isEmpty()) {
			throw new CustomerException("no customer available.");
		}

		List<CustomerOutputDto> listOfDtos = new ArrayList<>();
		for (Customer customer : lc) {
			listOfDtos.add(new CustomerOutputDto(customer.getCustomerId(), customer.getName(),
					customer.getEmail(), customer.getMobile()));
		}

		return listOfDtos;
		// return this.modelMapper.map(listOfDtos, CustomerOutputDTO.class);
	}

}

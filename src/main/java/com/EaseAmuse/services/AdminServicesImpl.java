package com.EaseAmuse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.repositories.AdminRepo;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	AdminRepo adminRepo;
	
	
	

}

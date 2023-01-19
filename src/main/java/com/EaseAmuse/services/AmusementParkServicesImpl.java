package com.EaseAmuse.services;

import java.util.List;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;

public class AmusementParkServicesImpl implements AmusementParkServices {

	@Override
	public AmusementParkOutputDto createAmusementPark(AmusementParkInputDto ParkDto) {
		
		
		
		return null;
	}

	@Override
	public AmusementParkOutputDto getAmusementParkById(Integer parkId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmusementParkOutputDto> getAmusementParksByCity(String city) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AmusementParkOutputDto> getAllAmusementParks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmusementParkOutputDto updateAmusementPark(Integer managerId, Integer parkId, AmusementParkInputDto parkDto)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmusementParkOutputDto removeAmusementpark(Integer parkId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}

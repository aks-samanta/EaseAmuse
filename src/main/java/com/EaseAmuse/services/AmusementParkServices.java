package com.EaseAmuse.services;

import java.util.List;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;

public interface AmusementParkServices {

	// create
	AmusementParkOutputDto createAmusementPark(AmusementParkInputDto ParkDto);

	// readById
	AmusementParkOutputDto getAmusementParkById(Integer parkId) throws ResourceNotFoundException;

	// readByCity
	List<AmusementParkOutputDto> getAmusementParksByCity(String city) throws ResourceNotFoundException;

	// readAll
	List<AmusementParkOutputDto> getAllAmusementParks();

	// update
	AmusementParkOutputDto updateAmusementPark(Integer managerId, Integer parkId, AmusementParkInputDto parkDto)throws ResourceNotFoundException;

	// delete
	AmusementParkOutputDto removeAmusementpark(Integer parkId) throws ResourceNotFoundException;
}

package com.EaseAmuse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.repositories.AmusementParkRepo;
import com.EaseAmuse.repositories.ManagerRepo;

public class AmusementParkServicesImpl implements AmusementParkServices {

	@Autowired
	private ManagerRepo managerRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AmusementParkRepo amusementParkRepo;

	@Override
	public AmusementParkOutputDto createAmusementPark(AmusementParkInputDto ParkDto) {

		Manager manager = this.managerRepo.findById(ParkDto.getManagerId()).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "customerId", ParkDto.getManagerId().toString()));
		AmusementPark park = this.modelMapper.map(ParkDto, AmusementPark.class);

		park.setManager(manager);
		manager.setAmusementPark(park);

		this.amusementParkRepo.save(park);
		this.managerRepo.save(manager);

		return this.modelMapper.map(park, AmusementParkOutputDto.class);
	}

	@Override
	public AmusementParkOutputDto getAmusementParkById(Integer parkId) throws ResourceNotFoundException {

		AmusementPark park = this.amusementParkRepo.findById(parkId)
				.orElseThrow(() -> new ResourceNotFoundException("Amusement Park", "Park Id", parkId.toString()));
		return this.modelMapper.map(park, AmusementParkOutputDto.class);
	}

	@Override
	public List<AmusementParkOutputDto> getAmusementParksByCity(String city) throws ResourceNotFoundException {

		List<AmusementPark> parks = this.amusementParkRepo.findByCity(city)
				.orElseThrow(() -> new ResourceNotFoundException("Amusemement Park", "City", city));

		List<AmusementParkOutputDto> parkDtos = parks.stream()
				.map(park -> this.modelMapper.map(park, AmusementParkOutputDto.class)).collect(Collectors.toList());

		return parkDtos;
	}

	@Override
	public List<AmusementParkOutputDto> getAllAmusementParks() {
		
		List<AmusementPark> parks = this.amusementParkRepo.findAll();

		List<AmusementParkOutputDto> parkDtos = parks.stream()
				.map(park -> this.modelMapper.map(park, AmusementParkOutputDto.class)).collect(Collectors.toList());

		return parkDtos;
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

package com.EaseAmuse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.exceptions.UnauthorisedException;
import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.repositories.AmusementParkRepo;
import com.EaseAmuse.repositories.ManagerRepo;

@Service
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

		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Manager Id", managerId.toString()));

		AmusementPark park = this.amusementParkRepo.findById(parkId)
				.orElseThrow(() -> new ResourceNotFoundException("Amusement Park", "Park Id", parkId.toString()));

		if (manager.getAmusementPark().getParkId() == park.getParkId()) {
			park.setCity(parkDto.getCity());
			park.setName(parkDto.getName());
			AmusementPark updatedPark = this.amusementParkRepo.save(park);
			return this.modelMapper.map(updatedPark, AmusementParkOutputDto.class);

		} else {
			throw new UnauthorisedException("You Are Not the manager of this Park so you cannot update this Park!");
		}

	}

	@Override
	public AmusementParkOutputDto removeAmusementpark(Integer parkId) throws ResourceNotFoundException {
		AmusementPark park = this.amusementParkRepo.findById(parkId)
				.orElseThrow(() -> new ResourceNotFoundException("Amusement Park", "Park Id", parkId.toString()));

		this.amusementParkRepo.delete(park);

		return this.modelMapper.map(park, AmusementParkOutputDto.class);

	}

}

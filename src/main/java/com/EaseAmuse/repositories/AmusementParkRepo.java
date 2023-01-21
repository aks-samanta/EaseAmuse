package com.EaseAmuse.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.AmusementPark;

public interface AmusementParkRepo extends JpaRepository<AmusementPark, Integer> {

	Optional<List<AmusementPark>> findByCity(String city);

}

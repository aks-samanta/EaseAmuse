package com.EaseAmuse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.AmusementPark;

public interface AmusementParkRepo extends JpaRepository<AmusementPark, Integer> {

	List<AmusementPark> findByCity(String city);

}

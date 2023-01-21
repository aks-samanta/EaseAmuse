package com.EaseAmuse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.Activity;
import com.EaseAmuse.models.AmusementPark;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {

	List<Activity> findByCharges(Double charges);

	List<Activity> findByAmusementPark(AmusementPark amusementPark);
}

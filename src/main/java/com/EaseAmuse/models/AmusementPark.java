package com.EaseAmuse.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AmusementPark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parkId;
	private String parkName;
	private String city;
	@OneToOne
	@JoinColumn(name = "managerId")
	private Manager manager;

	@OneToMany(mappedBy = "amusementPark", cascade = CascadeType.ALL)
	private List<Activity> activities = new ArrayList<>();

	@OneToMany(mappedBy = "amusementPark", cascade = CascadeType.ALL)
	private List<DailyActivity> dailyActivities = new ArrayList<>();
}

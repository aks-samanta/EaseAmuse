package com.EaseAmuse.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityId;
	private String name;
	private String description;
	private Double charges;

	@ManyToOne
	@JoinColumn(name = "parkId")
	private AmusementPark amusementPark;

	@OneToMany(mappedBy = "activity", cascade = CascadeType.MERGE)
	private List<DailyActivity> dailyActivities = new ArrayList<>();
}

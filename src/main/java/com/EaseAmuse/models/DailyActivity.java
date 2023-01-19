package com.EaseAmuse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DailyActivities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dailyActivityId;
	private Integer slotsRemaining;
	private Date activityDate;

	@ManyToOne
	@JoinColumn(name = "activityId")
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "parkId")
	private AmusementPark amusementPark;

	@OneToMany(mappedBy = "dailyActivity", cascade = CascadeType.ALL)
	private List<Ticket> tickets = new ArrayList<>();

}

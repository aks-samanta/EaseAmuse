package com.EaseAmuse.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer managerId;
	private String mangerName;
	private String mangerPassword;
	private String mangerEmail;
	private String mangerMobile;
	
	@OneToOne
	private AmusementPark amusementPark;

	
}

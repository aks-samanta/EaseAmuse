package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AmusementParkInputDto {

	private String name;
	private String city;
	private Integer managerId;
}

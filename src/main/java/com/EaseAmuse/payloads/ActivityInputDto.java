package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityInputDto {
	private String name;
	private String description;
	private Double charges;
}

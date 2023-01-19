package com.EaseAmuse.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MyErrorDetails {

	private String message;
	private boolean success;
	private LocalDateTime timestamp;
}

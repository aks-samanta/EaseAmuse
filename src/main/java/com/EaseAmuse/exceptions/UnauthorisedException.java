package com.EaseAmuse.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorisedException extends RuntimeException {

	public UnauthorisedException() {

	}

	public UnauthorisedException(String message) {
		super(message);

	}

}

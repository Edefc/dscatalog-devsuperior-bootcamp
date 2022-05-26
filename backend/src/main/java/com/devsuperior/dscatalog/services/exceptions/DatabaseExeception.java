package com.devsuperior.dscatalog.services.exceptions;

public class DatabaseExeception extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseExeception(String msg) {
		super(msg);
	}

}

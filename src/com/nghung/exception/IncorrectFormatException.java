/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.exception;

public class IncorrectFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectFormatException() {
		super();
	}

	public IncorrectFormatException(String message) {
		super(message);
	}
}

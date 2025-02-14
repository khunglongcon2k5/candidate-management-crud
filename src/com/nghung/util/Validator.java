/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validator {
	public static boolean isValidDateOfBirth(String dateOfBirth) {
		try {
			LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("^[0-9]{7,}$");
	}
	
	public static boolean isValidEmailAddress(String emailAddress) {
		return emailAddress.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+(?<!\\.)\\.[A-Za-z]{2,}$");
	}
	
	public static boolean isValidYearsOfExperience(double yearsOfExperience) {
		double yoe = Math.round(yearsOfExperience * 10.0) / 10.0;
		return (yoe < 100 && yoe > 0);
	}
}

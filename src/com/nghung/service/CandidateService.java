/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.service;

import java.util.HashSet;
import java.util.Set;

import com.nghung.exception.IncorrectFormatException;
import com.nghung.model.Candidate;
import com.nghung.util.IncorrectDataType;
import com.nghung.util.Validator;

public abstract class CandidateService {
	public Set<IncorrectDataType> isValidCandidateData(String data) {
		String[] fields = data.split(", ");
		Set<IncorrectDataType> result = new HashSet<>();
		
		if (!Validator.isValidDateOfBirth(fields[2])) {
			result.add(IncorrectDataType.DATE_OF_BIRTH_FORMAT);
		}
		
		if (!Validator.isValidPhoneNumber(fields[5])) {
			result.add(IncorrectDataType.PHONE_NUMBER_FORMAT);
		}
		
		if (!Validator.isValidEmailAddress(fields[6])) {
			result.add(IncorrectDataType.EMAIL_ADDRESS_FORMAT);
		}
		
		return result;
	}
	
	public abstract Candidate createCandidate(String data) throws IncorrectFormatException;
}

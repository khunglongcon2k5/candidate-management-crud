/*
 * 
 * @author NguyenHung
 * @date Jan 5, 2025
 * @version 1.0
 *
 */

package com.nghung.service;

import java.util.Set;

import com.nghung.exception.IncorrectFormatException;
import com.nghung.model.Candidate;
import com.nghung.model.InternCandidate;
import com.nghung.util.IncorrectDataType;

public class InternCandidateService extends CandidateService {
	public InternCandidateService() {
		
	}
	
	@Override
	public Set<IncorrectDataType> isValidCandidateData(String data) {
		String[] fields = data.split(", ");
		Set<IncorrectDataType> result = super.isValidCandidateData(data);
		
		for (int i = 7; i <= 12; i++) {
			if (!fields[i].equalsIgnoreCase("NONE")) {
				result.add(IncorrectDataType.EXCESS_DATA);
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public Candidate createCandidate(String data) throws IncorrectFormatException {
		if (!isValidCandidateData(data).isEmpty()) {
			throw new IncorrectFormatException();
		}
		
		String[] fields = data.split(", ");
		
		InternCandidate internCandidate = new InternCandidate();
		internCandidate.setfullName(fields[1]);
		internCandidate.setDateOfBirth(fields[2]);
		internCandidate.setAddress(fields[3]);
		internCandidate.setHometown(fields[4]);
		internCandidate.setPhoneNumber(fields[5]);
		internCandidate.setEmailAddress(fields[6]);
		internCandidate.setMajor(fields[13]);
		internCandidate.setCurrentSemester(Integer.parseInt(fields[14]));
		internCandidate.setSchoolName(fields[15]);
		internCandidate.setExpectedGraduationDate(fields[16]);
		return internCandidate;
	}
}

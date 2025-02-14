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
import com.nghung.model.FresherCandidate;
import com.nghung.util.IncorrectDataType;

public class FresherCandidateService extends CandidateService {
	public FresherCandidateService() {
		
	}

	@Override
	public Set<IncorrectDataType> isValidCandidateData(String data) {
		String[] fields = data.split(", ");
		Set<IncorrectDataType> result = super.isValidCandidateData(data);
		
		for (int i = 7; i <= 16; i++) {
			if (i == 10) {
				i = 13;
			}
			
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
		
		FresherCandidate fresherCandidate = new FresherCandidate();
		fresherCandidate.setfullName(fields[1]);
		fresherCandidate.setDateOfBirth(fields[2]);
		fresherCandidate.setAddress(fields[3]);
		fresherCandidate.setHometown(fields[4]);
		fresherCandidate.setPhoneNumber(fields[5]);
		fresherCandidate.setEmailAddress(fields[6]);
		fresherCandidate.setGraduationDate(fields[10]);
		fresherCandidate.setGraduationRank(fields[11]);
		fresherCandidate.setGraduationSchool(fields[12]);
		return fresherCandidate;
	}
}

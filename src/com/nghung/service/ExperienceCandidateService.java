/*
 * 
 * @author NguyenHung
 * @date Jan 4, 2025
 * @version 1.0
 *
 */

package com.nghung.service;

import java.util.Set;

import com.nghung.exception.IncorrectFormatException;
import com.nghung.model.Candidate;
import com.nghung.model.ExperienceCandidate;
import com.nghung.util.IncorrectDataType;
import com.nghung.util.Validator;

public class ExperienceCandidateService extends CandidateService {
	public ExperienceCandidateService() {
		super();
	}
	
	@Override
	public Set<IncorrectDataType> isValidCandidateData(String data) {
		String[] fields = data.split(", ");
		Set<IncorrectDataType> result = super.isValidCandidateData(data);
		
		if (!Validator.isValidYearsOfExperience(Double.parseDouble(fields[7]))) {
			result.add(IncorrectDataType.YEARS_OF_EXPERIENCE_FORMAT);
		}
		
		for (int i = 10; i <= 16; i++) {
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
		
		ExperienceCandidate exp = new ExperienceCandidate();
		exp.setfullName(fields[1]);
		exp.setDateOfBirth(fields[2]);
		exp.setAddress(fields[3]);
		exp.setHometown(fields[4]);
		exp.setPhoneNumber(fields[5]);
		exp.setEmailAddress(fields[6]);
		exp.setYearsOfExperience(Double.parseDouble(fields[7]));
		exp.setSpecializedSkills(fields[8]);
		exp.setLastWorkplace(fields[9]);
		return exp;
	}
}

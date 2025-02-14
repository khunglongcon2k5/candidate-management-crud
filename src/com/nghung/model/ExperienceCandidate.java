/*
 * 
 * @author NguyenHung
 * @date Jan 4, 2025
 * @version 1.0
 *
 */

package com.nghung.model;

public class ExperienceCandidate extends Candidate {
	private double yearsOfExperience;
	private String specializedSkills;
	private String lastWorkplace;
	
	public ExperienceCandidate(String fullName, String dateOfBirth, String address, String hometown, String phoneNumber,
			String emailAddress, double yearsOfExperience, String specializedSkills, String lastWorkplace) {
		super(fullName, dateOfBirth, address, hometown, phoneNumber, emailAddress);
		this.yearsOfExperience = yearsOfExperience;
		this.specializedSkills = specializedSkills;
		this.lastWorkplace = lastWorkplace;
	}
	
	public ExperienceCandidate() {
		
	}

	/**
	 * @return the yearsOfExperience
	 */
	public double getYearsOfExperience() {
		return yearsOfExperience;
	}

	/**
	 * @param yearsOfExperience the yearsOfExperience to set
	 */
	public void setYearsOfExperience(double yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	/**
	 * @return the specializedSkills
	 */
	public String getSpecializedSkills() {
		return specializedSkills;
	}

	/**
	 * @param specializedSkills the specializedSkills to set
	 */
	public void setSpecializedSkills(String specializedSkills) {
		this.specializedSkills = specializedSkills;
	}

	/**
	 * @return the lastWorkplace
	 */
	public String getLastWorkplace() {
		return lastWorkplace;
	}

	/**
	 * @param lastWorkplace the lastWorkplace to set
	 */
	public void setLastWorkplace(String lastWorkplace) {
		this.lastWorkplace = lastWorkplace;
	}
}

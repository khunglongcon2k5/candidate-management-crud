/*
 * 
 * @author NguyenHung
 * @date Jan 4, 2025
 * @version 1.0
 *
 */

package com.nghung.model;

public class FresherCandidate extends Candidate {
	private String graduationDate;
	private String graduationRank;
	private String graduationSchool;
	
	public FresherCandidate(String name, String dateOfBirth, String address, String hometown, String phoneNumber,
			String emailAddress, String graduationDate, String graduationRank, String graduationSchool) {
		super(name, dateOfBirth, address, hometown, phoneNumber, emailAddress);
		this.graduationDate = graduationDate;
		this.graduationRank = graduationRank;
		this.graduationSchool = graduationSchool;
	}
	
	public FresherCandidate() {
		
	}
	
	/**
	 * @return the graduationDate
	 */
	public String getGraduationDate() {
		return graduationDate;
	}

	/**
	 * @param graduationDate the graduationDate to set
	 */
	public void setGraduationDate(String graduationDate) {
		this.graduationDate= graduationDate;
	}

	/**
	 * @return the graduationRank
	 */
	public String getGraduationRank() {
		return graduationRank;
	}

	/**
	 * @param graduationRank the graduationRank to set
	 */
	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}

	/**
	 * @return the graduationSchool
	 */
	public String getGraduationSchool() {
		return graduationSchool;
	}

	/**
	 * @param graduationSchool the graduationSchool to set
	 */
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}
}


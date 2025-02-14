/*
 * 
 * @author NguyenHung
 * @date Jan 4, 2025
 * @version 1.0
 *
 */

package com.nghung.model;

public class InternCandidate extends Candidate {
	private String major;
	private int currentSemester;
	private String schoolName;
	private String expectedGraduationDate;
	
	public InternCandidate(String name, String dateOfBirth, String address, String hometown, String phoneNumber,
			String emailAddress, String major, int currentSemester, String schoolName, String expectedGraduationDate) {
		super(name, dateOfBirth, address, hometown, phoneNumber, emailAddress);
		this.major = major;
		this.currentSemester = currentSemester;
		this.schoolName = schoolName;
		this.expectedGraduationDate = expectedGraduationDate;
	}
	
	public InternCandidate() {
		
	}
	
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return the semester
	 */
	public int getCurrentSemester() {
		return currentSemester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setCurrentSemester(int semester) {
		this.currentSemester = semester;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return the expectedGraduationDate
	 */
	public String getExpectedGraduationDate() {
		return expectedGraduationDate;
	}

	/**
	 * @param expectedGraduationDate the expectedGraduationDate to set
	 */
	public void setExpectedGraduationDate(String expectedGraduationDate) {
		this.expectedGraduationDate = expectedGraduationDate;
	}
}

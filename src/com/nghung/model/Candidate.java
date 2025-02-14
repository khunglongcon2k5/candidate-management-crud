/*
 * 
 * @author NguyenHung
 * @date Jan 4, 2025
 * @version 1.0
 *
 */

package com.nghung.model;

public abstract class Candidate {
	private String fullName;
	private String dateOfBirth;
	private String address;
	private String hometown;
	private String phoneNumber;
	private String emailAddress;
	
	public Candidate(String fullName, String dateOfBirth, String address, String hometown, String phoneNumber,
			String emailAddress) {
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.hometown = hometown;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
	
	public Candidate() {
		
	}

	/**
	 * @return the fullName
	 */
	public String getfullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setfullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the hometown
	 */
	public String getHometown() {
		return hometown;
	}

	/**
	 * @param hometown the hometown to set
	 */
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}

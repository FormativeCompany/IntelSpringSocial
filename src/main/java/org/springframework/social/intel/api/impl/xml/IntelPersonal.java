package org.springframework.social.intel.api.impl.xml;

public class IntelPersonal {
	// <birthday>10/31</birthday>
	// <maritalStatus>Married</maritalStatus>
	// <education>University</education>
	// <occupation>Cook</occupation>
	// <language>en-US</language>
	// <gender>Male</gender>

	private String birthday;
	private String maritalStatus;
	private String education;
	private String occupation;
	private String language;
	private String gender;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}

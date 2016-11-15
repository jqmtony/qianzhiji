package com.cxf.entity.bcert;
// default package

import java.util.Date;

/**
 * BCertHkb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BCertHkb implements java.io.Serializable {

	// Fields

	private String rowguid;
	private String thename;
	private String relation;
	private String othername;
	private String sex;
	private String birthplace;
	private String nation;
	private String jiguan;
	private String birthday;
	private String address;
	private String religion;
	private String idnumber;
	private String height;
	private String typeOfBlood;
	private String education;
	private String marriage;
	private String military;
	private String company;
	private String occupation;
	private String previousCity;
	private String previousAddress;
	private String registeredDate;
	private String isHouseholder;
	private String houseCategory;
	private String householder;
	private String housenumber;
	private String houseaddress;
	private String issuedDate;
	private String unsignclinegguid;
	private String signedclinegguid;
	private String isHistory;
	private Long version;
	private Date versiondate;
	private String appguid;
	private String auditState;
	private String auditNote;
	private String metaGuid;
	private String bbglguid;
	private String hkbGuid;

	// Constructors

	/** default constructor */
	public BCertHkb() {
	}

	/** minimal constructor */
	public BCertHkb(String rowguid) {
		this.rowguid = rowguid;
	}

	/** full constructor */
	public BCertHkb(String rowguid, String thename, String relation,
			String othername, String sex, String birthplace, String nation,
			String jiguan, String birthday, String address, String religion,
			String idnumber, String height, String typeOfBlood,
			String education, String marriage, String military, String company,
			String occupation, String previousCity, String previousAddress,
			String registeredDate, String isHouseholder, String houseCategory,
			String householder, String housenumber, String houseaddress,
			String issuedDate, String unsignclinegguid,
			String signedclinegguid, String isHistory, Long version,
			Date versiondate, String appguid, String auditState,
			String auditNote, String metaGuid, String bbglguid, String hkbGuid) {
		this.rowguid = rowguid;
		this.thename = thename;
		this.relation = relation;
		this.othername = othername;
		this.sex = sex;
		this.birthplace = birthplace;
		this.nation = nation;
		this.jiguan = jiguan;
		this.birthday = birthday;
		this.address = address;
		this.religion = religion;
		this.idnumber = idnumber;
		this.height = height;
		this.typeOfBlood = typeOfBlood;
		this.education = education;
		this.marriage = marriage;
		this.military = military;
		this.company = company;
		this.occupation = occupation;
		this.previousCity = previousCity;
		this.previousAddress = previousAddress;
		this.registeredDate = registeredDate;
		this.isHouseholder = isHouseholder;
		this.houseCategory = houseCategory;
		this.householder = householder;
		this.housenumber = housenumber;
		this.houseaddress = houseaddress;
		this.issuedDate = issuedDate;
		this.unsignclinegguid = unsignclinegguid;
		this.signedclinegguid = signedclinegguid;
		this.isHistory = isHistory;
		this.version = version;
		this.versiondate = versiondate;
		this.appguid = appguid;
		this.auditState = auditState;
		this.auditNote = auditNote;
		this.metaGuid = metaGuid;
		this.bbglguid = bbglguid;
		this.hkbGuid = hkbGuid;
	}

	// Property accessors

	public String getRowguid() {
		return this.rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	public String getThename() {
		return this.thename;
	}

	public void setThename(String thename) {
		this.thename = thename;
	}

	public String getRelation() {
		return this.relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOthername() {
		return this.othername;
	}

	public void setOthername(String othername) {
		this.othername = othername;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getJiguan() {
		return this.jiguan;
	}

	public void setJiguan(String jiguan) {
		this.jiguan = jiguan;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTypeOfBlood() {
		return this.typeOfBlood;
	}

	public void setTypeOfBlood(String typeOfBlood) {
		this.typeOfBlood = typeOfBlood;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getMilitary() {
		return this.military;
	}

	public void setMilitary(String military) {
		this.military = military;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPreviousCity() {
		return this.previousCity;
	}

	public void setPreviousCity(String previousCity) {
		this.previousCity = previousCity;
	}

	public String getPreviousAddress() {
		return this.previousAddress;
	}

	public void setPreviousAddress(String previousAddress) {
		this.previousAddress = previousAddress;
	}

	public String getRegisteredDate() {
		return this.registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getIsHouseholder() {
		return this.isHouseholder;
	}

	public void setIsHouseholder(String isHouseholder) {
		this.isHouseholder = isHouseholder;
	}

	public String getHouseCategory() {
		return this.houseCategory;
	}

	public void setHouseCategory(String houseCategory) {
		this.houseCategory = houseCategory;
	}

	public String getHouseholder() {
		return this.householder;
	}

	public void setHouseholder(String householder) {
		this.householder = householder;
	}

	public String getHousenumber() {
		return this.housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getHouseaddress() {
		return this.houseaddress;
	}

	public void setHouseaddress(String houseaddress) {
		this.houseaddress = houseaddress;
	}

	public String getIssuedDate() {
		return this.issuedDate;
	}

	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getUnsignclinegguid() {
		return this.unsignclinegguid;
	}

	public void setUnsignclinegguid(String unsignclinegguid) {
		this.unsignclinegguid = unsignclinegguid;
	}

	public String getSignedclinegguid() {
		return this.signedclinegguid;
	}

	public void setSignedclinegguid(String signedclinegguid) {
		this.signedclinegguid = signedclinegguid;
	}

	public String getIsHistory() {
		return this.isHistory;
	}

	public void setIsHistory(String isHistory) {
		this.isHistory = isHistory;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getVersiondate() {
		return this.versiondate;
	}

	public void setVersiondate(Date versiondate) {
		this.versiondate = versiondate;
	}

	public String getAppguid() {
		return this.appguid;
	}

	public void setAppguid(String appguid) {
		this.appguid = appguid;
	}

	public String getAuditState() {
		return this.auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getAuditNote() {
		return this.auditNote;
	}

	public void setAuditNote(String auditNote) {
		this.auditNote = auditNote;
	}

	public String getMetaGuid() {
		return this.metaGuid;
	}

	public void setMetaGuid(String metaGuid) {
		this.metaGuid = metaGuid;
	}

	public String getBbglguid() {
		return this.bbglguid;
	}

	public void setBbglguid(String bbglguid) {
		this.bbglguid = bbglguid;
	}

	public String getHkbGuid() {
		return this.hkbGuid;
	}

	public void setHkbGuid(String hkbGuid) {
		this.hkbGuid = hkbGuid;
	}

}
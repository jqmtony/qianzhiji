package com.cxf.entity.bcert;
// default package

import java.util.Date;

/**
 * BCert60lnz entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BCert60lnz implements java.io.Serializable {

	// Fields

	private String rowguid;
	private String thename;
	private String sex;
	private String birthday;
	private String idnumber;
	private String address;
	private String certnumber;
	private String issueddate;
	private String image;
	private String unsignclinegguid;
	private String signedclinegguid;
	private String ishistory;
	private Long version;
	private Date versiondate;
	private String appguid;
	private String auditstate;
	private String auditnote;
	private String metaguid;
	private String bbglguid;

	// Constructors

	/** default constructor */
	public BCert60lnz() {
	}

	/** minimal constructor */
	public BCert60lnz(String rowguid) {
		this.rowguid = rowguid;
	}

	/** full constructor */
	public BCert60lnz(String rowguid, String thename, String sex,
			String birthday, String idnumber, String address,
			String certnumber, String issueddate, String image,
			String unsignclinegguid, String signedclinegguid, String ishistory,
			Long version, Date versiondate, String appguid, String auditstate,
			String auditnote, String metaguid, String bbglguid) {
		this.rowguid = rowguid;
		this.thename = thename;
		this.sex = sex;
		this.birthday = birthday;
		this.idnumber = idnumber;
		this.address = address;
		this.certnumber = certnumber;
		this.issueddate = issueddate;
		this.image = image;
		this.unsignclinegguid = unsignclinegguid;
		this.signedclinegguid = signedclinegguid;
		this.ishistory = ishistory;
		this.version = version;
		this.versiondate = versiondate;
		this.appguid = appguid;
		this.auditstate = auditstate;
		this.auditnote = auditnote;
		this.metaguid = metaguid;
		this.bbglguid = bbglguid;
	}

	// Property accessors

	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	public String getThename() {
		return thename;
	}

	public void setThename(String thename) {
		this.thename = thename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCertnumber() {
		return certnumber;
	}

	public void setCertnumber(String certnumber) {
		this.certnumber = certnumber;
	}

	public String getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUnsignclinegguid() {
		return unsignclinegguid;
	}

	public void setUnsignclinegguid(String unsignclinegguid) {
		this.unsignclinegguid = unsignclinegguid;
	}

	public String getSignedclinegguid() {
		return signedclinegguid;
	}

	public void setSignedclinegguid(String signedclinegguid) {
		this.signedclinegguid = signedclinegguid;
	}

	public String getIshistory() {
		return ishistory;
	}

	public void setIshistory(String ishistory) {
		this.ishistory = ishistory;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getVersiondate() {
		return versiondate;
	}

	public void setVersiondate(Date versiondate) {
		this.versiondate = versiondate;
	}

	public String getAppguid() {
		return appguid;
	}

	public void setAppguid(String appguid) {
		this.appguid = appguid;
	}

	public String getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}

	public String getAuditnote() {
		return auditnote;
	}

	public void setAuditnote(String auditnote) {
		this.auditnote = auditnote;
	}

	public String getMetaguid() {
		return metaguid;
	}

	public void setMetaguid(String metaguid) {
		this.metaguid = metaguid;
	}

	public String getBbglguid() {
		return bbglguid;
	}

	public void setBbglguid(String bbglguid) {
		this.bbglguid = bbglguid;
	}


	
}
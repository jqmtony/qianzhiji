package com.cxf.entity.bcert;
// default package

import java.util.Date;

/**
 * BCertGsyyzz entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BCertGsyyzz implements java.io.Serializable {

	// Fields
	private String rowguid;
	private Date issueddate;
	private String enterprisename;
	private String certnumber;
	private String legalmen;
	private Date establishdate;
	private String registeredcapital;
	private Date startvaliddate;
	private Date endvaliddate;
	private String businessplace;
	private String address;
	private String businessscope;
	private String registoucode;
	private String enterprisetypecode;
	private String registouname;
	private String enterprisetypename;
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
	public BCertGsyyzz() {
	}

	/** minimal constructor */
	public BCertGsyyzz(String rowguid) {
		this.rowguid = rowguid;
	}

	/** full constructor */
	public BCertGsyyzz(String rowguid, Date issueddate, String enterprisename,
			String certnumber, String legalmen, Date establishdate,
			String registeredcapital, Date startvaliddate, Date endvaliddate,
			String businessplace, String address, String businessscope,
			String registoucode, String enterprisetypecode,
			String registouname, String enterprisetypename,
			String unsignclinegguid, String signedclinegguid, String ishistory,
			Long version, Date versiondate, String appguid, String auditstate,
			String auditnote, String metaguid, String bbglguid) {
		this.rowguid = rowguid;
		this.issueddate = issueddate;
		this.enterprisename = enterprisename;
		this.certnumber = certnumber;
		this.legalmen = legalmen;
		this.establishdate = establishdate;
		this.registeredcapital = registeredcapital;
		this.startvaliddate = startvaliddate;
		this.endvaliddate = endvaliddate;
		this.businessplace = businessplace;
		this.address = address;
		this.businessscope = businessscope;
		this.registoucode = registoucode;
		this.enterprisetypecode = enterprisetypecode;
		this.registouname = registouname;
		this.enterprisetypename = enterprisetypename;
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

	public Date getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}

	public String getCertnumber() {
		return certnumber;
	}

	public void setCertnumber(String certnumber) {
		this.certnumber = certnumber;
	}

	public String getLegalmen() {
		return legalmen;
	}

	public void setLegalmen(String legalmen) {
		this.legalmen = legalmen;
	}

	public Date getEstablishdate() {
		return establishdate;
	}

	public void setEstablishdate(Date establishdate) {
		this.establishdate = establishdate;
	}

	public String getRegisteredcapital() {
		return registeredcapital;
	}

	public void setRegisteredcapital(String registeredcapital) {
		this.registeredcapital = registeredcapital;
	}

	public Date getStartvaliddate() {
		return startvaliddate;
	}

	public void setStartvaliddate(Date startvaliddate) {
		this.startvaliddate = startvaliddate;
	}

	public Date getEndvaliddate() {
		return endvaliddate;
	}

	public void setEndvaliddate(Date endvaliddate) {
		this.endvaliddate = endvaliddate;
	}

	public String getBusinessplace() {
		return businessplace;
	}

	public void setBusinessplace(String businessplace) {
		this.businessplace = businessplace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessscope() {
		return businessscope;
	}

	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}

	public String getRegistoucode() {
		return registoucode;
	}

	public void setRegistoucode(String registoucode) {
		this.registoucode = registoucode;
	}

	public String getEnterprisetypecode() {
		return enterprisetypecode;
	}

	public void setEnterprisetypecode(String enterprisetypecode) {
		this.enterprisetypecode = enterprisetypecode;
	}

	public String getRegistouname() {
		return registouname;
	}

	public void setRegistouname(String registouname) {
		this.registouname = registouname;
	}

	public String getEnterprisetypename() {
		return enterprisetypename;
	}

	public void setEnterprisetypename(String enterprisetypename) {
		this.enterprisetypename = enterprisetypename;
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
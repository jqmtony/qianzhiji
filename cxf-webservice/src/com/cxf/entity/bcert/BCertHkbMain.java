package com.cxf.entity.bcert;

/**
 * 户口本首页实体
 * @author 苑高川
 */
public class BCertHkbMain implements java.io.Serializable {

	//wanglei edit 2016-09-02 去掉无用的属性，简化返回字符串
	/*户别*/
	private String housecategory;
	
	/*户主姓名*/
	private String householder;
	
	/*户号*/
	private String housenumber;
	
	/*住址*/
	private String houseaddress;
	
	/*签发日期*/
	private String issueddate;
	
	/*private String rowguid;
	private String unsignclinegguid;
	private String signedclinegguid;
	private String ishistory;
	private Long version;
	private Date versiondate;
	private String appguid;
	private String auditstate;
	private String auditnote;
	private String metaguid;
	private String bbglguid;*/
	
	/*public String getRowguid() {
		return rowguid;
	}
	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}*/
	public String getHousecategory() {
		return housecategory;
	}
	public void setHousecategory(String housecategory) {
		this.housecategory = housecategory;
	}
	public String getHouseholder() {
		return householder;
	}
	public void setHouseholder(String householder) {
		this.householder = householder;
	}
	public String getHousenumber() {
		return housenumber;
	}
	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}
	public String getHouseaddress() {
		return houseaddress;
	}
	public void setHouseaddress(String houseaddress) {
		this.houseaddress = houseaddress;
	}
	public String getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}
	/*public String getUnsignclinegguid() {
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
	}*/
}
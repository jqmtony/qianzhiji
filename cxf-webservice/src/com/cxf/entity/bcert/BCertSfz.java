package com.cxf.entity.bcert;

/**
 *身份证实体
 */
public class BCertSfz implements java.io.Serializable {

	// wanglei edit 2016-09-02 去掉无用的属性，简化返回字符串
	/*姓名*/
	private String thename;
	
	/*性别*/
	private String sex;
	
	/*民族*/
	private String nation;
	
	/*出生年月*/
	private String birthday;
	
	/*住址*/
	private String contactaddress;
	
	/*身份证号*/
	private String idnumber;
	
	/*签发机关*/
	private String qianfajg;
	
	/*有效期限*/
	private String youxiaoqixian;
	
	/*照片（二进制）*/
	private String image;
	
	/*照片base64格式*/
	private String imageOfBase64;
	/*
	private String rowguid;
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
	
	public String getImageOfBase64() {
		return imageOfBase64;
	}
	public void setImageOfBase64(String imageOfBase64) {
		this.imageOfBase64 = imageOfBase64;
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
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getContactaddress() {
		return contactaddress;
	}
	public void setContactaddress(String contactaddress) {
		this.contactaddress = contactaddress;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getQianfajg() {
		return qianfajg;
	}
	public void setQianfajg(String qianfajg) {
		this.qianfajg = qianfajg;
	}
	public String getYouxiaoqixian() {
		return youxiaoqixian;
	}
	public void setYouxiaoqixian(String youxiaoqixian) {
		this.youxiaoqixian = youxiaoqixian;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
package com.cxf.entity.bcert;

/**
 * 常住人口实体
 * @author 苑高川
 */
public class BCertHkbCzrk implements java.io.Serializable {

	//wanglei edit 2016-09-02 去掉无用的属性，简化返回字符串
	/*private String rowguid;
	private String hkbguid;*/
	/*姓名*/
	private String thename;
	
	/*户主或与户主关系*/
	private String relation;
	
	/*曾用名*/
	private String othername;
	
	/*性别*/
	private String sex;
	
	/*出生地*/
	private String birthplace;
	
	/*民族*/
	private String nation;
	
	/*籍贯*/
	private String nativeplace;
	
	/*出生日期*/
	private String birthday;
	
	/*本市（县）其他住址*/
	private String address;
	
	/*宗教信仰*/
	private String religion;
	
	/*身份证号*/
	private String idnumber;
	
	/*身高*/
	private String height;
	
	/*血型*/
	private String typeofblood;
	
	/*文化程度*/
	private String education;
	
	/*婚姻状况*/
	private String marriage;
	
	/*兵役状况*/
	private String military;
	
	/*服务处所*/
	private String company;
	
	/*职业*/
	private String occupation;
	
	/*何时由何地迁来本市（县）*/
	private String previouscity;
	
	/*何时由何地迁来本址*/
	private String previousaddress;
	
	/*登记日期*/
	private String registereddate;
	
	/*是否户主*/
	private String ishouseholder;
	
	/*public String getRowguid() {
		return rowguid;
	}
	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}
	public String getHkbguid() {
		return hkbguid;
	}
	public void setHkbguid(String hkbguid) {
		this.hkbguid = hkbguid;
	}*/
	public String getThename() {
		return thename;
	}
	public void setThename(String thename) {
		this.thename = thename;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getOthername() {
		return othername;
	}
	public void setOthername(String othername) {
		this.othername = othername;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTypeofblood() {
		return typeofblood;
	}
	public void setTypeofblood(String typeofblood) {
		this.typeofblood = typeofblood;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getMilitary() {
		return military;
	}
	public void setMilitary(String military) {
		this.military = military;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPreviouscity() {
		return previouscity;
	}
	public void setPreviouscity(String previouscity) {
		this.previouscity = previouscity;
	}
	public String getPreviousaddress() {
		return previousaddress;
	}
	public void setPreviousaddress(String previousaddress) {
		this.previousaddress = previousaddress;
	}
	public String getRegistereddate() {
		return registereddate;
	}
	public void setRegistereddate(String registereddate) {
		this.registereddate = registereddate;
	}
	public String getIshouseholder() {
		return ishouseholder;
	}
	public void setIshouseholder(String ishouseholder) {
		this.ishouseholder = ishouseholder;
	}
}
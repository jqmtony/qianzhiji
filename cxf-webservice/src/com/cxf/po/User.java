package com.cxf.po;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
 

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "username","createTime","phone","childlist"})
public class User {
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private Date  createTime;
	private String phone;
	private List<UserChild> childlist;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<UserChild> getChildlist() {
		return childlist;
	}
	public void setChildlist(List<UserChild> childlist) {
		this.childlist = childlist;
	}
	
	
	
	
	
	

}

package com.cxf.po;

import java.io.Serializable;

public class SzbUser implements Serializable {
	/**
	 */
	private static final long serialVersionUID = 3487372236525657043L;
	
		private String id;
		private String username;
		private String  createTime;
		private String phone;
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
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
	
	

}

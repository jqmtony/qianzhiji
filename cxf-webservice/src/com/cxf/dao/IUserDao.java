package com.cxf.dao;

import java.util.List;

import com.cxf.po.SzbUser;
import com.cxf.po.User;


public interface IUserDao {
    public abstract User getUser(String userid);

	public abstract List<SzbUser> getUserListByUserName(String username);

}

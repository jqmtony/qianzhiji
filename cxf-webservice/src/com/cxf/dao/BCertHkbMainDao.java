package com.cxf.dao;

import com.cxf.entity.bcert.BCertHkbMain;

/**
 * 电子证照-户口本首页Dao
 * @author 苑高川
 *
 */
public interface BCertHkbMainDao {
	
	 public abstract BCertHkbMain getHkbMainByIdNumber(String idNumber);
}

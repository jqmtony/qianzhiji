package com.cxf.dao;

import java.util.List;

import com.cxf.entity.bcert.BCertHkbCzrk;

/**
 * 电子证照-户口本常住人口登记卡
 * @author 苑高川
 *
 */
public interface BCertHkbCzrkDao {
	
	 public abstract List<BCertHkbCzrk> getHkbCzrkByIdNumber(String idNumber);
}

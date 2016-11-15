package com.cxf.dao;

import com.cxf.entity.bcert.BCertSfz;

/**
 * 电子证照-身份证Dao
 * @author yangxiaoqing
 *
 */
public interface BCertSfzDao {
	public abstract BCertSfz getBCertSfzByIdNumber(String idNumber);
}

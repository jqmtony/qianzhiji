package com.cxf.dao;

import com.cxf.entity.bcert.BCertJhz;

/**
 * 电子证照 结婚证Dao
 * @author wanglei
 *
 */
public interface BCertJhzDao {
	
	 public abstract BCertJhz getBCertJhz(String idNumber);
}

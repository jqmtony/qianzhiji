package com.cxf.dao;

import com.cxf.entity.bcert.BCertEhsyfwz;

/**
 * 电子证照-二孩生育服务证Dao
 * @author 苑高川
 *
 */
public interface BCertEhsyfwzDao {
	
	public abstract BCertEhsyfwz getDetailBcertEHSYFWZInfo(String woman_idNumber, String man_idNumber);
	
}

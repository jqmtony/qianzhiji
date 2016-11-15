package com.cxf.dao;

import com.cxf.entity.bcert.BCertYhsyfwz;

/**
 * 电子证照-一孩生育Dao
 * @author zhangyalan
 *
 */
public interface BCertYhsyfwzDao {
	
	/**
	 * 根据证照检索字段获取证照详细信息
	 * @param woman_idnumber
	 * @param man_idnumber
	 * @return
	 */
	public abstract BCertYhsyfwz getBCertYhsyfwz(String woman_idnumber,String man_idnumber);
}

package com.cxf.dao;

import com.cxf.entity.bcert.BCertGsyyzz;

/**
 * 电子证照-工商营业执照Dao
 * @author zhangyalan
 *
 */
public interface BCertGsyyzzDao {
	
	/**
	 * 根据证照检索字段获取证照详细信息
	 * @param enterpriseName
	 * @return
	 */
	public abstract BCertGsyyzz getBCertGsyyzz(String enterpriseName);

}

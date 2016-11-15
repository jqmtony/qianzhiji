package com.cxf.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import com.cxf.po.SzbUser;
import com.cxf.po.User;

@WebService
public interface BCertService {
	
	@WebMethod
	public abstract User getUser(String userid);
	
	@WebMethod
	public abstract List<SzbUser> getUserList(String username);
	
	/**
	 * 2.1电子证照-身份证
	 * 根据身份证号获取身份证证照信息
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertSfzInfo(String paras);
	
	/**
	 * 2.2电子证照-户口本
	 * 根据身份证号获取户口本所有人信息
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * （说明：返回身份证所在户口本上所有人的信息）
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertHkbInfo(String paras);
	
	/**
	 * 2.3电子证照-60岁老年证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcert60LNZInfo(String paras);
	
	/**
	 * 2.4电子证照-70岁老年证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * （说明：返回身份证所在户口本上所有人的信息）
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcert70LNZInfo(String paras);
	
	/**
	 * 2.5电子证照-二孩生育服务证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"WOMAN_IDNUMBER":"女方身份证号","MAN_IDNUMBER":"男方身份证号"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertEHSYFWZInfo(String paras);
	
	/**
	 * 2.6电子证照-流动人口婚育证明
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertLDRKHYZMInfo(String paras);
	
	/**
	 * 2.7电子证照-一孩生育服务证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"WOMAN_IDNUMBER":"女方身份证号","MAN_IDNUMBER":"男方身份证号"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertYHSYFWZInfo(String paras);
	
	/**
	 * 2.8电子证照-工商营业执照
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{" EnterpriseName ":"企业名称"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertGSYYZZInfo(String paras);
	
	/**
	 * 2.9电子证照-结婚证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * @return
	 */
	@WebMethod
	public abstract String GetDetailBcertJHZ(String paras);
	
}

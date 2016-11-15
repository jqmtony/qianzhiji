package com.cxf.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.cxf.dao.BCert60lnzDao;
import com.cxf.dao.BCert70lnzDao;
import com.cxf.dao.BCertEhsyfwzDao;
import com.cxf.dao.BCertGsyyzzDao;
import com.cxf.dao.BCertHkbCzrkDao;
import com.cxf.dao.BCertHkbMainDao;
import com.cxf.dao.BCertJhzDao;
import com.cxf.dao.BCertLdrkhyzmDao;
import com.cxf.dao.BCertSfzDao;
import com.cxf.dao.BCertYhsyfwzDao;
import com.cxf.dao.IUserDao;
import com.cxf.entity.bcert.BCert60lnz;
import com.cxf.entity.bcert.BCert70lnz;
import com.cxf.entity.bcert.BCertEhsyfwz;
import com.cxf.entity.bcert.BCertGsyyzz;
import com.cxf.entity.bcert.BCertHkbCzrk;
import com.cxf.entity.bcert.BCertHkbMain;
import com.cxf.entity.bcert.BCertJhz;
import com.cxf.entity.bcert.BCertLdrkhyzm;
import com.cxf.entity.bcert.BCertSfz;
import com.cxf.entity.bcert.BCertYhsyfwz;
import com.cxf.po.SzbUser;
import com.cxf.po.User;
import com.cxf.util.JsonUtil;

@Component
@WebService(endpointInterface = "com.cxf.service.BCertService", serviceName = "bCertService") 
public class BCertServiceImpl implements BCertService {
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Resource(name="bCert60lnzDao")
	private BCert60lnzDao bCert60lnzDao;
	
	@Resource(name="bCert70lnzDao")
	private BCert70lnzDao bCert70lnzDao;
	
	@Resource(name="bcertSfzDao")
	private BCertSfzDao bcertSfzDao;
	
	@Resource(name="bCertHkbMainDao")
	private BCertHkbMainDao bCertHkbMainDao;
	
	@Resource(name="bCertHkbCzrkDao")
	private BCertHkbCzrkDao bCertHkbCzrkDao;
	
	@Resource(name = "bCertEhsyfwzDao")
	private BCertEhsyfwzDao bCertEhsyfwzDao;//二孩生育服务证dao
	
	@Resource(name = "bCertLdrkhyzmDao")
	private BCertLdrkhyzmDao bCertLdrkhyzmDao;//流动人口婚育证明dao
	
	@Resource(name = "bCertYhsyfwzDao")
	private BCertYhsyfwzDao bCertYhsyfwzDao;//一孩生育服务证dao
	
	@Resource(name = "bCertGsyyzzDao")
	private BCertGsyyzzDao bCertGsyyzzDao;//工商营业执照dao
	
	@Resource(name = "bCertJhzDao")
	private BCertJhzDao bCertJhzDao;//结婚证执照dao
	
	private final Logger log=LoggerFactory.getLogger(BCertServiceImpl.class);
	
	public User getUser(String userid) {
		User user =null;
		try {
			 user=userDao.getUser(userid);
			 log.info("执行成功username==="+user.getUsername());
		} catch (Exception e) {
			log.error("查询抛出异常==="+e.getMessage());
		}
		return user;
	}
	public List<SzbUser> getUserList(String username) {
		List<SzbUser> userlist =new ArrayList<SzbUser>();
		try {
			  userlist=userDao.getUserListByUserName(username);
			  if(userlist==null){
				  log.info("userlist没有信息");
			  }else{
				  log.info("userlist数据为："+userlist.size());
			  }
		} catch (Exception e) {
			log.error("异常信息为："+e.getMessage());
		}
		return userlist;
	}

	/**
	 * 2.1电子证照-身份证
	 */
	public String GetDetailBcertSfzInfo(String paras) {
		BCertSfz bCertSfz = new BCertSfz();
		String returnCode="";           //0为程序出错
		String returnDescription="";     //程序描述
		String businessCode="";         //0为业务出错
		String businessDescription="";   //业务描述
		String infoType="BcertSfz_Info";
		if(null!=paras && !"".equals(paras)){
			String str[]=JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String certSfz=str[0];
				try{
					bCertSfz = bcertSfzDao.getBCertSfzByIdNumber(certSfz);
					//wanglei edit start 2016-09-06 添加无数据的判断
					if(bCertSfz != null && bCertSfz.getIdnumber() != null && bCertSfz.getIdnumber().length() > 0){
						returnCode="1";           
						returnDescription="身份证查询，执行成功！";
						businessCode="1";         
						businessDescription="身份证查询，执行成功！";
					}else{
						returnCode="0";           
						returnDescription="无符合条件数据！";
						businessCode="0";         
						businessDescription="无符合条件数据！";
					}
					//wanglei edit end 2016-09-06
					log.info("身份证查询，执行成功！");
				}catch(Exception e){
					returnCode="0";           
					returnDescription="身份证查询，抛出异常！";  
					log.error("身份证查询，抛出异常！"+e.getMessage());
				}
			}else{
				businessCode="0";         
				businessDescription="身份证查询，参数个数不符！";  
				log.error("身份证查询，参数个数不符！");
			}
		}else{
			businessCode="0";         
			businessDescription="身份证查询，参数个数不符！"; 
			log.error("身份证查询，参数个数不符！");
		}
		String result=JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, bCertSfz);
		log.info("前置机查询结束，返回串==="+result);
		return result;
	}
	
	/**
	 * 2.2电子证照-户口本
	 * 根据身份证号获取户口本所有人信息
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * （说明：返回身份证所在户口本上所有人的信息）
	 * @return
	 */
	@WebMethod
	public String GetDetailBcertHkbInfo(String paras){
		BCertHkbMain main=new BCertHkbMain();
		List<BCertHkbCzrk> czrk =new ArrayList<BCertHkbCzrk>();
		String returnCode="";           //0为程序出错
		String returnDescription="";     //程序描述
		String businessCode="";         //0为业务出错
		String businessDescription="";   //业务描述
		if(null!=paras && !"".equals(paras)){
			String str[]=JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String certHkb=str[0];
				try{
					main=bCertHkbMainDao.getHkbMainByIdNumber(certHkb);       //户口首页
					czrk=bCertHkbCzrkDao.getHkbCzrkByIdNumber(certHkb);        //常住人口
					//wanglei edit start 2016-09-06 添加无返回数据判断
					if(main.getHousenumber() != null && main.getHousenumber().length() > 0 && czrk.size() > 0){
						returnCode="1";
						returnDescription="户口本查询，执行成功！";
						businessCode="1";
						businessDescription="户口本查询，执行成功！";
					}else{
						returnCode="0";
						returnDescription="户口本首页或常住人口无符合条件数据！";
						businessCode="0";
						businessDescription="户口本首页或常住人口无符合条件数据！";
					}
					//wanglei edit end 2016-09-06
					log.info("户口本查询，执行成功！");
				}catch(Exception e){
					returnCode="0";           
					returnDescription="户口本查询，抛出异常！";  
					log.error("户口本查询，抛出异常！"+e.getMessage());
				}
			}else{
				businessCode="0";         
				businessDescription="户口本查询，参数个数不符！";  
				log.error("户口本查询，参数个数不符！");
			}
		}else{
			businessCode="0";         
			businessDescription="户口本查询，参数个数不符！"; 
			log.error("户口本查询，参数个数不符！");
		}
		//getJsonForHKB()方法的参数：1.是否为程序出错2.程序描述3.是否为业务出错4.业务描述5.户口本首页6.户口本常住人口
		String result=JsonUtil.getJsonForHKB(returnCode, returnDescription, businessCode, businessDescription, main, czrk);
		log.info("前置机查询结束，返回串==="+result);
		return result;
	}
	
	/**
	 * 2.3电子证照-60岁老年证
	 */
	public String GetDetailBcert60LNZInfo(String paras){
		BCert60lnz bCert60lnz =null;
		String returnCode="";           //0为程序出错
		String returnDescription="";     //程序描述
		String businessCode="";         //0为业务出错
		String businessDescription="";   //业务描述
		String infoType="Bcert60LNZ_Info";
		if(null!=paras && !"".equals(paras)){
			String str[]=JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String cert60lnz=str[0];
				try{
					bCert60lnz=bCert60lnzDao.getBCert60lnz(cert60lnz);
					returnCode="1";           
					returnDescription="60岁老年证查询，执行成功！";    
					businessCode="1";         
					businessDescription="60岁老年证查询，执行成功！";   
					log.info("60岁老年证查询，执行成功！");
				}catch(Exception e){
					returnCode="0";           
					returnDescription="60岁老年证查询，抛出异常！";  
					log.error("60岁老年证查询，抛出异常！"+e.getMessage());
				}
			}else{
				businessCode="0";         
				businessDescription="60岁老年证查询，参数个数不符！"; 
				log.error("60岁老年证查询，参数个数不符！");
			}
		}else{
			businessCode="0";         
			businessDescription="60岁老年证查询，参数个数不符！"; 
			log.error("60岁老年证查询，参数个数不符！");
		}
		String result=JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, bCert60lnz);
		return result;
	}
	
	/**
	 * 2.4电子证照-70岁老年证
	 */
	public String GetDetailBcert70LNZInfo(String paras){
		BCert70lnz bCert70lnz =null;
		String returnCode="";           //0为程序出错
		String returnDescription="";     //程序描述
		String businessCode="";         //0为业务出错
		String businessDescription="";   //业务描述
		String infoType="Bcert70LNZ_Info";
		if(null!=paras && !"".equals(paras)){
			String str[]=JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String cert70lnz=str[0];
				try{
					bCert70lnz=bCert70lnzDao.getBCert70lnz(cert70lnz);
					returnCode="1";           
					returnDescription="70岁老年证查询，执行成功！";    
					businessCode="1";         
					businessDescription="70岁老年证查询，执行成功！";   
					log.info("70岁老年证查询，执行成功！");
				}catch(Exception e){
					returnCode="0";           
					returnDescription="70岁老年证查询，抛出异常！";     
					log.error("70岁老年证查询，抛出异常！"+e.getMessage());
				}
			}else{
				businessCode="0";         
				businessDescription="70岁老年证查询，参数个数不符！";   
				log.error("70岁老年证查询，参数个数不符！");
			}
		}else{
			businessCode="0";         
			businessDescription="70岁老年证查询，参数个数不符！"; 
			log.error("70岁老年证查询，参数个数不符！");
		}
		String result=JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, bCert70lnz);
		return result;
	}
	
	/**
	 * 2.5电子证照-二孩生育服务证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"WOMAN_IDNUMBER":"女方身份证号","MAN_IDNUMBER":"男方身份证号"}}
	 * @return
	 */
	public String GetDetailBcertEHSYFWZInfo(String paras){
		BCertEhsyfwz fwz = null;
		String returnCode = "";//0为程序出错
		String returnDescription = "";//程序出错描述
		String businessCode = "";//0为业务出错
		String businessDescription = "";//业务出错描述
		String infoType = "BcertEHSYFWZ_Info";
		//调用工具类，解析参数
		if(null!=paras && !"".equals(paras)){
			String[] str = JsonUtil.getTwoPara(paras);
			if(null!=str && str.length==2){
				String woman_idnumber = str[0];
				String man_idnumber = str[1];
				//调用dao获取数据
				try {
					fwz = bCertEhsyfwzDao.getDetailBcertEHSYFWZInfo(woman_idnumber, man_idnumber);
					returnCode = "1";
					returnDescription = "二孩生育服务证查询，执行成功！";
					businessCode = "1";
					businessDescription = "二孩生育服务证查询，执行成功！";
					log.info("二孩生育服务证查询，执行成功！");
				} catch (Exception e) {
					returnCode = "0";
					returnDescription = "二孩生育服务证查询，抛出异常！";
					log.error("二孩生育服务证查询，抛出异常！" + e.getMessage());
				}
			}else {
				businessCode = "0";
				businessDescription = "二孩生育服务证查询，参数个数不符！";
				log.error("二孩生育服务证查询，参数个数不符！");
			}
		}else {
			businessCode = "0";
			businessDescription = "二孩生育服务证查询，参数个数不符！";
			log.error("二孩生育服务证查询，参数个数不符！");
		}
		//调用工具类，按要求转为json格式
		String result = JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, fwz);
		return result;
	}
	
	/**
	 * 2.6电子证照-流动人口婚育证明
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * @return
	 */
	public String GetDetailBcertLDRKHYZMInfo(String paras){
		BCertLdrkhyzm ldrkhyzm = null;
		String returnCode = "";//0为程序出错
		String returnDescription = "";//程序出错描述
		String businessCode = "";//0为业务出错
		String businessDescription = "";//业务出错描述
		String infoType = "BcertLDRKHYZM_Info";
		//调用工具类，解析参数
		if(null!=paras && !"".equals(paras)){
			String[] str = JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String idnumber = str[0];
				//调用dao获取数据
				try {
					ldrkhyzm = bCertLdrkhyzmDao.getDetailBcertLDRKHYZMInfo(idnumber);
					returnCode = "1";
					returnDescription = "流动人口婚育证明查询，执行成功！";
					businessCode = "1";
					businessDescription = "流动人口婚育证明查询，执行成功！";
					log.info("流动人口婚育证明查询，执行成功！");
				} catch (Exception e) {
					returnCode = "0";
					returnDescription = "流动人口婚育证明查询，抛出异常！";
					log.error("流动人口婚育证明查询，抛出异常！" + e.getMessage());
				}
			}else {
				businessCode = "0";
				businessDescription = "流动人口婚育证明查询，参数个数不符！";
				log.error("流动人口婚育证明查询，参数个数不符！");
			}
		}else {
			businessCode = "0";
			businessDescription = "流动人口婚育证明查询，参数个数不符！";
			log.error("流动人口婚育证明查询，参数个数不符！");
		}
		//调用工具类，按要求转为json格式
		String result = JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, ldrkhyzm);
		return result;
	}
	
	/**
	 * 2.7电子证照-一孩生育服务证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"WOMAN_IDNUMBER":"女方身份证号","MAN_IDNUMBER":"男方身份证号"}}
	 * @return
	 */
	public String GetDetailBcertYHSYFWZInfo(String paras){
		BCertYhsyfwz fwz = null;
		String returnCode = "";//0为程序出错
		String returnDescription = "";//程序出错描述
		String businessCode = "";//0为业务出错
		String businessDescription = "";//业务出错描述
		String infoType = "BcertYHSYFWZ_Info";
		//调用工具类，解析参数
		if(null!=paras && !"".equals(paras)){
			String[] str = JsonUtil.getTwoPara(paras);
			if(null!=str && str.length==2){
				String woman_idnumber = str[0];
				String man_idnumber = str[1];
				//调用dao获取数据
				try {
					fwz = bCertYhsyfwzDao.getBCertYhsyfwz(woman_idnumber, man_idnumber);
					returnCode = "1";
					returnDescription = "一孩生育服务证查询，执行成功！";
					businessCode = "1";
					businessDescription = "一孩生育服务证查询，执行成功！";
					log.info("一孩生育服务证查询，执行成功！");
				} catch (Exception e) {
					returnCode = "0";
					returnDescription = "一孩生育服务证查询，抛出异常！";
					log.error("一孩生育服务证查询，抛出异常！" + e.getMessage());
				}
			}else {
				businessCode = "0";
				businessDescription = "一孩生育服务证查询，参数个数不符！";
				log.error("一孩生育服务证查询，参数个数不符！");
			}
		}else {
			businessCode = "0";
			businessDescription = "一孩生育服务证查询，参数个数不符！";
			log.error("一孩生育服务证查询，参数个数不符！");
		}
		//调用工具类，按要求转为json格式
		String result = JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, fwz);
		return result;
	}
	
	/**
	 * 2.8电子证照-工商营业执照
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"EnterpriseName":"企业名称"}}
	 * @return
	 */
	public String GetDetailBcertGSYYZZInfo(String paras){
		BCertGsyyzz gsyyzz = null;
		String returnCode = "";//0为程序出错
		String returnDescription = "";//程序出错描述
		String businessCode = "";//0为业务出错
		String businessDescription = "";//业务出错描述
		String infoType = "BcertGSYYZZ_Info";
		//调用工具类，解析参数
		if(null!=paras && !"".equals(paras)){
			String[] str = JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String enterpriseName = str[0];
				//调用dao获取数据
				try {
					gsyyzz = bCertGsyyzzDao.getBCertGsyyzz(enterpriseName);
					returnCode = "1";
					returnDescription = "工商营业执照查询，执行成功！";
					businessCode = "1";
					businessDescription = "工商营业执照查询，执行成功！";
					log.info("工商营业执照查询，执行成功！");
				} catch (Exception e) {
					returnCode = "0";
					returnDescription = "工商营业执照查询，抛出异常！";
					log.error("工商营业执照查询，抛出异常！" + e.getMessage());
				}
			}else {
				businessCode = "0";
				businessDescription = "工商营业执照查询，参数个数不符！";
				log.error("工商营业执照查询，参数个数不符！");
			}
		}else {
			businessCode = "0";
			businessDescription = "工商营业执照查询，参数个数不符！";
			log.error("工商营业执照查询，参数个数不符！");
		}
		//调用工具类，按要求转为json格式
		String result = JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, gsyyzz);
		return result;
	}
	
	/**
	 * 2.9电子证照-结婚证
	 * 根据证照检索字段获取证照详细信息（只能查看发布字段）
	 * @param paras 参数为json格式的字符串{"paras":{"IDNUMBER":"具体身份证号"}}
	 * @return
	 */
	public String GetDetailBcertJHZ(String paras){
		BCertJhz bCertJhz = null;
		String returnCode = "";//0为程序出错
		String returnDescription = "";//程序出错描述
		String businessCode = "";//0为业务出错
		String businessDescription = "";//业务出错描述
		String infoType = "BcertJHZ_Info";
		//调用工具类，解析参数
		if(null!=paras && !"".equals(paras)){
			String[] str = JsonUtil.getOnePara(paras);
			if(null!=str && str.length==1){
				String idnumber = str[0];
				//调用dao获取数据
				try {
					bCertJhz = bCertJhzDao.getBCertJhz(idnumber);
					returnCode = "1";
					returnDescription = "结婚证查询，执行成功！";
					businessCode = "1";
					businessDescription = "结婚证查询，执行成功！";
					log.info("结婚证查询，执行成功！");
				} catch (Exception e) {
					returnCode = "0";
					returnDescription = "结婚证查询，抛出异常！";
					log.error("结婚证查询，抛出异常！" + e.getMessage());
				}
			}else {
				businessCode = "0";
				businessDescription = "结婚证查询，参数个数不符！";
				log.error("结婚证查询，参数个数不符！");
			}
		}else {
			businessCode = "0";
			businessDescription = "结婚证查询，参数个数不符！";
			log.error("结婚证查询，参数个数不符！");
		}
		//调用工具类，按要求转为json格式
		String result = JsonUtil.getJsonByPara(returnCode, returnDescription, businessCode, businessDescription, infoType, bCertJhz);
		return result;
	}
	
}

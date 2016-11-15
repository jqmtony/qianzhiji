package com.cxf.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import rpcservice.RemoteService;
import com.cxf.entity.bcert.BCertHkbMain;
import com.cxf.util.SearchUtil;
import com.cxf.util.XMLParser;

/**
 * 电子证照-户口本首页Dao实现类
 * @author 苑高川
 *
 */
@SuppressWarnings("unchecked")
public class BCertHkbMainDaoImpl implements BCertHkbMainDao {
	
	private final Logger log=LoggerFactory.getLogger(BCertHkbMainDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
    
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Logger getLog() {
		return log;
	}

	@SuppressWarnings("unchecked")
	public BCertHkbMain getHkbMainByIdNumber(String idNumber) {
		
		//根据身份证查询户号
		String nowHH = "";
		String hhXmlStr =  SearchUtil.getHuHaoByIdCard(idNumber);
		log.info("公安接口返回的报文为：" + hhXmlStr);
		if(hhXmlStr.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			nowHH = xmlParser.parseHh(hhXmlStr);
		}
		BCertHkbMain bhm = new BCertHkbMain();
		//请求SIN-00000048资源，获得户口本首页户主姓名、户号、签发日期
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		data = data + "<ReqDataTrace><Version>1.0</Version><SenderID>CID-00000102</SenderID><SenderInfo><UserCardId>123</UserCardId><Username>123</Username><Userdept>410100</Userdept></SenderInfo><ReceiverID>SIN-00000048</ReceiverID><REQUEST><Name>Query</Name><Condition>HH = '"+nowHH+"'</Condition><GETFEATURES><FeatureLimit>20</FeatureLimit><Beginrecord>1</Beginrecord><Showcount>0</Showcount></GETFEATURES>";
		data = data + "<Items><Item><Data>JTGX</Data><Data>HH</Data><Data>SFZQFRQ</Data><Data>XM</Data></Item></Items></REQUEST></ReqDataTrace>";
		log.info("执行成功户口本首页参数==="+data);
		String xmlStr =  RemoteService.sendMessage(data);
		log.info("公安接口返回的户口本首页数据为===" + xmlStr);
		if(xmlStr.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			bhm = xmlParser.parseHkbMain(xmlStr);
		}
		//请求SIN-00000076资源，获得户口本首页户别、住址
		String xmlStr2 =  SearchUtil.getAddressAndHuBieByHH(nowHH);
		log.info("公安接口返回的户口本首页数据2为===" + xmlStr2);
		if(xmlStr.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			BCertHkbMain temp = new BCertHkbMain();
			temp = xmlParser.parseHkbMain2(xmlStr2);
			bhm.setHousecategory(temp.getHousecategory());
			bhm.setHouseaddress(temp.getHouseaddress());
		}
		return bhm;
	}

}
package com.cxf.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import rpcservice.RemoteService;
import com.cxf.entity.bcert.BCertHkbCzrk;
import com.cxf.util.SearchUtil;
import com.cxf.util.XMLParser;

/**
 * 电子证照-户口本常住人口登记卡实现类
 * @author 苑高川
 *
 */
@SuppressWarnings("unchecked")
public class BCertHkbCzrkDaoImpl implements BCertHkbCzrkDao {
	
	private final Logger log=LoggerFactory.getLogger(BCertHkbCzrkDaoImpl.class);
	
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

	public List<BCertHkbCzrk> getHkbCzrkByIdNumber(String idNumber) {
		//根据身份证查询户号
		String nowHH = "";
		String hhXmlStr =  SearchUtil.getHuHaoByIdCard(idNumber);
		log.info("公安接口返回的报文为：" + hhXmlStr);
		if(hhXmlStr.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			nowHH = xmlParser.parseHh(hhXmlStr);
		}
		//根据户号查询常住人口
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		data = data + "<ReqDataTrace><Version>1.0</Version><SenderID>CID-00000102</SenderID><SenderInfo><UserCardId>123</UserCardId><Username>123</Username><Userdept>410100</Userdept></SenderInfo><ReceiverID>SIN-00000048</ReceiverID><REQUEST><Name>Query</Name><Condition>HH = '"+nowHH+"'</Condition><GETFEATURES><FeatureLimit>20</FeatureLimit><Beginrecord>1</Beginrecord><Showcount>0</Showcount></GETFEATURES>";
		//wanglei edit start 2016-08-30增加请求 '出生地省市县'，'出生地详址'；修改性别,文化程度，民族。
		//wanglei edit 2016-09-21 修改JGGJ -> JGGJ_MC,CSDGJ -> CSDGJ_MC,
		data = data + "<Items><Item><Data>XM</Data><Data>JGGJ_MC</Data><Data>JGSSX</Data><Data>XB</Data><Data>CSDGJ_MC</Data><Data>CSRQ</Data><Data>CYM</Data><Data>MZ</Data><Data>ZJXY_DM</Data><Data>SG</Data><Data>XX_DM</Data><Data>WHCD</Data><Data>HYZK_DM</Data><Data>BYZK_DM</Data><Data>FWCS</Data><Data>ZY</Data><Data>DJSJ</Data><Data>GMSFHM</Data><Data>JTGX</Data><Data>QLBS_RQ</Data><Data>CSDSSX</Data><Data>CSDXZ</Data></Item></Items></REQUEST></ReqDataTrace>";
		//wanglei edit start 2016-08-30
		log.info("执行成功paras==="+data);
		String xmlStr =  RemoteService.sendMessage(data);
		log.info("公安接口返回的报文为：" + xmlStr);
		List<BCertHkbCzrk> czrkList =  new ArrayList();
		if(xmlStr.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			czrkList = xmlParser.parseCZRK(xmlStr);
		}
		return czrkList;
	}

}

package com.cxf.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import rpcservice.RemoteService;
import com.cxf.entity.bcert.BCertSfz;
import com.cxf.util.SearchUtil;
import com.cxf.util.XMLParser;
import com.cxf.util.BlobUtil;;

/**
 * 电子证照-身份证Dao实现类
 * @author yangxiaoqing
 *
 */
@SuppressWarnings("unchecked")
public class BCertSfzDaoImpl implements BCertSfzDao {
	private final Logger log=LoggerFactory.getLogger(BCertSfzDaoImpl.class);
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

	public BCertSfz getBCertSfzByIdNumber(String idNumber) {
		//根据身份证从 SIN-00000048 查基本信息
		String data1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		data1 = data1 + "<ReqDataTrace><Version>1.0</Version><SenderID>CID-00000102</SenderID><SenderInfo><UserCardId>123</UserCardId><Username>123</Username><Userdept>410100</Userdept></SenderInfo><ReceiverID>SIN-00000048</ReceiverID><REQUEST><Name>Query</Name><Condition>GMSFHM = '"+idNumber+"'</Condition><GETFEATURES><FeatureLimit>20</FeatureLimit><Beginrecord>1</Beginrecord><Showcount>0</Showcount></GETFEATURES>";
		data1 = data1 + "<Items><Item><Data>XM</Data><Data>XB</Data><Data>MZ</Data><Data>CSRQ</Data><Data>GMSFHM</Data><Data>SFZ_YXQX_DM</Data></Item></Items></REQUEST></ReqDataTrace>";
		log.info("请求报文1为："+data1);
		String xmlStr1 =  RemoteService.sendMessage(data1);
		log.info("公安接口返回的响应报文1为：" + xmlStr1);
		//根据身份证从 SIN-00000076  查询户号
		String nowHH = "";
		String hhXmlStr =  SearchUtil.getHuHaoByIdCard(idNumber);
		log.info("公安接口返回的报文为：" + hhXmlStr);
		if(hhXmlStr.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			nowHH = xmlParser.parseHh(hhXmlStr);
		}
		//根据户号从 SIN-00000076  查拼接地址的三个字段；
		String xmlStr2 =  SearchUtil.getAddressAndHuBieByHH(nowHH);
		log.info("公安接口返回的响应报文2为：" + xmlStr2);
		//查询照片并转化为二进制
		String image ="";
		String imageOfBase64 = "";
		try{
			imageOfBase64 = RemoteService.remoteZpByCardId(idNumber);
			image = BlobUtil.byteToBinary(BlobUtil.base64Tobyte(imageOfBase64));
		}catch(Exception e){
			log.info("查询照片抛出异常！");
			e.printStackTrace();
		}
		//解析返回对象
		BCertSfz sfz = new BCertSfz();
		if(xmlStr1.contains("<ResqDataTrace>")){
			XMLParser xmlParser = new XMLParser();
			sfz = xmlParser.parseSFZ(xmlStr1, xmlStr2, image);
		}
		sfz.setImageOfBase64(imageOfBase64);
		return sfz;
	}
}

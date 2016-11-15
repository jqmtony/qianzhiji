package com.cxf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rpcservice.RemoteService;


/**
 * 通过请求报文查询需要信息
 * @author 王磊
 * @Date Jul 26, 2016 9:12:12 AM
 */
public class SearchUtil {
	
	private static final Logger log=LoggerFactory.getLogger(SearchUtil.class);
	
	 /**
	 * 根据身份证查询户号
	 * @author 王磊
	 * @Date Jul 26, 2016 9:11:58 AM
	 * @param idCard
	 * @return
	 */
	public static String getHuHaoByIdCard(String idCard){
		String queryForHH = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		queryForHH = queryForHH + "<ReqDataTrace><Version>1.0</Version><SenderID>CID-00000102</SenderID><SenderInfo><UserCardId>123</UserCardId><Username>123</Username><Userdept>410100</Userdept></SenderInfo><ReceiverID>SIN-00000048</ReceiverID><REQUEST><Name>Query</Name><Condition>GMSFHM = '"+idCard+"'</Condition><GETFEATURES><FeatureLimit>20</FeatureLimit><Beginrecord>1</Beginrecord><Showcount>0</Showcount></GETFEATURES>";
		queryForHH = queryForHH + "<Items><Item><Data>HH</Data><Data>XXJB</Data><Data>CZR_BS</Data></Item></Items></REQUEST></ReqDataTrace>";
		log.info("查询户号xml==="+queryForHH);
		return RemoteService.sendMessage(queryForHH);
	}
	
	/**
	 * 根据户号查询住址和户别
	 * @author 王磊
	 * @Date Jul 26, 2016 9:28:08 AM
	 * @param huHao
	 * @return
	 */
	public static String getAddressAndHuBieByHH(String huHao){
		String data2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		data2 = data2 + "<ReqDataTrace><Version>1.0</Version><SenderID>CID-00000102</SenderID><SenderInfo><UserCardId>123</UserCardId><Username>123</Username><Userdept>410100</Userdept></SenderInfo><ReceiverID>SIN-00000076</ReceiverID><REQUEST><Name>Query</Name><Condition>HH = '"+huHao+"'</Condition><GETFEATURES><FeatureLimit>20</FeatureLimit><Beginrecord>1</Beginrecord><Showcount>0</Showcount></GETFEATURES>";
		//wanglei edit 2016-09-21 SSSSXQ_DM -> SSSSXQ
		data2 = data2 + "<Items><Item><Data>HKLB</Data><Data>SSSSXQ</Data><Data>JLX_MC</Data><Data>MPH_MC</Data></Item></Items></REQUEST></ReqDataTrace>";
		log.info("执行成功户口本首页参数2==="+data2);
		return RemoteService.sendMessage(data2);
	}
}

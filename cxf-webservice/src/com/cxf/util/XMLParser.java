package com.cxf.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.cxf.entity.bcert.BCertHkbCzrk;
import com.cxf.entity.bcert.BCertHkbMain;
import com.cxf.entity.bcert.BCertSfz;

public class XMLParser {
	
	//户主
	public static String HuZhu = "户主";
	//是户主
	public static String isHuZhu = "是";
	//不是户主
	public static String isNotHuZhu = "否";

	/**
	 * 解析常住人口信息字符串
	 * @param str 报文格式如下：
	 *<Data>XM</Data>
	 *<Data>JGGJ</Data>
	 *<Data>JGSSX</Data>
	 *<Data>XB_DM</Data>
	 *<Data>CSDGJ</Data>
	 *<Data>CSRQ</Data>
	 *<Data>CYM</Data>
	 *<Data>MZ_DM</Data>
	 *<Data>ZJXY_DM</Data>
	 *<Data>SG</Data>
	 *<Data>XX_DM</Data>
	 *<Data>WHCD_DM</Data>
	 *<Data>HYZK_DM</Data>
	 *<Data>BYZK_DM</Data>
	 *<Data>FWCS</Data>
	 *<Data>ZY</Data>
	 *<Data>DJSJ</Data>
	 *<Data>GMSFHM</Data>
	 *<Data>HH</Data>
	 *<Data>QLBS_RQ</Data>
	 * @return
	 */
	public List<BCertHkbCzrk> parseCZRK(String str) {
		List<BCertHkbCzrk> result = new ArrayList<BCertHkbCzrk>();
		try {
			InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			Element element = document.getDocumentElement();
			NodeList rowNodes = element.getElementsByTagName("Row");
			for (int i = 2; i < rowNodes.getLength(); i++) {
				Element row = (Element) rowNodes.item(i);
				BCertHkbCzrk czrk = new BCertHkbCzrk();
				if (row != null) {
					NodeList datas = row.getElementsByTagName("Data");
					String CSDGJ="";//出生地国籍
					String CSDSSX="";//出生地省市县
					for (int j = 0; j < datas.getLength(); j++) {
						String rowValue = datas.item(j).getTextContent();
						if(j==0){
							//第1个返回值是姓名，放入姓名字段中
							czrk.setThename(rowValue);
						}else if(j==1){
							//第2个返回值是籍贯；
							czrk.setNativeplace(rowValue + datas.item(j+1).getTextContent());
						}else if(j==2){
							//第3个返回值是籍贯j=2时已经拼接好了；
							continue;
						}else if(j==3){
							//第4个返回值是性别；
							czrk.setSex(rowValue);
						}else if(j==4){
							//第5个返回值是出生地国籍；
							//czrk.setBirthplace(rowValue);
							CSDGJ = rowValue;
						}else if(j==5){
							//第6个返回值是生日；
							czrk.setBirthday(rowValue);
						}else if(j==6){
							//第7个返回值是曾用名；
							czrk.setOthername(rowValue);
						}else if(j==7){
							//第8个返回值是民族；
							czrk.setNation(rowValue);
						}else if(j==8){
							//第9个返回值是宗教信仰；
							czrk.setReligion(rowValue);
						}else if(j==9){
							//第10个返回值是身高；
							czrk.setHeight(rowValue);
						}else if(j==10){
							//第11个返回值是血型；
							czrk.setTypeofblood(rowValue);
						}else if(j==11){
							//第12个返回值是文化程度；
							czrk.setEducation(rowValue);
						}else if(j==12){
							//第13个返回值是婚姻状况；
							czrk.setMarriage(rowValue);
						}else if(j==13){
							//第14个返回值是兵役状况；
							czrk.setMilitary(rowValue);
						}else if(j==14){
							//第15个返回值是服务处所；
							czrk.setCompany(rowValue);
						}else if(j==15){
							//第16个返回值是职业；
							czrk.setOccupation(rowValue);
						}else if(j==16){
							//第17个返回值是登记人日期；
							czrk.setRegistereddate(rowValue);
						}else if(j==17){
							//第18个返回值是身份证编号；
							czrk.setIdnumber(rowValue);
						}else if(j==18){
							//第19个返回值家庭关系（户主或与户主关系、是否户主）
							czrk.setRelation(rowValue);
							if(HuZhu.equals(rowValue)){
								czrk.setIshouseholder(isHuZhu);
							}else{
								czrk.setIshouseholder(isNotHuZhu);
							}
						}else if(j==19){
							//第20个返回值是何时由何地迁来本市（县）、何时由何地迁来本址，只set了时间，没有set“何时”；
							czrk.setPreviousaddress(rowValue);
							czrk.setPreviouscity(rowValue);
						}else if(j==20){
							//第21个返回值是出生地省市县
							CSDSSX = rowValue;
						}else if(j==21){
							//第22个返回值是出生地详址
							czrk.setBirthplace(CSDGJ+""+CSDSSX+""+rowValue);
						}
					}
					result.add(czrk);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 解析身份证信息字符串
	 * @param xmlStr1  报文格式如下：
	 * <Data>XM</Data>
	 * <Data>XB_DM</Data>
	 * <Data>MZ_DM</Data>
	 * <Data>CSRQ</Data>
	 * <Data>GMSFHM</Data>
	 * <Data>SFZ_YXQX_DM</Data>
	 * @return
	 */
	public BCertSfz parseSFZ(String xmlStr1, String xmlStr2, String image){
		BCertSfz sfz = new BCertSfz();
		try {
			InputStream inputStream1 = new ByteArrayInputStream(xmlStr1.getBytes("UTF-8"));
			DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder1 = factory1.newDocumentBuilder();
			Document document1 = builder1.parse(inputStream1);
			Element element1 = document1.getDocumentElement();
			NodeList rowNodes1 = element1.getElementsByTagName("Row");
			Element row1 = (Element) rowNodes1.item(2);
			if (row1 != null) {
				NodeList datas1 = row1.getElementsByTagName("Data");
				for (int j = 0; j < datas1.getLength(); j++) {
					String rowValue = datas1.item(j).getTextContent();
					if(j==0){
						//第1个返回值是姓名，放入姓名字段中
						sfz.setThename(rowValue);
					}else if(j==1){
						//第2个返回值是性别；
						sfz.setSex(rowValue);
					}else if(j==2){
						//第3个返回值是民族；
						sfz.setNation(rowValue);
					}else if(j==3){
						//第4个返回值是出生日期；
						sfz.setBirthday(rowValue);
					}else if(j==4){
						//第5个返回值是身份证号；
						sfz.setIdnumber(rowValue);
					}else if(j==5){
						//第6个返回值是有效期限；
						sfz.setYouxiaoqixian(rowValue);
					}
				}
			}
			if(xmlStr2.contains("<ResqDataTrace>")){
				InputStream inputStream2 = new ByteArrayInputStream(xmlStr2.getBytes("UTF-8"));
				DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document document2 = builder2.parse(inputStream2);
				Element element2 = document2.getDocumentElement();
				NodeList rowNodes2 = element2.getElementsByTagName("Row");
				Element row2 = (Element) rowNodes2.item(2);
				if (row2 != null) {
					NodeList datas2 = row2.getElementsByTagName("Data");
					String rowValue = datas2.item(1).getTextContent() + datas2.item(2).getTextContent() + datas2.item(3).getTextContent();
					sfz.setContactaddress(rowValue);
				}
			}
			sfz.setImage(image);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfz;
	}
	
	/**
	 * 解析户口本首页字符串
	 * @param str 报文格式如下：
	 * <Data>JTGX</Data>
	 * <Data>HH</Data>
	 * <Data>SFZQFRQ</Data>
	 * <Data>XM</Data>
	 * @return
	 */
	public BCertHkbMain parseHkbMain(String str) {
		BCertHkbMain bhm = new BCertHkbMain();
		try {
			InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			Element element = document.getDocumentElement();
			NodeList rowNodes = element.getElementsByTagName("Row");
			for (int i = 2; i < rowNodes.getLength(); i++) {
				Element row = (Element) rowNodes.item(i);
				if (row != null) {
					NodeList datas = row.getElementsByTagName("Data");
					//得到家庭关系
					String nowJTGX = datas.item(0).getTextContent();
					if(HuZhu.equals(nowJTGX)){
						for (int j = 1; j < datas.getLength(); j++) {
							String rowValue = datas.item(j).getTextContent();
							if(j==1){
								//第1个返回户号
								bhm.setHousenumber(rowValue);
							}else if(j==2){
								//第2个返回值签发日期；
								bhm.setIssueddate(rowValue);
							}else if(j==3){
								//第3个返回值户主姓名；
								bhm.setHouseholder(rowValue);
							}
						}
					}else{
						continue;
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bhm;
	}
	
	/**
	 * 解析户口本首页户别、地址字符串
	 * @param str 报文格式如下：
	 * <Data>HKLB</Data>
	 * <Data>SSSSXQ_DM</Data>
	 * <Data>JLX_MC</Data>
	 * <Data>MPH_MC</Data>
	 * @return
	 */
	public BCertHkbMain parseHkbMain2(String str) {
		BCertHkbMain bhm = new BCertHkbMain();
		try {
			InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			Element element = document.getDocumentElement();
			NodeList rowNodes = element.getElementsByTagName("Row");
			for (int i = 2; i < rowNodes.getLength(); i++) {
				Element row = (Element) rowNodes.item(i);
				if (row != null) {
					NodeList datas = row.getElementsByTagName("Data");
					String huBie = datas.item(0).getTextContent();
					bhm.setHousecategory(huBie);
					bhm.setHouseaddress(datas.item(1).getTextContent()+""+datas.item(2).getTextContent()+""+datas.item(3).getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bhm;
	}
	
	/**
	 * 解析通过身份证得到户号的xml响应报文
	 * @param str 报文格式如下：
	 * <Data>HKLB</Data>
	 * <Data>SSSSXQ_DM</Data>
	 * <Data>JLX_MC</Data>
	 * <Data>MPH_MC</Data>
	 * @return
	 */
	public String parseHh(String str) {
		String result = "";
		try {
			InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			Element element = document.getDocumentElement();
			NodeList rowNodes = element.getElementsByTagName("Row");
			for (int i = 2; i < rowNodes.getLength(); i++) {
				Element row = (Element) rowNodes.item(i);
				if (row != null) {
					NodeList datas = row.getElementsByTagName("Data");
					//第一个是户号，第二个是XXJB，第三个是CZR_BS，只有当xxjb > 3 且 CZR_BS <> 9，户号才有效
					if(Integer.parseInt(datas.item(1).getTextContent()) > 3 && Integer.parseInt(datas.item(2).getTextContent()) != 9){
						result = datas.item(0).getTextContent();
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

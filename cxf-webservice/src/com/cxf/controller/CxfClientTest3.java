package com.cxf.controller;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.cxf.entity.bcert.BCert60lnz;
import com.cxf.entity.bcert.BCert70lnz;
import com.cxf.entity.bcert.BCertEhsyfwz;
import com.cxf.entity.bcert.BCertGsyyzz;
import com.cxf.entity.bcert.BCertHkbCzrk;
import com.cxf.entity.bcert.BCertHkbMain;
import com.cxf.entity.bcert.BCertLdrkhyzm;
import com.cxf.entity.bcert.BCertSfz;
import com.cxf.entity.bcert.BCertYhsyfwz;
import com.cxf.util.BlobUtil;
import com.cxf.util.SimpleJsonUtil;

public class CxfClientTest3 {
	
	public static void main(String[] args) {
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/bCertService?WSDL");
        Object[] objs;
		try {
			//身份证
			objs = client.invoke("GetDetailBcertSfzInfo", "{\"paras\":{\"IDNUMBER\":\"131127199103050030\"}}");
			System.out.println("2.1电子证照-身份证:\n" + objs[0]);
			//户口本
			objs = client.invoke("GetDetailBcertHkbInfo", "{\"paras\":{\"IDNUMBER\":\"131127199108090046\"}}");
			System.out.println("2.2电子证照-户口本:\n" + objs[0]);
			//60老年证
			objs = client.invoke("GetDetailBcert60LNZInfo", "{\"paras\":{\"IDNUMBER\":\"130103195601020314\"}}");
			System.out.println("2.3电子证照-60岁老年证:\n" + objs[0]);
			//70老年证
			objs = client.invoke("GetDetailBcert70LNZInfo", "{\"paras\":{\"IDNUMBER\":\"130103194505050323\"}}");
			System.out.println("2.4电子证照-70岁老年证:\n" + objs[0]);
			//二孩生育服务证
			objs = client.invoke("GetDetailBcertEHSYFWZInfo", "{\"paras\":{\"WOMAN_IDNUMBER\":\"130627199307053333\",\"MAN_IDNUMBER\":\"130627199307053332\"}}");
			System.out.println("2.5电子证照-二孩生育服务:\n" + objs[0]);
			//流动人口婚育证明
			objs = client.invoke("GetDetailBcertLDRKHYZMInfo", "{\"paras\":{\"IDNUMBER\":\"130633199009099911\"}}");
			System.out.println("2.6电子证照-流动人口婚育证明:\n" + objs[0]);
			//一孩生育服务证
			objs = client.invoke("GetDetailBcertYHSYFWZInfo", "{\"paras\":{\"WOMAN_IDNUMBER\":\"138489199302153450\",\"MAN_IDNUMBER\":\"138489199302153455\"}}");
			System.out.println("2.7电子证照-一孩生育服务证:\n" + objs[0]);
			//工商营业执照
			objs = client.invoke("GetDetailBcertGSYYZZInfo", "{\"paras\":{\"EnterpriseName\":\"福建\"}}");
			System.out.println("2.8电子证照-工商营业执照:\n" + objs[0]);
			
			/**
			 * 将图片二进制字符串通过输出流输出到本地硬盘（注：如果dao中对image是采用base64处理的，采用以下方式将会报错）
			 */
			//将返回值中业务数据转为对应的业务实体对象（户口本的未处理）
			Map<String, Map> map = SimpleJsonUtil.toMap(objs[0].toString(),String.class,Map.class);
			Map<String, Map> dataMap = map.get("UserArea");
			BCertSfz sfz = new BCertSfz();
			BCertHkbMain main = new BCertHkbMain();
			BCertHkbCzrk czrk = new BCertHkbCzrk();
			BCert60lnz lnz60 = new BCert60lnz();
			BCert70lnz lnz70 = new BCert70lnz();
			BCertEhsyfwz fwz2 = new BCertEhsyfwz();
			BCertLdrkhyzm ldrk = new BCertLdrkhyzm();
			BCertYhsyfwz fwz1 = new BCertYhsyfwz();
			BCertGsyyzz gsyy = new BCertGsyyzz();
			String rowguid = "";
			// 将字符串转换成二进制，用于显示图片    
			// 将上面生成的图片格式字符串 imgStr，还原成图片显示  
			byte[] imgByte = new byte[]{}; 
			if(dataMap.keySet().contains("BcertSfz_Info")){
				sfz = (BCertSfz)JSONObject.toBean(JSONObject.fromObject(dataMap.get("BcertSfz_Info")),BCertSfz.class);
				//rowguid = sfz.getRowguid();
				imgByte = BlobUtil.base64Tobyte(sfz.getImage());
			}else if(dataMap.keySet().contains("Bcert60LNZ_Info")){
				lnz60 = (BCert60lnz)JSONObject.toBean(JSONObject.fromObject(dataMap.get("Bcert60LNZ_Info")),BCert60lnz.class);
				rowguid = lnz60.getRowguid();
				imgByte = BlobUtil.base64Tobyte(lnz60.getImage());
			}else if(dataMap.keySet().contains("Bcert70LNZ_Info")){
				lnz70 = (BCert70lnz)JSONObject.toBean(JSONObject.fromObject(dataMap.get("Bcert70LNZ_Info")),BCert70lnz.class);
				rowguid = lnz70.getRowguid();
				imgByte = BlobUtil.base64Tobyte(lnz70.getImage());
			}else if(dataMap.keySet().contains("BcertEHSYFWZ_Info")){
				fwz2 = (BCertEhsyfwz)JSONObject.toBean(JSONObject.fromObject(dataMap.get("BcertEHSYFWZ_Info")),BCertEhsyfwz.class);
				rowguid = fwz2.getRowguid();
				imgByte = BlobUtil.base64Tobyte(fwz2.getImage());
			}else if(dataMap.keySet().contains("BcertLDRKHYZM_Info")){
				ldrk = (BCertLdrkhyzm)JSONObject.toBean(JSONObject.fromObject(dataMap.get("BcertLDRKHYZM_Info")),BCertLdrkhyzm.class);
				rowguid = ldrk.getRowguid();
				imgByte = BlobUtil.base64Tobyte(ldrk.getImage());
			}else if(dataMap.keySet().contains("BcertYHSYFWZ_Info")){
				fwz1 = (BCertYhsyfwz)JSONObject.toBean(JSONObject.fromObject(dataMap.get("BcertYHSYFWZ_Info")),BCertYhsyfwz.class);
				rowguid = fwz1.getRowguid();
				imgByte = BlobUtil.base64Tobyte(fwz1.getImage());
			}else if(dataMap.keySet().contains("BcertGSYYZZ_Info")){
				gsyy = (BCertGsyyzz)JSONObject.toBean(JSONObject.fromObject(dataMap.get("BcertGSYYZZ_Info")),BCertGsyyzz.class);
				rowguid = gsyy.getRowguid();
			}
			//.jpeg,.png,.gif,.bmp图片的格式依据
			byte[] jpeg = new byte[]{(byte)Integer.decode("0XFF").intValue(),(byte)Integer.decode("0XD8").intValue(),
					(byte)Integer.decode("0XFF").intValue(),(byte)Integer.decode("0XD9").intValue()};
			byte[] png = new byte[]{(byte)Integer.decode("0X89").intValue(),(byte)Integer.decode("0X50").intValue(),
					(byte)Integer.decode("0X4E").intValue(),(byte)Integer.decode("0X47").intValue(),
					(byte)Integer.decode("0X0D").intValue(),(byte)Integer.decode("0X0A").intValue(),
					(byte)Integer.decode("0X1A").intValue(),(byte)Integer.decode("0X0A").intValue()};
			byte[] gif1 = new byte[]{(byte)Integer.decode("0X47").intValue(),(byte)Integer.decode("0X49").intValue(),
					(byte)Integer.decode("0X46").intValue(),(byte)Integer.decode("0X38").intValue(),
					(byte)Integer.decode("0X39").intValue(),(byte)Integer.decode("0X61").intValue()};
			byte[] gif2 = new byte[]{(byte)Integer.decode("0X47").intValue(),(byte)Integer.decode("0X49").intValue(),
					(byte)Integer.decode("0X46").intValue(),(byte)Integer.decode("0X38").intValue(),
					(byte)Integer.decode("0X37").intValue(),(byte)Integer.decode("0X61").intValue()};
			byte[] gif3 = new byte[]{(byte)Integer.decode("0X47").intValue(),(byte)Integer.decode("0X49").intValue(),
					(byte)Integer.decode("0X46").intValue(),(byte)Integer.decode("0X38").intValue(),
					(byte)Integer.decode("0X3B").intValue(),(byte)Integer.decode("0X61").intValue()};
			byte[] bmp = new byte[]{(byte)Integer.decode("0X42").intValue(),(byte)Integer.decode("0X4D").intValue()};
			
			//判断图片的类型
			String imgType = "";
			if(imgByte[0]==jpeg[0] && imgByte[1]==jpeg[1] && imgByte[imgByte.length-2]==jpeg[2] && imgByte[imgByte.length-1]==jpeg[3]){
				imgType = ".jpg";
			}else if(imgByte[0]==png[0] && imgByte[1]==png[1] && imgByte[2]==png[2] && imgByte[3]==png[3] && imgByte[4]==png[4] && 
					imgByte[5]==png[5] && imgByte[6]==png[6] && imgByte[7]==png[7]){
				imgType = ".png";
			}else if(imgByte[0]==gif1[0] && imgByte[1]==gif1[1] && imgByte[2]==gif1[2] && imgByte[3]==gif1[3] && 
					(imgByte[4]==gif1[4] || imgByte[4]==gif2[4] || imgByte[4]==gif3[4]) && imgByte[5]==gif1[5]){
				imgType = ".gif";
			}else if(imgByte[0]==bmp[0] && imgByte[1]==bmp[1]){
				imgType = ".bmp";
			}
			//输出流将图片输出到本地硬盘中
			OutputStream o = new FileOutputStream("F:\\"+rowguid+imgType);    
			InputStream in = new ByteArrayInputStream(imgByte);    
			byte[] b = new byte[imgByte.length];    
			int nRead = 0;    
			while( ( nRead = in.read(b) ) != -1 ){    
				o.write( b, 0, nRead );    
			}    
			o.flush();    
			o.close();    
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

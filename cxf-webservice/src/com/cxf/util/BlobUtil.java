package com.cxf.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;    
import sun.misc.BASE64Encoder; 

/**
 * TODO Blob工具类
 * @author zps
 * @Date 2016-6-21 17:53:35
 */
public class BlobUtil{  
	
	private final Logger log = LoggerFactory.getLogger(BlobUtil.class);
	
	/**
	 * TODO 將byte[] 转化为二进制字符串
	 * @param bs
	 * @return String
	 */
	public static String byteToBinary(byte[] bs) {
		String ZERO = "00000000";
		/*String x = "2";
		byte[] bs = x.getBytes();*/
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < bs.length; i++) {
			String s = Integer.toBinaryString(bs[i]);
			if (s.length() > 8) {
				s = s.substring(s.length() - 8);
			} else if (s.length() < 8) {
				s = ZERO.substring(s.length()) + s;
			}
			str.append(s);
		}
		return str.toString();
	}
	
	/**
	 * TODO 將Blob数据转化为byte[]
	 * @param blob
	 * @return byte[]
	 */
	public static byte[] blobToBytes(Blob blob) {
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len && (read = is.read(bytes, offset, len)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}
	}
	
	/**
	 * TODO 將byte[] 转化为Base64字符串
	 * @param bs
	 * @return String
	 */
	public static String byteToBase64(byte[] bs) {
		BASE64Encoder encoder = new BASE64Encoder();    
	    return encoder.encodeBuffer(bs).trim(); 
	}
	
	/**
	 * TODO 將Base64字符串  转化为  byte[]
	 * @param String
	 * @return bs
	 */
	public static byte[] base64Tobyte(String base64String) {
		BASE64Decoder decoder = new BASE64Decoder();
	    try {
			return decoder.decodeBuffer(base64String);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/** 
	 * 二进制字符串转byte数组
	 * @param str 字符串 
	 * @return byte数组 
	 */  
	public static byte[] binaryStr2byte(String str) {  
		if(str == null)  
			return null;  
		str = str.trim();  
		int len = str.length();  
		if(len == 0 || len % 8 != 0)  
			return null;  
		byte[] b = new byte[len/8];  
		try{  
			for(int i = 0; i < str.length(); i+=8) {  
				int num = Integer.parseInt(str.substring(i, i+8),2);
				b[i/8] = (byte)num;  
			}  
			return b;  
		} catch (Exception e) {  
			return null;  
		}  
	}
}  
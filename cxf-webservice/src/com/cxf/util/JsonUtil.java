/*
 * Copyright 2015 www.hyberbin.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Email:hyberbin@qq.com
 */
package com.cxf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cxf.entity.bcert.BCertHkbCzrk;
import com.cxf.entity.bcert.BCertHkbMain;

/**
 * json工具.
 * 这里只是引用�?个简单的，如果要性能更高或�?�项目本身有可以自己设置
 * @author Hyberbin
 */
public class JsonUtil {

    private static IJsonUtil jsonUtil = new SimpleJsonUtil();

    public static String toJSON(Object o) {
        return jsonUtil.toJSON(o);
    }

    public static <T> T toObject(String s, Class type) {
        return jsonUtil.toObject(s, type);
    }

    public static void setJsonUtil(IJsonUtil jsonUtil) {
        JsonUtil.jsonUtil = jsonUtil;
    }
    
    /**
	 * 解析一个参数的json字符串，获得参数数组
	 */
	public static String[] getOnePara(String para) {
		//String paras = "{\"paras\":{\"thename\":\"张三\",\"rowguid\":\"12\"}}";
		Map<String,Map> map  = SimpleJsonUtil.toMap(para, String.class,Map.class);
		Map<String,String> valueMap = map.get("paras");
		String []values = null;
		if(valueMap.keySet().size() == 1){
			values = new String[1];
			if(valueMap.keySet().contains("IDNUMBER")){
				values[0] = valueMap.get("IDNUMBER").toString();
			}else{
				values[0] = valueMap.get("EnterpriseName").toString();
			}
		}else{
			values = new String[0];
		}
		return values;
	}
	
	/**
	 * 解析两个参数的json字符串，获得参数数组
	 */
	public static String[] getTwoPara(String para) {
		//String paras = "{\"paras\":{\"WOMAN_IDNUMBER\":\"张三\",\"MAN_IDNUMBER\":\"12\"}}";
		Map<String,Map> map  = SimpleJsonUtil.toMap(para, String.class,Map.class);
		Map<String,String> valueMap = map.get("paras");
		String []values = null;
		if(valueMap.keySet().size() == 2 && valueMap.keySet().contains("WOMAN_IDNUMBER") && valueMap.keySet().contains("MAN_IDNUMBER")){
			values = new String[2];
			values[0] = valueMap.get("WOMAN_IDNUMBER").toString();
			values[1] = valueMap.get("MAN_IDNUMBER").toString();
		}else{
			values = new String[0];
		}
		return values;
	}
	
	/**
	 * 整合数据并返回json字符串
	 * @param returnCode
	 * @param returnDescription
	 * @param businessCode
	 * @param businessDescription
	 * @param infoType 
	 * @param object
	 * @return
	 */
	public static String getJsonByPara(String returnCode,String returnDescription,
			String businessCode,String businessDescription,String infoType,Object object) {
		Map<String,Object> mapAll = new HashMap<String,Object>();
		Map<String,String> returnInfoMap = new HashMap<String,String>();
		Map<String,String> businessInfoMap = new HashMap<String,String>();
		returnInfoMap.put("Code", returnCode);
		returnInfoMap.put("Description", returnDescription);
		businessInfoMap.put("Code", businessCode);
		businessInfoMap.put("Description", businessDescription);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put(infoType, object);
		mapAll.put("ReturnInfo", returnInfoMap);
		mapAll.put("BusinessInfo", businessInfoMap);
		mapAll.put("UserArea", dataMap);
		//System.out.println(JsonUtil.toJSON(mapAll));
		return JsonUtil.toJSON(mapAll);
	}
	
	/**
	 * 户口本整合数据并json字符串
	 * @param returnCode
	 * @param returnDescription
	 * @param businessCode
	 * @param businessDescription
	 * @param infoType 
	 * @param object
	 * @return
	 */
	public static String getJsonForHKB(String returnCode,String returnDescription,
			String businessCode,String businessDescription,BCertHkbMain huzhu,List<BCertHkbCzrk> czrkList) {
		Map<String,Object> mapAll = new HashMap<String,Object>();
		Map<String,String> returnInfoMap = new HashMap<String,String>();
		Map<String,String> businessInfoMap = new HashMap<String,String>();
		returnInfoMap.put("Code", returnCode);
		returnInfoMap.put("Description", returnDescription);
		businessInfoMap.put("Code", businessCode);
		businessInfoMap.put("Description", businessDescription);
		//czrk
		List<Map<String,BCertHkbCzrk>> czrkData = new ArrayList<Map<String,BCertHkbCzrk>>();
		for(BCertHkbCzrk cz : czrkList){////1.常住人口
			Map<String,BCertHkbCzrk> map = new HashMap<String,BCertHkbCzrk>();
			map.put("czrk", cz);
			czrkData.add(map);
		}
		//CertInfo
		Map<String,BCertHkbMain> certInfoData = new HashMap<String,BCertHkbMain>();
		certInfoData.put("CertInfo", huzhu);////2.户主
		//UserArea
		Map<String,Object> userAreaMap = new HashMap<String,Object>();
		userAreaMap.put("CertInfo", certInfoData);
		userAreaMap.put("HKB_CZRK", czrkData);
		//dataMap.put("", list);
		mapAll.put("ReturnInfo", returnInfoMap);
		mapAll.put("BusinessInfo", businessInfoMap);
		mapAll.put("UserArea", userAreaMap);
		System.out.println(JsonUtil.toJSON(mapAll));
		return JsonUtil.toJSON(mapAll);
	}
}

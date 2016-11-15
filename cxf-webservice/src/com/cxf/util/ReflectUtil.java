package com.cxf.util;

import java.lang.annotation.Annotation;  
import java.lang.reflect.Field;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;  
import java.util.LinkedHashMap;  
import java.util.Map;  
import javax.persistence.Column;  
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
  
/**
 * TODO 反射工具类(bean=>map;map=>bean)
 * @author zps
 * @Date May 26, 2016 9:49:09 AM
 */
public class ReflectUtil{  
	
//	private static final Log log = LogUtil.getLog();
	
	/**
	 * TODO 根据一个.class得到实体属性和DB字段的对应关系
	 * @author zps
	 * @Date Jun 2, 2016 4:21:38 PM
	 * @param clazz
	 * @return map
	 */
	public static Map<String, String> reflectToFiledAndColumnMap(Class<? extends Object> clazz){  
        Field[] fields = clazz.getDeclaredFields();  
        //存放field和column对应关系，该关系来自于实体类的 @Column配置(key为bean属性，value为DB字段)  
        Map<String/*field name in modelBean*/, String/*column in db*/> fieldHasColumnAnnoMap = new LinkedHashMap<String, String>();  
        Annotation[] annotations = null;  
        for (Field field : fields){  
            annotations = field.getAnnotations();  
            for (Annotation an : annotations){  
                if (an instanceof Column){  
                    Column column = (Column)an;  
                    fieldHasColumnAnnoMap.put(field.getName(), column.name());  
                }  
            }  
        }  
        return fieldHasColumnAnnoMap;
    }  
	
    /**
     * TODO 将jdbcTemplate查询的map结果集转化得到实体属性与数据的对应关系
     * @author zps
     * @Date May 26, 2016 4:01:53 PM
     * @param clazz 想要反射的实体.class
     * @param jdbcMapResult
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> reflect(Class clazz, Map<String, Object> jdbcMapResult){  
        //存放field和column对应关系，该关系来自于实体类的 @Column配置(key为bean属性，value为DB字段)  
        Map<String/*field name in modelBean*/, String/*column in db*/> fieldHasColumnAnnoMap = new LinkedHashMap<String, String>();  
        fieldHasColumnAnnoMap = reflectToFiledAndColumnMap(clazz);
//        log.info("---bean属性和DB字段对应关系：---" + fieldHasColumnAnnoMap);
        //存放field name 和 对应的来自map的该field的属性值，用于后续reflect成ModelBean  
        Map<String,Object> conCurrent = new LinkedHashMap<String, Object>();  
        for (Map.Entry<String, String> en : fieldHasColumnAnnoMap.entrySet())  
        {  
            //将column大写。因为jdbcMapResult key is UpperCase  
        	//String key = en.getValue().toUpperCase();  
            String key = en.getValue();  
            //获得map的该field的属性值  
            Object value = jdbcMapResult.get(key);  
            if (value != null){  
                conCurrent.put(en.getKey(), value);  
            }  
        }  
//        log.info("---bean属性和数据的对应关系：---" + conCurrent);
        return conCurrent;
    }  
  
    /**
     * TODO 将数据封装成bean
     * @author zps
     * @Date May 26, 2016 9:46:51 AM
     * @param <T> 泛型
     * @param type bean的class类型
     * @param map 数据格式为：key是bean属性名称，value是数据值
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
	public static <T> T convertMap(Class<T> type, Map map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
    	//获取类属性
		BeanInfo beanInfo = Introspector.getBeanInfo(type); 
		//创建 JavaBean 对象
		Object obj = type.newInstance(); 
		//给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			//依次获取bean的每一个属性
			String propertyName = descriptor.getName();
			if(map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				try{
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}catch(Exception e){
//					log.info("---bean属性[" + propertyName + "]对应的数据为空---");
				}
			}
		}
		return (T) obj;
	} 
    
	/**
	 * TODO 将使用纯jdbc查询得到的ResultSet结果通过反射得到bean实体
	 * @author zps
	 * @Date Jun 2, 2016 5:07:19 PM
	 * @param <T>
	 * @param clazz
	 * @param rs
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertRs(Class<T> clazz, ResultSet rs)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException, SQLException {
		Map<String/*field name in modelBean*/, String/*column in db*/> fieldHasColumnAnnoMap = new LinkedHashMap<String, String>();  
	    fieldHasColumnAnnoMap = reflectToFiledAndColumnMap(clazz);
	    //获取类属性
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz); 
		//创建 JavaBean 对象
		Object obj = clazz.newInstance(); 
		//给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			//依次获取bean的每一个属性
			String propertyName = descriptor.getName();
			if(propertyName.equals("class") || !fieldHasColumnAnnoMap.containsKey(propertyName))
				continue;
			if(rs.getString(fieldHasColumnAnnoMap.get(propertyName)) != null) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				try{
					Object value = rs.getString(fieldHasColumnAnnoMap.get(propertyName));
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}catch(Exception e){
//					log.info("---bean属性[" + propertyName + "]对应的数据为空---");
				}
			}
		}
		return (T) obj;
	} 
	
    /**
     * TODO 将一个JavaBean 对象转化为一个Map
     * @author zps
     * @Date May 26, 2016 2:29:05 PM
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("unchecked")
	public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    } 
    
    /** 
     * test example 
     * @param args 
     * @throws Exception  
     * @see 
     */  
   /* public static void main(String[] args)  throws Exception{   
    	Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		conn = ConnOracle.getConn();
		try {
			String sql = "select * from " + CommonConstants.WAGEPAY;
			st = conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
			List<WagePay> wagePayList = new ArrayList<WagePay>();
			while(rs.next()){
				wagePayList.add(convertRs(WagePay.class,rs));
			}
			log.info(wagePayList.size());
			for(WagePay wagePayTemp : wagePayList){
				log.info(wagePayTemp.getEmpName() + "=" + wagePayTemp.getPrPaylistN13());
			}
		} catch(Exception e){
			log.info("--异常--");
		}
		
    	UserManagement u = new UserManagement();
    	u.setDeptId("001001");
    	u.setDeptName("办公室");
    	u.setUnitName("ssdsd");
    	log.info(ReflectUtil.convertBean(u));
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
        //当jdbcMapResult的key是数据库字段的时候(jdbcMapResult为jdbcTemplate执行sql语句返回的数据)
        Map<String, Object> jdbcMapResult = new HashMap<String, Object>();  
        Map<String, Object> jdbcMapResult1 = new HashMap<String, Object>();  
        jdbcMapResult.put("nd", "第一条数据的年度");  
        jdbcMapResult.put("co_code", "第一条数据的co_code");  
        //相当于制造了数据库查询出的两条记录
        list.add(jdbcMapResult);
        jdbcMapResult1.put("nd", "第二条数据的年度");  
        jdbcMapResult1.put("co_code", "第二条数据的co_code");  
        jdbcMapResult1.put("emp_name", "张三");  
        list.add(jdbcMapResult1);
        
        for(Map<String, Object> tempMap : list){
        	PersonInfo p1 = ReflectUtil.convertMap(PersonInfo.class, ReflectUtil.reflect(PersonInfo.class, tempMap));
        	log.info(p1.getNd() + " == " + p1.getCoCode() + " == " + p1.getEmpName());
            log.info("===============");
        }
        
        //当jdbcMapResult1的key是实体属性的时候(jdbcMapResult1为jdbcTemplate执行sql语句返回的数据)
        Map<String, Object> jdbcMapResult2 = new HashMap<String, Object>();  
        jdbcMapResult2.put("nd", "reflectbb");  
        jdbcMapResult2.put("coCode", "reflect123456bb");
        PersonInfo p2 = ReflectUtil.convertMap(PersonInfo.class, jdbcMapResult2);
//        log.info(p2.getNd() + " == " + p2.getCoCode());
    }  */
}  
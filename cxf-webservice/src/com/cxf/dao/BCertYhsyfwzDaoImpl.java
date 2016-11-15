package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cxf.entity.bcert.BCertYhsyfwz;
import com.cxf.util.BlobUtil;
import com.cxf.util.ReflectUtil;

/**
 * 电子证照-一孩生育Dao实现类
 * @author zhangyalan
 *
 */
public class BCertYhsyfwzDaoImpl implements BCertYhsyfwzDao{

    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public BCertYhsyfwz getBCertYhsyfwz(String woman_idnumber,
			String man_idnumber) {
		Map<String,Object> map = null;
		BCertYhsyfwz bCertYhsyfwz = new BCertYhsyfwz();
		String sql = "SELECT ROWGUID as rowguid,CERTNUMBER as certnumber,WOMAN_NAME as womanname,MAN_NAME as manname,WOMAN_IDNUMBER as womanidnumber,MAN_IDNUMBER as manidnumber,WOMAN_COMPANY as womancompany,MAN_COMPANY as mancompany,"+
        "WOMAN_HOUSEPLACE as womanhouseplace,MAN_HOUSEPLACE as manhouseplace,WOMAN_LIVEPLACE as womanliveplace,MAN_LIVEPLACE as manliveplace,WOMAN_MARRIAGE as womanmarriage,MAN_MARRIAGE as manmarriage,MARRIAGE_TIME as marriagetime,REASON as reason,"+
        "CHILD_NAME as childname,CHILD_SEX as childsex,CHILD_BIRTHDAY as childbirthday,ISSUED_DATE as issueddate,EXPIRE_DATE as expiredate FROM B_CERT_YHSYFWZ WHERE WOMAN_IDNUMBER = '"+woman_idnumber+"' and MAN_IDNUMBER = '"+man_idnumber+"'";   
		 List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		 
		 String image = (String) jdbcTemplate.queryForObject(  
	        "select image from b_cert_yhsyfwz where woman_idnumber = ? and man_idnumber = ? ",   
	        new Object[]{woman_idnumber,man_idnumber},  
	        new RowMapper(){  
	            public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
	                return BlobUtil.byteToBinary(BlobUtil.blobToBytes(rs.getBlob("image")));  
	            }  
        });
		 
		 if(list.size() > 0){
			 map = list.get(0);
		 }
		 if(map != null){
			 try {
				 bCertYhsyfwz = ReflectUtil.convertMap(BCertYhsyfwz.class, map);
				 bCertYhsyfwz.setImage(image);
				 if(bCertYhsyfwz.getChildsex().equals("0")){
					 bCertYhsyfwz.setChildsex("女");
				 }else if(bCertYhsyfwz.getChildsex().equals("1")){
					 bCertYhsyfwz.setChildsex("男");
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 return bCertYhsyfwz;
	}

}

package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import com.cxf.entity.bcert.BCertEhsyfwz;
import com.cxf.util.BlobUtil;
import com.cxf.util.ReflectUtil;
import org.springframework.jdbc.core.RowMapper;

/**
 * 电子证照-二孩生育服务证Dao实现类
 * @author 苑高川
 *
 */
@SuppressWarnings("unchecked")
public class BCertEhsyfwzDaoImpl implements BCertEhsyfwzDao {
	
	private final Logger log = LoggerFactory.getLogger(BCertLdrkhyzmDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public BCertEhsyfwz getDetailBcertEHSYFWZInfo(String woman_idNumber, String man_idNumber) {
		Map<String,Object> map = null;
		BCertEhsyfwz bCertEhsyfwz = new BCertEhsyfwz();
		String sql ="select rowguid as rowguid, certnumber as certnumber, woman_name as womanname, man_name as manname, woman_idnumber as womanidnumber," +
				" man_idnumber as manidnumber, woman_company as womancompany, man_company as mancompany, woman_houseplace as womanhouseplace, man_houseplace as manhouseplace," +
				" woman_liveplace as womanliveplace, man_liveplace as manliveplace, woman_marriage as womanmarriage, man_marriage as manmarriage, marriage_time as marriagetime," +
				" reason as reason, child_name as childname, child_sex as childsex, child_birthday as childbirthday, issued_date as issueddate," +
				" expire_date as expiredate" +
				" from B_CERT_EHSYFWZ where woman_idnumber = '" + woman_idNumber + "' and man_idnumber = '" + man_idNumber + "'";
		 List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		 
		 String image = (String) jdbcTemplate.queryForObject(  
	                "select image from b_cert_ehsyfwz where woman_idnumber = ? and man_idnumber = ? ",   
	                new Object[]{woman_idNumber,man_idNumber},  
	                new RowMapper(){  
	                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
	                        return BlobUtil.byteToBinary(BlobUtil.blobToBytes(rs.getBlob("IMAGE")));  
	                    }  
	     });
		 
		 if(list.size() > 0){
			 map = list.get(0);
		 }
		 if(map != null){
			 try {
				 bCertEhsyfwz = ReflectUtil.convertMap(BCertEhsyfwz.class, map);
				 bCertEhsyfwz.setImage(image);
				 if(bCertEhsyfwz.getChildsex().equals("1")){
					 bCertEhsyfwz.setChildsex("男");
				 }else if(bCertEhsyfwz.getChildsex().equals("0")){
					 bCertEhsyfwz.setChildsex("女");
				 }
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 return bCertEhsyfwz;
	}
}

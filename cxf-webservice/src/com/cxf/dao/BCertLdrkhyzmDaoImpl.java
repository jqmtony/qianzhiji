package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.cxf.entity.bcert.BCertLdrkhyzm;
import com.cxf.util.BlobUtil;
import com.cxf.util.ReflectUtil;

/**
 * 电子证照-流动人口婚育证明Dao实现类
 * @author 苑高川
 *
 */
@SuppressWarnings("unchecked")
public class BCertLdrkhyzmDaoImpl implements BCertLdrkhyzmDao {
	
	private final Logger log = LoggerFactory.getLogger(BCertLdrkhyzmDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public BCertLdrkhyzm getDetailBcertLDRKHYZMInfo(String IDNUMBER) {
		
		Map<String,Object> map = null;
		BCertLdrkhyzm bCertLdrkhyzm = new BCertLdrkhyzm();
		String sql = "select rowguid as rowguid, certnumber as certnumber, thename as thename, sex as sex, age as age," +
				" authority as authority, authority_address as authorityaddress, postnumber as postnumber, tel as tel, issued_date as issueddate," +
				" expire_date as expiredate, operator as operator, marriage as marriage, birthday as birthday, idnumber as idnumber," +
				" marriage_time as marriagetime, houseplace as houseplace, houseplace_tel as houseplacetel, mate_name as matename, mate_houseplace as matehouseplace," +
				" mate_houseplace_tel as matehouseplacetel, child_number_boy as childnumberboy, child_number_girl as childnumbergirl, marriage_record as marriagerecord, marriage_record_boy as marriagerecordboy," +
				" marriage_record_girl as marriagerecordgirl, operation_record as operationrecord, charge_record as chargerecord, houseplace_record1 as houseplacerecord1, houseplace_record2 as houseplacerecord2," +
				" houseplace_record3 as houseplacerecord3, houseplace_record4 as houseplacerecord4, houseplace_record5 as houseplacerecord5, houseplace_record6 as houseplacerecord6, houseplace_record7 as houseplacerecord7," +
				" houseplace_record8 as houseplacerecord8, houseplace_record9 as houseplacerecord9, houseplace_record10 as houseplacerecord10, houseplace_record11 as houseplacerecord11, houseplace_record12 as houseplacerecord12," +
				" situation_record1 as situationrecord1, situation_record2 as situationrecord2, situation_record3 as situationrecord3, situation_record4 as situationrecord4, situation_record5 as situationrecord5," +
				" situation_record6 as situationrecord6, situation_record7 as situationrecord7, situation_record8 as situationrecord8, situation_record9 as situationrecord9, situation_record10 as situationrecord10," +
				" situation_record11 as situationrecord11, situation_record12 as situationrecord12, situation_record13 as situationrecord13, situation_record14 as situationrecord14, situation_record15 as situationrecord15," +
				" situation_record16 as situationrecord16, situation_record17 as situationrecord17, situation_record18 as situationrecord18, service_record1 as servicerecord1, service_record2 as servicerecord2," +
				" service_record3 as servicerecord3, service_record4 as servicerecord4, service_record5 as servicerecord5, service_record6 as servicerecord6, service_record7 as servicerecord7," +
				" service_record8 as servicerecord8, service_record9 as servicerecord9" +
				" from b_cert_ldrkhyzm where idnumber = '" + IDNUMBER + "'";
		
		 List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		 if(list.size() > 0){
			 map = list.get(0);
		 }
		 String image = (String) jdbcTemplate.queryForObject(  
	                "select image from b_cert_ldrkhyzm where idnumber = ? ",   
	                new Object[]{IDNUMBER},  
	                new RowMapper(){  
	                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
	                        return BlobUtil.byteToBinary(BlobUtil.blobToBytes(rs.getBlob("image")));  
	                    }  
	        });
		 if(map != null){
			 try {
				bCertLdrkhyzm = ReflectUtil.convertMap(BCertLdrkhyzm.class, map);
				bCertLdrkhyzm.setImage(image);
				if(bCertLdrkhyzm.getSex().equals("1")){
					bCertLdrkhyzm.setSex("男");
				 }else if(bCertLdrkhyzm.getSex().equals("0")){
					 bCertLdrkhyzm.setSex("女");
				 }
			 } catch (Exception e) {
				e.printStackTrace();
			 }
		 }
		 return bCertLdrkhyzm;
	}
}

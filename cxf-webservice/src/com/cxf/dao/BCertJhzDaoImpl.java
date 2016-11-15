package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.cxf.entity.bcert.BCertJhz;
import com.cxf.util.BlobUtil;

/**
 * 电子证照 结婚证Dao实现类
 * @author wanglei
 *
 */
@SuppressWarnings("unchecked")
public class BCertJhzDaoImpl implements BCertJhzDao {
	
	private final Logger log=LoggerFactory.getLogger(BCert60lnzDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 根据身份证查询结婚证信息
	 */
	public BCertJhz getBCertJhz(String idNumber) {
		 return (BCertJhz) jdbcTemplate.queryForObject(  
                "select s.rowguid,s.authority,s.registrars,s.holder,s.recoed_date,s.certnumber,s.remark," +
                "s.name,s.sex,s.nationality,s.birthday,s.idnumber,s.spousename,s.spousesex,s.spousenationality,"+
                " s.spousebirthday,s.spouseidnumber,s.image from b_cert_jhz s where s.idnumber = ? ",   
                new Object[]{idNumber},  
                new RowMapper(){  
                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
                    	BCertJhz BCertJhz  = new BCertJhz();
                    	BCertJhz.setRowguid(rs.getString("rowguid"));
                    	BCertJhz.setAuthority(rs.getString("authority"));
                    	BCertJhz.setRegistrars(rs.getString("registrars"));
                    	BCertJhz.setHolder(rs.getString("holder"));
                    	BCertJhz.setRecoeddate(rs.getDate("recoed_date"));
                    	BCertJhz.setCertnumber(rs.getString("certnumber"));
                    	BCertJhz.setRemark(rs.getString("remark"));
                    	BCertJhz.setName(rs.getString("name"));
                    	if(rs.getString("sex").equals("0")){
                    		BCertJhz.setSex("女");
       				 	}else if(rs.getString("sex").equals("1")){
       				 		BCertJhz.setSex("男");
       				 	}
                    	BCertJhz.setNationality(rs.getString("nationality"));
                    	BCertJhz.setBirthday(rs.getDate("birthday"));
                    	BCertJhz.setIdnumber(rs.getString("idnumber"));
                    	BCertJhz.setSpousename(rs.getString("spousename"));
                    	if(rs.getString("spousesex").equals("0")){
                    		BCertJhz.setSpousesex("女");
       				 	}else if(rs.getString("spousesex").equals("1")){
       				 		BCertJhz.setSpousesex("男");
       				 	}
                    	BCertJhz.setSpousenationality(rs.getString("spousenationality"));
                    	BCertJhz.setSpousebirthday(rs.getDate("spousebirthday"));
                    	BCertJhz.setSpouseidnumber(rs.getString("spouseidnumber"));
                    	BCertJhz.setImage(BlobUtil.byteToBinary(BlobUtil.blobToBytes(rs.getBlob("image"))));
                    	return BCertJhz;
                    }  
	        }); 
	}
}
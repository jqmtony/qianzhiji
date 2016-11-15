package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.cxf.entity.bcert.BCert60lnz;
import com.cxf.util.BlobUtil;

/**
 * 电子证照 60岁老年证Dao实现类
 * @author hushiyang
 *
 */
@SuppressWarnings("unchecked")
public class BCert60lnzDaoImpl implements BCert60lnzDao {
	
	private final Logger log=LoggerFactory.getLogger(BCert60lnzDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public BCert60lnz getBCert60lnz(String idNumber) {
		 return (BCert60lnz) jdbcTemplate.queryForObject(  
                "SELECT S.ROWGUID,S.THENAME,S.SEX,S.IDNUMBER,S.BIRTHDAY,S.ADDRESS,S.CERTNUMBER," +
                "S.ISSUED_DATE,S.IMAGE FROM B_CERT_60LNZ S WHERE S.IDNUMBER = ? ",   
                new Object[]{idNumber},  
                new RowMapper(){  
                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
                    	BCert60lnz BCert60lnz  = new BCert60lnz();  
                    	BCert60lnz.setRowguid(rs.getString("ROWGUID"));  
                    	BCert60lnz.setThename(rs.getString("THENAME")); 
                    	if(rs.getString("SEX").equals("0")){
                    		BCert60lnz.setSex("女");
       				 	}else if(rs.getString("SEX").equals("1")){
       				 		BCert60lnz.setSex("男");
       				 	}
                    	BCert60lnz.setIdnumber(rs.getString("IDNUMBER"));
                    	BCert60lnz.setBirthday(rs.getString("BIRTHDAY"));
                    	BCert60lnz.setAddress(rs.getString("ADDRESS"));
                    	BCert60lnz.setCertnumber(rs.getString("CERTNUMBER"));
                    	BCert60lnz.setIssueddate(rs.getString("ISSUED_DATE"));
                    	BCert60lnz.setImage(BlobUtil.byteToBinary(BlobUtil.blobToBytes(rs.getBlob("IMAGE"))));
                    	return BCert60lnz;
                    }  	              
        }); 
	}
}
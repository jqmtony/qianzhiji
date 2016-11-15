package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.cxf.entity.bcert.BCert70lnz;
import com.cxf.util.BlobUtil;

/**
 * 电子证照 70岁老年证Dao实现类
 * @author hushiyang
 *
 */
@SuppressWarnings("unchecked")
public class BCert70lnzDaoImpl implements BCert70lnzDao {
	
	private final Logger log=LoggerFactory.getLogger(BCert70lnzDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public BCert70lnz getBCert70lnz(String idNumber) {
		 return (BCert70lnz) jdbcTemplate.queryForObject(  
                "SELECT S.ROWGUID,S.THENAME,S.SEX,S.IDNUMBER,S.BIRTHDAY,S.ADDRESS,S.CERTNUMBER," +
                "S.ISSUED_DATE,S.IMAGE FROM B_CERT_70LNZ S WHERE S.IDNUMBER = ? ",   
                new Object[]{idNumber},  
                new RowMapper(){  
                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
                    	BCert70lnz BCert70lnz  = new BCert70lnz();  
                    	BCert70lnz.setRowguid(rs.getString("ROWGUID"));  
                    	BCert70lnz.setThename(rs.getString("THENAME")); 
                    	if(rs.getString("SEX").equals("0")){
                    		BCert70lnz.setSex("女");
       				 	}else if(rs.getString("SEX").equals("1")){
       				 		BCert70lnz.setSex("男");
       				 	}
                    	BCert70lnz.setIdnumber(rs.getString("IDNUMBER"));
                    	BCert70lnz.setBirthday(rs.getString("BIRTHDAY"));
                    	BCert70lnz.setAddress(rs.getString("ADDRESS"));
                    	BCert70lnz.setCertnumber(rs.getString("CERTNUMBER"));
                    	BCert70lnz.setIssueddate(rs.getString("ISSUED_DATE"));
                    	BCert70lnz.setImage(BlobUtil.byteToBinary(BlobUtil.blobToBytes(rs.getBlob("IMAGE"))));
                    	return BCert70lnz;
                    }  
        }); 
	}
}
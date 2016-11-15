package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.cxf.entity.bcert.BCertGsyyzz;

/**
 * 电子证照-工商营业执照Dao实现类 
 * @author zhangyalan
 *
 */
public class BCertGsyyzzDaoImpl implements  BCertGsyyzzDao{
	
	private final Logger log=LoggerFactory.getLogger(BCertGsyyzzDaoImpl.class);
	
    private JdbcTemplate jdbcTemplate;
    
    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
	@SuppressWarnings("unchecked")
	public BCertGsyyzz getBCertGsyyzz(String enterpriseName) {
		 return (BCertGsyyzz) jdbcTemplate.queryForObject(  
                "SELECT ROWGUID,ISSUED_DATE,EnterpriseName,CertNumber,LegalMen,EstablishDate,RegisteredCapital,StartValidDate,"+
                "EndValidDate,BusinessPlace,Address,BusinessScope,RegistOUCode,EnterpriseTypeCode,RegistOUName,EnterpriseTypeName FROM B_CERT_GSYYZZ WHERE EnterpriseName like ?",   
                new Object[]{"%"+enterpriseName+"%"},  
                new RowMapper(){  
                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
                    	BCertGsyyzz bCertGsyyzz  = new BCertGsyyzz();  
                    	bCertGsyyzz.setRowguid(rs.getString("rowguid"));
                    	bCertGsyyzz.setIssueddate(rs.getDate("issued_date"));
                    	bCertGsyyzz.setEnterprisename(rs.getString("enterprisename"));
                    	bCertGsyyzz.setCertnumber(rs.getString("certnumber"));
                    	bCertGsyyzz.setLegalmen(rs.getString("legalmen"));
                    	bCertGsyyzz.setEstablishdate(rs.getDate("establishdate"));
                    	bCertGsyyzz.setRegisteredcapital(rs.getString("registeredcapital"));
                    	bCertGsyyzz.setStartvaliddate(rs.getDate("startvaliddate"));
                    	bCertGsyyzz.setEndvaliddate(rs.getDate("endvaliddate"));
                    	bCertGsyyzz.setBusinessplace(rs.getString("businessplace"));
                    	bCertGsyyzz.setAddress(rs.getString("address"));
                    	bCertGsyyzz.setBusinessscope(rs.getString("businessscope"));
                    	bCertGsyyzz.setRegistoucode(rs.getString("registoucode"));
                    	bCertGsyyzz.setEnterprisetypecode(rs.getString("enterprisetypecode"));
                    	bCertGsyyzz.setRegistouname(rs.getString("registouname"));
                    	bCertGsyyzz.setEnterprisetypename(rs.getString("enterprisetypename"));
                        return bCertGsyyzz;  
                    }  
	              
	        }); 
	}

}

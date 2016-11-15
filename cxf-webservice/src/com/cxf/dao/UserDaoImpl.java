package com.cxf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cxf.po.SzbUser;
import com.cxf.po.User;


@SuppressWarnings("unchecked")
public class UserDaoImpl implements IUserDao {
	private final Logger log=LoggerFactory.getLogger(UserDaoImpl.class);
    private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	public User getUser(String userid) {
		 return (User) jdbcTemplate.queryForObject(  
	                "SELECT * FROM SZB_USER WHERE ID = ?",   
	                new Object[]{userid},  
	                new RowMapper(){  
	                    public Object mapRow(ResultSet rs,int rowNum)throws SQLException {  
	                        User user  = new User();  
	                        user.setUsername(rs.getString("username"));
	                        return user;  
	                    }  
	              
	        }); 
	}

	public List<SzbUser> getUserListByUserName(String username) {
		 List<Object> params = new ArrayList<Object>();
		 params.add("%"+username+"%");
		 
		 return jdbcTemplate.query("SELECT * FROM SZB_USER S WHERE S.USERNAME LIKE ? ",   
				   params.toArray(),   
	                new RowMapper(){  
	              
	                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {  
	                    	SzbUser user  = new SzbUser();  
	                        user.setId(rs.getString("id"));  
	                        user.setUsername(rs.getString("USERNAME")); 
	                        user.setPhone(rs.getString("phone"));
	                        user.setCreateTime(rs.getString("create_Time"));
	                        return user;  
	                    }  
	        });  
	    }  
	
	

}

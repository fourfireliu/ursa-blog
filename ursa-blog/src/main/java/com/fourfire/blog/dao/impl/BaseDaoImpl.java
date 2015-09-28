package com.fourfire.blog.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.fourfire.blog.dao.BaseDao;

public class BaseDaoImpl extends JdbcDaoSupport implements BaseDao {

	@Override
	public List queryAllDataBySql(String sql) {	
		try {
			return super.getJdbcTemplate().queryForList(sql);
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public Map queryOneDateBySql(String sql) {
		try {
			return super.getJdbcTemplate().queryForMap(sql);
		} catch (Exception e) {
			
		}
		return null;
		
	}

	@Override
	public int queryCountBySql(String sql) {
		try {
				return super.getJdbcTemplate().queryForObject(sql, Integer.class);
			} catch (Exception e) {
				
		}
		return 0;
	}
	
	public int update(String sql){
		
		try {
			
			return super.getJdbcTemplate().update(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
		
	}

	@Override
	public boolean checkIsblackIp(String ip) {
		int res=super.getJdbcTemplate().queryForObject("select count(*) as count from black where ip='"+ip+"'", Integer.class);
		if(res>0){
			return true;
		}
		return false;
	}

}

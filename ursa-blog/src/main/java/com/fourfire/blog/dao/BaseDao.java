package com.fourfire.blog.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao{
	
	public List queryAllDataBySql(String sql);
	public Map queryOneDateBySql(String sql);
	public int queryCountBySql(String sql);
	public int update(String sql);
	public boolean checkIsblackIp(String ip);
}

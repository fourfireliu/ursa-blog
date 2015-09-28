package com.fourfire.blog.dao.impl;

import java.util.List;
import java.util.Map;

import com.fourfire.blog.dao.TypeInfoDao;

public class TypeInfoDaoImpl extends BaseDaoImpl implements TypeInfoDao {

	@Override
	public List getAllTypeInfo() {
		String sql="SELECT id,typeName,shortName,isFType,fTypeId from typeinfo order by orderby";
		
		return super.queryAllDataBySql(sql);
	}

	
	@Override
	public List getFatherTypeInfo() {
		
		String sql="SELECT id,typeName,shortName,isFType from typeinfo where isFType=1 order by orderby";
		
		return super.queryAllDataBySql(sql);
	}
	
	public Map<String, Object> getTypeInfoById(int id) {
		
		String sql="select id,typeName,isFType from typeinfo where id="+id;
		
		return super.queryOneDateBySql(sql);
		
	}

	@Override
	public List getChildTypeInfo(int fid) {
		String sql="SELECT id,typeName,shortName,isFType from typeinfo where fTypeId="+fid+" order by orderby";
		
		return super.queryAllDataBySql(sql);
	}

}

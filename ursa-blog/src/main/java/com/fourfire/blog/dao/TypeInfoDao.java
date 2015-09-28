package com.fourfire.blog.dao;

import java.util.List;
import java.util.Map;

public interface TypeInfoDao {

	public List getFatherTypeInfo();
	public List getChildTypeInfo(int fid);
	public Map getTypeInfoById(int id);
	public List getAllTypeInfo();
}

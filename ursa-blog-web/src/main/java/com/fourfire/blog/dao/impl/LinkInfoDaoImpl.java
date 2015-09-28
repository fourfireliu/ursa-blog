package com.fourfire.blog.dao.impl;

import java.util.List;

import com.fourfire.blog.dao.LinkInfoDao;

public class LinkInfoDaoImpl extends BaseDaoImpl implements LinkInfoDao {

	@Override
	public List getAllLinkInfo() {
		
		String sql="select linkname,linkurl from linkinfo ORDER BY orderby";
		return super.queryAllDataBySql(sql);
	}

}

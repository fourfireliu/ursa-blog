package com.fourfire.blog.dao.impl;

import java.util.List;

import com.fourfire.blog.dao.TagInfoDao;

public class TagInfoDaoImpl extends BaseDaoImpl implements TagInfoDao {

	@Override
	public List getAlltagInfo() {
		
		String sql="select distinct(tagname) from taginfo";
		
		return super.queryAllDataBySql(sql);
	}
	
	@Override
	public List getHotTagInfo() {
		
		String sql="select tagName,count(tagName) as count from taginfo GROUP BY tagName ORDER BY count desc limit 0,8";
		
		return super.queryAllDataBySql(sql);
	}

	@Override
	public List getTagListByArticleId(int id) {
		String sql="select distinct(tagname) from taginfo where articleId="+id;
		return super.queryAllDataBySql(sql);
	}
	
	

}

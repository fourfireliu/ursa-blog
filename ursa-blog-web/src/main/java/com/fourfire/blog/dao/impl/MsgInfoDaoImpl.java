package com.fourfire.blog.dao.impl;

import java.util.List;

import com.fourfire.blog.dao.MsgInfoDao;
import com.fourfire.blog.model.PageModel;

public class MsgInfoDaoImpl extends BaseDaoImpl implements MsgInfoDao {
	
	
	public List getNew10MsgInfo(){
		
		String sql="select id,username,content,date,mail_qq from msginfo order by id desc limit 0,10";
		
		return super.queryAllDataBySql(sql);
	}
	
	public PageModel getMsgInfoByPage(int page,int pageCount){
		
		String sql_num="select count(*) from msginfo";
		
		String sql="select * from msginfo order by id desc limit "+(page-1)*pageCount+","+pageCount;
		
		PageModel pm=new PageModel();
		pm.setCurrentPage(page);
		pm.setPageCount(pageCount);
		pm.setSumCount(super.queryCountBySql(sql_num));
		pm.setList(super.queryAllDataBySql(sql));
		
		return pm;
		
	}
	
	public int saveMsg(String sql){
		
		return super.update(sql);
		
	}

}

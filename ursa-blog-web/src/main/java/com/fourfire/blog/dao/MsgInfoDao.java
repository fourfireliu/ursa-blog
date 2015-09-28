package com.fourfire.blog.dao;

import java.util.List;

import com.fourfire.blog.model.PageModel;

public interface MsgInfoDao {
	
	
	public List getNew10MsgInfo();
	
	public PageModel getMsgInfoByPage(int page,int pageCount);
	
	public int saveMsg(String sql);

}

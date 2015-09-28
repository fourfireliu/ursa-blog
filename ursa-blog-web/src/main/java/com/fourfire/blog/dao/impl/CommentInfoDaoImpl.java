package com.fourfire.blog.dao.impl;

import java.util.List;

import com.fourfire.blog.dao.CommentInfoDao;



public class CommentInfoDaoImpl extends BaseDaoImpl implements CommentInfoDao {

	/**
	 * 首页查询10条最新评论
	 * 
	 * */
	public List getNewTop10CommentInfo() {
		String sql="select c.id,c.uname,c.date,c.content,c.articleId,c.mail_qq from comment as c left join articleinfo as a on c.articleId=a.id and a.status=1 and type=1 order by c.id desc limit 0,10";
		return super.queryAllDataBySql(sql);
	}

	/**
	 * 查询日志下面的评论
	 * 
	 * */
	public List getArticleCommentByArticleId(int id) {
		
		String sql="select id,uname,content,date,mail_qq from comment where articleId="+id+" order by id desc";
		return super.queryAllDataBySql(sql);
	}
	
}

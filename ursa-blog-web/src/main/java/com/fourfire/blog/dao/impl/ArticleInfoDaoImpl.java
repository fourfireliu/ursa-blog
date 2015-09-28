package com.fourfire.blog.dao.impl;

import java.util.List;
import java.util.Map;

import com.fourfire.blog.dao.ArticleInfoDao;
import com.fourfire.blog.model.PageModel;

public class ArticleInfoDaoImpl extends BaseDaoImpl implements ArticleInfoDao {

	/**
	 * 首页最新日志
	 * 
	 * */
	public List getNewAricle() {
		
		String sql="select a.id,a.title,a.typeId,a.readCount,a.content,t.typeName,a.date from articleinfo as a,typeinfo as t where a.typeId=t.id and a.status=1 and a.type=1 ORDER BY id desc limit 0,10";
		return super.queryAllDataBySql(sql);
		
	}
	
	/**
	 * 首页置顶日志
	 * */
	public List getTopAricle() {
		
		String sql="select a.id,a.title,a.typeId,a.readCount,t.typeName,a.date from articleinfo as a,typeinfo as t where a.typeId=t.id and a.status=1 and a.type=1 and top=1 ORDER BY id desc limit 0,10";
		return super.queryAllDataBySql(sql);
		
	}
	
	
	/**
	 * 首页推荐日志
	 * 
	 * */
	public List getCommendAricle() {
		
		String sql="select a.id,a.title,a.typeId,a.readCount,t.typeName,a.commendCount,a.date from articleinfo as a,typeinfo as t where a.typeId=t.id and a.status=1 and a.type=1 ORDER BY a.commendCount desc,a.id desc limit 0,10";
		return super.queryAllDataBySql(sql);
		
	}
	
	/**
	 * 推荐日志人数加1
	 * 
	 * */
	public int commendAricle(int id) {
		
		String sql="update articleinfo set commendCount=commendCount+1 where id="+id;
		return super.update(sql);
		
	}

	/**
	 * 首页热门点击日志
	 * 
	 * */
	public List getHotAricle() {
	
		String sql="select a.id,a.title,a.typeId,t.typeName,a.date,a.readCount from articleinfo as a,typeinfo as t where a.typeId=t.id and a.status=1 and a.type=1 ORDER BY a.readcount desc limit 0,10";
		return super.queryAllDataBySql(sql);
	
	}
	
	/**
	 * 首页热评日志
	 * 
	 * */
	public List getHotCommentAricle() {
	
		String sql="select a.id,a.type,a.typeId,t.typeName,a.title,a.content,a.author,a.readCount,a.commentCount,a.status,a.date,t.shortName from (select *,(select count(*) from comment where articleId=a.id) as commentCount from articleinfo as a WHERE a.status=1 and a.type=1) as a left join typeinfo as t on a.typeId=t.id ORDER BY a.commentCount desc,id desc LIMIT 0,10";
		return super.queryAllDataBySql(sql);
	
	}
	
	/**
	 * 首页最近心情
	 * 
	 * */
	public String getNearMood() {
	
		String sql="select content from articleinfo where type=0 order by id desc LIMIT 0,1";
		Map<String, Object> article= super.queryOneDateBySql(sql);
		if(article!=null&&article.get("content")!=null){
			return article.get("content")+"";
		}
		return "最近比较懒，没什么好心情...";
	}
	
	/**
	 * 
	 * 根据类型编号查询日志
	 * 
	 * */
	public List getTop10AricleByTypeId(int typeId){
		
		String sql="select id,title,readCount,date from articleinfo where typeId="+typeId+" order by readCount desc,id desc limit 0,10";
		return super.queryAllDataBySql(sql);
	}
	

	/**
	 * 根据Id查询日志
	 * 
	 * */
	public  Map<String, Object> getAticleInfoById(int id) {
		
		String sql="select a.id,a.type,a.typeId,t.typeName,a.title,a.commendCount,a.content,a.author,a.readCount,a.commentCount,a.status,a.date,t.shortName from (select *,(select count(*) from comment where articleId=a.id) as commentCount from articleinfo as a where id="+id+") as a left join typeinfo as t on a.typeId=t.id";
		
		return super.queryOneDateBySql(sql);
	}
	
	/**
	 * 上一篇
	 * 
	 * */
	public Map<String, Object> getUpArticleInfo(int id){
		
		String sql="select id,title from articleinfo where id>"+id+" LIMIT 0,1";
		
		return super.queryOneDateBySql(sql);
	}
	
	
	/**
	 * 下一篇
	 * 
	 * */
	public Map<String, Object> getDownArticleInfo(int id){
		
		String sql="select id,title from (select id,title from articleinfo ORDER BY id desc)as a where id<"+id+" LIMIT 0,1";
		
		return super.queryOneDateBySql(sql);
	}

	/**
	 * 分页查询日志
	 * 
	 * */
	@Override
	public PageModel getArticleInfoByPage(int typeId,int page,int pageCount) {
		StringBuffer sql_num=new StringBuffer("select count(*) from articleinfo where status=1 and type=1");
		StringBuffer sql=new StringBuffer("select a.id,a.title,a.typeId,a.readCount,a.commendCount,t.typeName,a.date,(select count(*) from `comment` where id=a.id) commentCount from articleinfo as a,typeinfo as t where a.typeId=t.id and a.status=1 and a.type=1");
		
		if(typeId>0){
			sql.append(" and a.typeId="+typeId);
			sql_num.append(" and typeId="+typeId);
		}
		
		sql.append(" ORDER BY id desc limit "+(page-1)*pageCount+","+pageCount+"");
		
		PageModel pm=new PageModel();
		pm.setCurrentPage(page);
		pm.setPageCount(pageCount);
		pm.setSumCount(super.queryCountBySql(sql_num.toString()));
		pm.setList(super.queryAllDataBySql(sql.toString()));
		
		return pm;
	}
	
	
	/**
	 * 搜索分页查询日志
	 * 
	 * */
	@Override
	public PageModel getArticleInfoByKey(String key,int page,int pageCount) {
		System.out.println(key);
		StringBuffer sql_num=new StringBuffer("select count(*) from (select DISTINCT articleId from taginfo WHERE tagName like '%"+key+"%' union select id  from articleinfo where title like '%"+key+"%' or content like '%"+key+"%') as i  left join articleinfo as a on a.id=i.articleId left join typeinfo t on a.typeId=t.id where a.status=1 and a.type=1 order by a.id ");
		StringBuffer sql=new StringBuffer("select a.id,a.title,a.readCount,a.typeId,t.typeName,a.date,a.commendCount,(select count(*) from `comment` where id=a.id) commentCount from (select DISTINCT articleId from taginfo WHERE tagName like '%"+key+"%' union select id  from articleinfo where title like '%"+key+"%' or content like '%"+key+"%') as i  left join articleinfo as a on a.id=i.articleId left join typeinfo t on a.typeId=t.id where a.status=1 and a.type=1 order by a.id");

		sql.append(" limit "+(page-1)*pageCount+","+pageCount+"");
		
		PageModel pm=new PageModel();
		pm.setKeyWords(key);
		pm.setCurrentPage(page);
		pm.setPageCount(pageCount);
		pm.setSumCount(super.queryCountBySql(sql_num.toString()));
		pm.setList(super.queryAllDataBySql(sql.toString()));
		
		return pm;
	}
	
	
	/**
	 * sava评论
	 * 
	 * */
	
	public int savaComment(String sql){
		
		return super.update(sql);
		
	}
	
	
	public int addReadCountByArticleId(int id){
		
		String sql="update articleinfo set readCount=readCount+1 where id="+id;
		return super.update(sql);
		
		
	}
	
	
	public List getAllAricle(){
		
		return super.queryAllDataBySql("select id from articleinfo where type=1 and status=1 order by id desc");
		
	}
	
	
	public int addArticle(String sql){
		
		return super.update(sql);
		
	}
	
	public int getUpdateArticleId(){
		String sql="select id from articleinfo order by id desc limit 0,1";
		return super.queryCountBySql(sql);
	}
	
	public int update(String sql){
		return super.update(sql);
	}
}

package com.fourfire.blog.dao;

import java.util.List;
import java.util.Map;

import com.fourfire.blog.model.PageModel;



public interface ArticleInfoDao {
	
	public List getAllAricle();
	public List getTopAricle();
	public List getNewAricle();
	public int update(String sql);
	public List getCommendAricle();
	
	public int commendAricle(int id);

	public List getHotAricle();
	
	public List getHotCommentAricle();
	
	public List getTop10AricleByTypeId(int typeId);
	
	public String getNearMood();
	
	public Map<String, Object> getAticleInfoById(int id);
	
	public Map<String, Object> getDownArticleInfo(int id);

	public Map<String, Object> getUpArticleInfo(int id);
	
	public PageModel getArticleInfoByPage(int typeId,int page,int pageCount);
	
	public PageModel getArticleInfoByKey(String key,int page,int pageCount);
	
	public int savaComment(String sql);
	
	public int addArticle(String sql);
	
	public int addReadCountByArticleId(int id);
	
	public int getUpdateArticleId();
	
}

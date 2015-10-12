package com.fourfire.blog.mapper;

import java.util.List;

import com.fourfire.blog.page.ArticlePageQuery;
import com.fourfire.blog.po.ArticleInfoPO;

/**
 * ArticleInfo表的Mapper
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public interface ArticleInfoMapper {
	public int insert(ArticleInfoPO articleInfoPO);
	public int updateById(ArticleInfoPO articleInfoPO);
	public ArticleInfoPO getArticleInfoById(long id);
	public ArticleInfoPO getUpArticleInfo(long id);
	public ArticleInfoPO getDownArticleInfo(long id);
	public List<ArticleInfoPO> pageQuery(ArticlePageQuery pageQuery);
	public int addReadCountById(long id);
}

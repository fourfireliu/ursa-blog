package com.fourfire.blog.mapper;

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
}

package com.fourfire.blog.mapper;

import com.fourfire.blog.po.ArticleInfoPO;

public interface ArticleInfoMapper {
	public int insert(ArticleInfoPO articleInfoPO);
	
	public int updateById(ArticleInfoPO articleInfoPO);
}

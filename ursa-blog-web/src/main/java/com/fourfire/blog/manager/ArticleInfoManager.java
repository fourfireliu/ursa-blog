package com.fourfire.blog.manager;

import javax.annotation.Resource;

import com.fourfire.blog.mapper.ArticleInfoMapper;
import com.fourfire.blog.vo.ArticleInfoVO;

public class ArticleInfoManager {
	@Resource
	private ArticleInfoMapper articleInfoMapper;
	
	public boolean addOrUpdateArticle(ArticleInfoVO articleInfoVO) {
		return false;
	}
}

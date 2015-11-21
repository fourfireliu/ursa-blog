package com.fourfire.blog.mapper;

import java.util.List;

import com.fourfire.blog.page.ArticlePageQuery;
import com.fourfire.blog.po.ArticleInfoPO;

public interface ArticleInfoPOMapper {
    int deleteByPrimaryKey(long id);

    int insert(ArticleInfoPO record);

    int insertSelective(ArticleInfoPO record);

    ArticleInfoPO selectByPrimaryKey(long id);
    
    int updateByPrimaryKey(ArticleInfoPO articleInfoPO);
    
    ArticleInfoPO getUpArticleInfo(long id);
    
    ArticleInfoPO getDownArticleInfo(long id);
    
    List<ArticleInfoPO> pageQuery(ArticlePageQuery articlePageQuery);
    
    int addReadCountById(long id);
}
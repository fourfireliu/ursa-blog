package com.fourfire.blog.mapper;

import java.util.List;

import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.query.ArticlePageQuery;

public interface ArticleInfoPOMapper {
    int deleteByPrimaryKey(long id);

    int insert(ArticleInfoPO record);

    int insertSelective(ArticleInfoPO record);

    ArticleInfoPO selectByPrimaryKey(long id);
    
    int updateByPrimaryKey(ArticleInfoPO articleInfoPO);
    
    ArticleInfoPO getUpOrDownArticleInfo(long id, int typeId, boolean isUp);
    
    List<ArticleInfoPO> pageQuery(ArticlePageQuery articlePageQuery);
    
    int addReadCountById(long id);
}
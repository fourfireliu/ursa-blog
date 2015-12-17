package com.fourfire.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fourfire.blog.po.ArticleInfoPO;
import com.fourfire.blog.query.ArticlePageQuery;

public interface ArticleInfoPOMapper {
    int deleteByPrimaryKey(long id);

    int insert(ArticleInfoPO record);

    int insertSelective(ArticleInfoPO record);

    ArticleInfoPO selectByPrimaryKey(@Param("id") long id);
    
    int updateByPrimaryKey(ArticleInfoPO articleInfoPO);
    
    ArticleInfoPO getUpOrDownArticleInfo(@Param("id") long id, @Param("typeId") int typeId, @Param("isUp") boolean isUp);
    
    List<ArticleInfoPO> pageQuery(ArticlePageQuery articlePageQuery);
    
    int addReadCountById(long id);
    
    int getArticleCount(@Param("typeId") int typeId);
}
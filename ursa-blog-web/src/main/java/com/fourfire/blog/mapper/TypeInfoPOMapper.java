package com.fourfire.blog.mapper;

import java.util.List;

import com.fourfire.blog.po.TypeInfoPO;
import com.fourfire.blog.query.BasePageQuery;

public interface TypeInfoPOMapper {
    int deleteByPrimaryKey(int id);

    int insert(TypeInfoPO record);

    TypeInfoPO selectByPrimaryKey(int id);
    
    List<TypeInfoPO> pageQuery(BasePageQuery pageQuery);
}
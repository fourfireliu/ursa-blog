package com.fourfire.blog.mapper;

import java.util.List;

import com.fourfire.blog.po.TypeInfoPO;

public interface TypeInfoMapper {
	public List<TypeInfoPO> getAllTypes();
	public int insertType(TypeInfoPO typeInfoPO);
	public int countTypeByName(String name);
}

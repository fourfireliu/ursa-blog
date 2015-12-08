package com.fourfire.blog.convert;

import java.util.ArrayList;
import java.util.List;

import com.fourfire.blog.po.TypeInfoPO;
import com.fourfire.blog.vo.TypeInfoVO;

public class TypeInfoConverter {
	public static List<TypeInfoVO> convertListFromPOToVO(List<TypeInfoPO> typeInfoPOList) {
		List<TypeInfoVO> typeInfoVOList = new ArrayList<TypeInfoVO>();
		if (typeInfoPOList == null) {
			return typeInfoVOList;
		}
		
		for (TypeInfoPO typeInfoPO:typeInfoPOList) {
			TypeInfoVO typeInfoVO = convertFromPOToVO(typeInfoPO);
			if (typeInfoVO != null) {
				typeInfoVOList.add(typeInfoVO);
			}
		}
		
		return typeInfoVOList;
	}
	
	public static TypeInfoVO convertFromPOToVO(TypeInfoPO typeInfoPO) {
		if (typeInfoPO == null) {
			return null;
		}
		
		TypeInfoVO typeInfoVO = new TypeInfoVO();
		typeInfoVO.setId(typeInfoPO.getId());
		typeInfoVO.setName(typeInfoPO.getName());
		typeInfoVO.setDescription(typeInfoPO.getDescription());
		typeInfoVO.setIconUrl(typeInfoPO.getIconUrl());
		typeInfoVO.setArticleCount(typeInfoPO.getArticleCount());
		typeInfoVO.setCreateDate(typeInfoPO.getCreateGmtDate());
		typeInfoVO.setModifyDate(typeInfoPO.getModifyGmtDate());
		
		return typeInfoVO;
	}
	
	public static TypeInfoPO convertFromVOToPO(TypeInfoVO typeInfoVO) {
		if (typeInfoVO == null) {
			return null;
		}
		
		TypeInfoPO typeInfoPO = new TypeInfoPO();
		typeInfoPO.setDescription(typeInfoVO.getDescription());
		typeInfoPO.setName(typeInfoVO.getName());
		typeInfoPO.setIconUrl(typeInfoVO.getIconUrl());
		typeInfoPO.setArticleCount(typeInfoVO.getArticleCount());
		typeInfoPO.setCreateGmtDate(typeInfoVO.getCreateDate());
		typeInfoPO.setModifyGmtDate(typeInfoVO.getModifyDate());
		
		return typeInfoPO;
	}
}
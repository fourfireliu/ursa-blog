package com.fourfire.blog.controller;

import java.util.List;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.vo.TypeInfoVO;

@Controller
public class TypeInfoController{
	Logger logger = LogManager.getLogger(TypeInfoController.class);
	
	@Resource
	private TypeInfoManager typeInfoManager; 
	
	@RequestMapping(value="/typelist")
	public String getTypeInfoList(ModelMap modelMap) {
		try {
			List<TypeInfoVO> allTypes = typeInfoManager.getAllTypeInfos();
			if (allTypes == null) {
				logger.error("index method get type list null");
			} else {
				//所有文章类型列表
				modelMap.put("typeInfos", allTypes);
			}
		} catch (Exception e) {
			logger.error("unknown error", e);
		}
		
		return "page/typelist";
	}
}

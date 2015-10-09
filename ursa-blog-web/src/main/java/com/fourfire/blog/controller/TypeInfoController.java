package com.fourfire.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfire.blog.manager.TypeInfoManager;
import com.fourfire.blog.vo.TypeInfoVO;

@Controller
@RequestMapping(value="/type")
public class TypeInfoController{
	Logger logger = LogManager.getLogger(TypeInfoController.class);
	
	@Resource
	private TypeInfoManager typeInfoManager; 
	
	@RequestMapping(value="/list")
	public String getTypeInfoList(HttpServletRequest request, HttpServletResponse response) {
		List<TypeInfoVO> typeInfoVOList = typeInfoManager.getAllTypeInfos();
		if (typeInfoVOList == null) {
			typeInfoVOList = new ArrayList<TypeInfoVO>();
		}
		
		request.setAttribute("typeList", typeInfoVOList);
		
		return "/type/list";
	}
	
	@RequestMapping(value="new")
	public String addNewType(HttpServletRequest request, HttpServletResponse response) {
		String name = ServletRequestUtils.getStringParameter(request, "name", "新的分类");
		String description = ServletRequestUtils.getStringParameter(request, "description", "新的分类");
		
	}
	
}

package com.fourfire.blog.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fourfire.blog.convert.TypeInfoConverter;
import com.fourfire.blog.mapper.TypeInfoMapper;
import com.fourfire.blog.po.TypeInfoPO;
import com.fourfire.blog.vo.TypeInfoVO;

/**
 * 文章标签相关操作的控制类, 与数据层交互
 * 
 * @author liuyi
 * @date 2015-09-29
 */
public class TypeInfoManager {
Logger logger = LogManager.getLogger(TypeInfoManager.class);
	
	@Resource
	private TypeInfoMapper typeInfoMapper;
	
	/**
	 * 由于类别不可能多 可以一次取出
	 */
	public List<TypeInfoVO> getAllTypeInfos() {
		List<TypeInfoPO> typeInfoPOList = typeInfoMapper.getAllTypes();
		List<TypeInfoVO> typeInfoVOList = new ArrayList<TypeInfoVO>();
		if (typeInfoPOList != null && typeInfoPOList.size() > 0) {
			typeInfoVOList = TypeInfoConverter.convertListFromPOToVO(typeInfoPOList);
		}
		
		return typeInfoVOList;
	}
}

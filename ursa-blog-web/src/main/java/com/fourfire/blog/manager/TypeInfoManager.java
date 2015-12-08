package com.fourfire.blog.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fourfire.blog.constant.BlogConstant;
import com.fourfire.blog.convert.TypeInfoConverter;
import com.fourfire.blog.mapper.TypeInfoPOMapper;
import com.fourfire.blog.page.PageResult;
import com.fourfire.blog.page.TypeInfoPageQuery;
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
	private TypeInfoPOMapper typeInfoPOMapper;
	
	/**
	 * 获取所有的文章类型列表
	 */
	public List<TypeInfoVO> getAllTypeInfos() {
		PageResult<TypeInfoVO> result = pageQueryTypeInfos(-1, -1);
		if (result == null || !result.isSuccess() || result.getPageResult() == null) {
			return null;
		}
		
		return result.getPageResult();
	}
	
	/**
	 * 由于类别不可能多 可以一次取出
	 */
	public PageResult<TypeInfoVO> pageQueryTypeInfos(int pageNo, int pageSize) {
		if (pageNo < 0) {
			pageNo = 0;
		}
		
		if (pageSize <= 0) {
			pageSize = BlogConstant.DEFAULT_PAGE_SIZE;
		}
		
		TypeInfoPageQuery pageQuery = new TypeInfoPageQuery();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setCheckNextPage(true);
		
		PageResult<TypeInfoVO> pageResult = new PageResult<TypeInfoVO>();
		pageResult.setPageNo(pageQuery.getPageNo());
		pageResult.setPageSize(pageQuery.getOldPageSize());
		
		List<TypeInfoPO> typeInfoPOList = typeInfoPOMapper.pageQuery(pageQuery);
		if (pageQuery.isCheckNextPage()) {
			if (typeInfoPOList != null && typeInfoPOList.size() > pageQuery.getOldPageSize()) {
				pageResult.setHasNext(true);
				typeInfoPOList.remove(typeInfoPOList.size() - 1);
			}
		}
		List<TypeInfoVO> typeInfoVOList = TypeInfoConverter.convertListFromPOToVO(typeInfoPOList);
		pageResult.setPageResult(typeInfoVOList);
		if (typeInfoVOList == null) {
			logger.error("pageQueryTypeInfos==>typeInfoVOList: " + typeInfoVOList);
		} else {
			pageResult.setSuccess(true);
		}
		
		return pageResult;
	}
	
	public boolean addNewType(TypeInfoVO typeInfoVO) {
		TypeInfoPO typeInfoPO = TypeInfoConverter.convertFromVOToPO(typeInfoVO);
		if (typeInfoPO == null || StringUtils.isBlank(typeInfoPO.getName())) {
			return false;
		}
		
		int result = typeInfoPOMapper.insert(typeInfoPO);
		if (result != 1) {
			return false;
		}
		
		return true;
	}
}

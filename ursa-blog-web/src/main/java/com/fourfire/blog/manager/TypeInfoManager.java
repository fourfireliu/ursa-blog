package com.fourfire.blog.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fourfire.blog.constant.BlogConstant;
import com.fourfire.blog.convert.TypeInfoConverter;
import com.fourfire.blog.mapper.TypeInfoPOMapper;
import com.fourfire.blog.po.TypeInfoPO;
import com.fourfire.blog.query.TypeInfoPageQuery;
import com.fourfire.blog.result.BaseResult;
import com.fourfire.blog.result.ErrorInfo;
import com.fourfire.blog.result.PageResult;
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
	 * 根据ID获取分类详情
	 */
	public BaseResult<TypeInfoVO> getTypeInfoById(int typeId) {
		BaseResult<TypeInfoVO> baseResult = new BaseResult<TypeInfoVO>();
		if (typeId <= 0) {
			baseResult.setErrorInfo(ErrorInfo.INVALID_PARAM);
			return baseResult;
		}
		
		TypeInfoPO typeInfoPO = typeInfoPOMapper.selectByPrimaryKey(typeId);
		TypeInfoVO typeInfoVO = TypeInfoConverter.convertFromPOToVO(typeInfoPO);
		if (typeInfoVO == null) {
			logger.error("getTypeInfoById==>typeInfoVO:" + typeInfoVO);
			baseResult.setErrorInfo(ErrorInfo.INVALID_RESULT);
		} else {
			baseResult.setSuccess(true);
		}
		baseResult.setT(typeInfoVO);
		
		return baseResult;
	}
	
	public BaseResult<Boolean> addArticleCountInType(int typeId, int num) {
		BaseResult<Boolean> result = new BaseResult<Boolean>();
		if (typeId <= 0) {
			logger.error("addArticleCountInType invalid parameter, typeId=" + typeId);
			result.setErrorInfo(ErrorInfo.INVALID_PARAM);
			return result;
		}
		
		int affectCount = typeInfoPOMapper.addArticleCountInType(typeId, num);
		if (affectCount != 1) {
			logger.error("addArticleCountInType update article count failed, affectCount=" + affectCount);
			result.setErrorInfo(ErrorInfo.UPDATE_DB_RESULT_ERROR);
			return result;
		}
		
		result.setSuccess(true);
		result.setT(true);
		
		return result;
	}
	
	/**
	 * 由于类别不可能多 可以一次取出
	 */
	public PageResult<TypeInfoVO> pageQueryTypeInfos(int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		
		if (pageSize <= 0) {
			pageSize = BlogConstant.DEFAULT_TYPE_PAGE_SIZE;
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
}

package com.fourfire.blog.manager;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fourfire.blog.mapper.BlackIpMapper;

/**
 * 黑名单相关操作控制类, 与数据库交互
 * 
 * @author liuyi
 *
 */
public class BlackIpManager {
	private Logger logger = LogManager.getLogger(BlackIpManager.class);
	
	@Resource
	private BlackIpMapper blackIpMapper;
	
	/**
	 * 校验是否已加入黑名单
	 */
	public boolean checkIsBlack(String ip) {
		if (StringUtils.isBlank(ip)) {
			return false;
		}
		
		if (blackIpMapper.getBlackCountByIp(ip) > 0) {
			return true;
		}
		
		return false;
	}
}

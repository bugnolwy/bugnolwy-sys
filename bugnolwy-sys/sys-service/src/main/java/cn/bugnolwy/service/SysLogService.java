package cn.bugnolwy.service;

import cn.bugnolwy.annotation.RequiredLog;
import cn.bugnolwy.mapper.SysLogMapper;
import cn.bugnolwy.model.SysLog;
import cn.bugnolwy.util.Assert;
import cn.bugnolwy.util.PageProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 日志管理
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Service
public class SysLogService extends ServiceImpl<SysLogMapper, SysLog> {
	@Autowired
	private PageProperties pageProperties;
	
	public IPage<SysLog> findPageObjects(String username, Integer pageCurrent) {
		// 条件
		QueryWrapper<SysLog> queryWrapper = new QueryWrapper<SysLog>()
				.like(!StringUtils.isEmpty(username), "username", username)
				.orderByDesc("create_time");
		
		// 校验
		Assert.isArgumentValid(pageCurrent == null || pageCurrent < 1, "页码值不正确");
		int total = count(queryWrapper);
		Assert.isServiceValid(total == 0, "没有找到对应记录");
		
		// 分页
		IPage<SysLog> logsPage = new Page<>(pageCurrent, pageProperties.getSize(), total, true);
		return page(logsPage, queryWrapper);
	}
	
	@RequiredLog(operation = "删除日志")
	public Boolean deleteObjects(Integer[] ids) {
		//校验
		Assert.isArgumentValid(ids == null || ids.length == 0, "未选中记录进行删除");
		return removeByIds(Arrays.asList(ids));
	}
	
	@Async
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void saveSysLog(SysLog sysLog) {
		save(sysLog);
	}
}

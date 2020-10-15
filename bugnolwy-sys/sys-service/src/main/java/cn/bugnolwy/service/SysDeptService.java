package cn.bugnolwy.service;

import cn.bugnolwy.annotation.RequiredLog;
import cn.bugnolwy.mapper.SysDeptMapper;
import cn.bugnolwy.model.SysDept;
import cn.bugnolwy.util.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Service
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> {
	
	public List<Map<String,Object>> findztreenodes() {
		// 条件
		List<Map<String, Object>> sysDepts = listMaps(
				new QueryWrapper<SysDept>().select("id", "name", "parentId"));
		
		// 校验
		Assert.isServiceValid(sysDepts == null || sysDepts.size() == 0, "没有部门信息");
		return sysDepts;
	}
	
	@RequiredLog(operation = "查询部门")
	public List<SysDept> findSysDepts() {
		return baseMapper.getSysDepts();
	}
	
	@RequiredLog(operation = "删除部门")
	public boolean deleteSysDept(Integer id) {
		// 校验
		Assert.isArgumentValid(id == null || id <= 1, "id值无效");
		
		// 条件
		QueryWrapper<SysDept> queryWrapper = new QueryWrapper<SysDept>().eq("parentId", id);
		
		// 校验
		Assert.isServiceValid(count(queryWrapper) > 0, "请先删除下属部门");
		return removeById(id);
	}
	
	@RequiredLog(operation = "更新部门")
	public boolean updateSysDept(SysDept sysDept) {
		// 校验
		Assert.isNull(sysDept, "保存对象不能为空");
		Assert.isEmpty(sysDept.getName(), "部门名不能为空");
		return updateById(sysDept);
	}
	
	@RequiredLog(operation = "新增部门")
	public boolean saveSysDept(SysDept sysDept) {
		// 校验
		Assert.isNull(sysDept, "保存对象不能为空");
		Assert.isEmpty(sysDept.getName(), "部门名不能为空");
		return save(sysDept);
	}
	
}

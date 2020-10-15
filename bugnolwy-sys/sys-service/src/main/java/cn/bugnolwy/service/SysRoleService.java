package cn.bugnolwy.service;

import cn.bugnolwy.annotation.RequiredLog;
import cn.bugnolwy.mapper.SysRoleMapper;
import cn.bugnolwy.mapper.SysRoleMenuMapper;
import cn.bugnolwy.mapper.SysUserRoleMapper;
import cn.bugnolwy.model.SysRole;
import cn.bugnolwy.model.SysRoleMenu;
import cn.bugnolwy.model.SysUserRole;
import cn.bugnolwy.model.vo.SysRoleVo;
import cn.bugnolwy.util.Assert;
import cn.bugnolwy.util.PageProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@CacheConfig(cacheNames = "BugnoLwy-role")
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {
	@Autowired(required = false)
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired(required = false)
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private PageProperties pageProperties;
	
	public List<Map<String,Object>> findRoles() {
		return listMaps(new QueryWrapper<SysRole>().select("id", "name"));
	}
	
	@Cacheable(key = "#id")
	public SysRoleVo findObjectByRoleId(Integer id) {
		// 校验
		Assert.isArgumentValid(id == null || id < 1, "id值无效");
		return baseMapper.findObjectById(id);
	}
	
	@RequiredLog(operation = "查询角色")
	public IPage<SysRole> findPageObjects(String name, Integer pageCurrent) {
		// 条件
		QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>()
				.like(!StringUtils.isEmpty(name), "name", name);
		
		// 校验
		Assert.isArgumentValid(pageCurrent == null || pageCurrent < 1, "页码值不正确");
		int size = count(wrapper);
		Assert.isServiceValid(size == 0, "没有找到对应记录");
		
		IPage<SysRole> rolesPage = new Page<>(pageCurrent, pageProperties.getSize(), size, true);
		return page(rolesPage, wrapper);
	}
	
	
	@RequiredLog(operation = "删除角色")
	public boolean deleteObject(Integer id) {
		// 校验
		Assert.isArgumentValid(id == null || id < 1, "id值无效");
		
		// 删除菜单角色关系数据
		sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
		
		// 删除角色用户关系数据和自身信息
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().eq("role_id", id));
		return removeById(id);
	}
	
	@RequiredLog(operation = "新增角色")
	public boolean saveObjects(SysRole sysRole, Integer[] menuIds) {
		// 校验
		Assert.isNull(sysRole, "保存对象不能为空");
		Assert.isEmpty(sysRole.getName(), "角色名不能为空");
		
		if (save(sysRole)) {
			// 插入角色菜单关系数据
			sysRoleMenuMapper.insertSysRoleMenus(sysRole.getId(), menuIds);
			return true;
		}
		return false;
	}
	
	@CacheEvict(key = "#sysRole.id")
	@RequiredLog(operation = "更新角色")
	public boolean updateObjects(SysRole sysRole, Integer[] menuIds) {
		// 校验
		Assert.isNull(sysRole, "保存对象不能为空");
		Assert.isEmpty(sysRole.getName(), "角色名不能为空");
		
		// 更新角色自身信息与角色菜单的关系数据
		sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", sysRole.getId()));
		sysRoleMenuMapper.insertSysRoleMenus(sysRole.getId(), menuIds);
		return updateById(sysRole);
	}
}

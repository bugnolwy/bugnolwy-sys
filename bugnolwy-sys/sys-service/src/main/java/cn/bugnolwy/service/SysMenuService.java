package cn.bugnolwy.service;

import cn.bugnolwy.annotation.RequiredLog;
import cn.bugnolwy.mapper.SysMenuMapper;
import cn.bugnolwy.mapper.SysRoleMenuMapper;
import cn.bugnolwy.model.SysMenu;
import cn.bugnolwy.model.SysRoleMenu;
import cn.bugnolwy.util.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Service
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu> {
	@Autowired(required = false)
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	public List<Map<String,Object>> findztreemenunodes() {
		return listMaps(new QueryWrapper<SysMenu>().select("id", "name", "parentId"));
	}
	
	@RequiredLog(operation = "查询菜单")
	public List<SysMenu> findSysMenus() {
		return baseMapper.findSysMenus();
	}
	
	@RequiredLog(operation = "删除菜单")
	public boolean deleteObject(Integer id) {
		// 校验
		Assert.isArgumentValid(id == null || id < 1, "id值无效");
		Assert.isServiceValid(count(new QueryWrapper<SysMenu>()
				.eq("parentId", id)) > 0, "请先删除子元素");
		
		// 删除菜单对应的关系数据
		sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>()
				.eq("menu_id", id));
		return removeById(id);
	}
	
	@RequiredLog(operation = "新增菜单")
	public boolean saveObject(SysMenu sysMenu) {
		// 校验
		Assert.isNull(sysMenu, "保存对象不能为空");
		Assert.isEmpty(sysMenu.getName(), "菜单名不能为空");
		return save(sysMenu);
	}
	
	@RequiredLog(operation = "更新菜单")
	public boolean updateObject(SysMenu sysMenu) {
		// 校验
		Assert.isNull(sysMenu, "保存对象不能为空");
		Assert.isEmpty(sysMenu.getName(), "菜单名不能为空");
		return updateById(sysMenu);
	}
	
}

package cn.bugnolwy.controller;

import cn.bugnolwy.model.SysRole;
import cn.bugnolwy.model.SysRoleMenu;
import cn.bugnolwy.service.SysRoleService;
import cn.bugnolwy.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * HandlerAdapter类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	@ApiOperation(value = "user_edit页面角色呈现")
	@GetMapping("/doFindRoles")
	public JsonResult doFindRoles() {
		return JsonResult.ok(sysRoleService.findRoles());
	}
	
	@ApiOperation(value = "分页查询角色", notes = "根据用户名模糊查询角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "角色名", dataType = "String", dataTypeClass = SysRole.class),
			@ApiImplicitParam(name = "pageCurrent", value = "当前页", dataType = "Integer", dataTypeClass = SysRole.class)
	})
	@GetMapping("/doFindPageObjects")
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		return JsonResult.ok(sysRoleService.findPageObjects(name, pageCurrent));
	}
	
	@ApiOperation(value = "查询菜单id", notes = "根据角色的id查询此角色拥有的菜单id")
	@ApiImplicitParam(name = "roleId", value = "角色id", dataType = "Integer", dataTypeClass = SysRole.class)
	@GetMapping("/doFindObjectByRoleId")
	public JsonResult doFindObjectById(Integer id) {
		return JsonResult.ok(sysRoleService.findObjectByRoleId(id));
	}
	
	@ApiOperation(value = "删除角色", notes = "根据id角色")
	@ApiImplicitParam(name = "id", value = "角色id", dataType = "Integer", dataTypeClass = SysRole.class)
	@DeleteMapping("/doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		if (sysRoleService.deleteObject(id)) {
			return JsonResult.ok("删除成功!");
		}
		return JsonResult.error("删除失败!");
	}
	
	@ApiOperation(value = "保存角色", notes = "根据角色设置菜单id参数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sysRole", value = "角色", dataType = "SysRole", dataTypeClass = SysRole.class),
			@ApiImplicitParam(name = "menuIds", value = "菜单id参数", dataType = "Integer", dataTypeClass = SysRoleMenu.class)
	})
	@PostMapping("/doSaveObject")
	public JsonResult doSaveObject(SysRole sysRole, Integer[] menuIds) {
		if (sysRoleService.saveObjects(sysRole, menuIds)) {
			return JsonResult.ok("保存成功!");
		}
		return JsonResult.error("保存失败!");
	}
	
	@ApiOperation(value = "更新角色", notes = "根据角色更新菜单id参数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sysRole", value = "角色", dataType = "SysRole", dataTypeClass = SysRole.class),
			@ApiImplicitParam(name = "menuIds", value = "菜单id参数", dataType = "Integer", dataTypeClass = SysRoleMenu.class)
	})
	@PostMapping("/doUpdateObject")
	public JsonResult doUpdateObject(SysRole sysRole, Integer[] menuIds) {
		if (sysRoleService.updateObjects(sysRole, menuIds)) {
			return JsonResult.ok("更新成功!");
		}
		return JsonResult.error("更新失败!");
	}
}

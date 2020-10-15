package cn.bugnolwy.controller;

import cn.bugnolwy.model.SysMenu;
import cn.bugnolwy.service.SysMenuService;
import cn.bugnolwy.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单管理
 * HandlerAdapter类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menu")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	
	@ApiOperation(value = "上级菜单呈现")
	@RequestMapping("/doFindZTreeMenuNodes")
	public JsonResult dofindztreemenunodes() {
		return JsonResult.ok(sysMenuService.findztreemenunodes());
	}
	
	@ApiOperation(value = "查询菜单")
	@GetMapping("/doFindObjects")
	public JsonResult findObjects() {
		return JsonResult.ok(sysMenuService.findSysMenus());
	}
	
	@ApiOperation(value = "删除菜单", notes = "根据接收的id参数进行删除菜单")
	@ApiImplicitParam(name = "id", value = "菜单id", dataType = "Integer", dataTypeClass = SysMenu.class)
	@DeleteMapping("/doDeleteObjects")
	public JsonResult doDeleteObjects(Integer id) {
		if (sysMenuService.deleteObject(id)) {
			return JsonResult.ok("删除成功!");
		}
		return JsonResult.error("删除失败!记录可能已经不存在...");
	}
	
	@ApiOperation(value = "添加菜单", notes = "根据菜单内容保存菜单")
	@ApiImplicitParam(name = "sysMenu", value = "菜单", dataType = "SysMenu", dataTypeClass = SysMenu.class)
	@PostMapping("/doSaveObject")
	public JsonResult doSaveObject(SysMenu sysMenu) {
		if (sysMenuService.saveObject(sysMenu)) {
			return JsonResult.ok("保存成功!");
		}
		return JsonResult.error("保存失败!");
	}
	
	@ApiOperation(value = "更新菜单", notes = "根据菜单内容更新菜单")
	@ApiImplicitParam(name = "sysMenu", value = "菜单", dataType = "SysMenu", dataTypeClass = SysMenu.class)
	@PostMapping("/doUpdateObject")
	public JsonResult doUpdateObject(SysMenu sysMenu) {
		if (sysMenuService.updateObject(sysMenu)) {
			return JsonResult.ok("更新成功!");
		}
		return JsonResult.error("更新失败!");
	}
}

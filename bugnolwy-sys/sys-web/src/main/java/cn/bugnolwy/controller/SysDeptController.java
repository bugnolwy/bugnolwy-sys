package cn.bugnolwy.controller;

import cn.bugnolwy.model.SysDept;
import cn.bugnolwy.service.SysDeptService;
import cn.bugnolwy.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理
 * HandlerAdapter类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/dept")
public class SysDeptController {
	@Autowired
	private SysDeptService sysDeptService;
	
	@ApiOperation(value = "查询部门")
	@RequestMapping("/doFindDepts")
	public JsonResult findPageDepts() {
		return JsonResult.ok(sysDeptService.findSysDepts());
	}
	
	@ApiOperation(value = "上级部门呈现")
	@GetMapping("/doFindZTreeNodes")
	public JsonResult doFindZtreeNodes() {
		return JsonResult.ok(sysDeptService.findztreenodes());
	}
	
	@ApiOperation(value = "删除部门", notes = "根据接收的id参数进行删除部门")
	@ApiImplicitParam(name = "id", value = "部门id", dataType = "Integer", dataTypeClass = SysDept.class)
	@DeleteMapping("/doDeleteSysDept")
	public JsonResult doDeleteSysDept(Integer id) {
		if (sysDeptService.deleteSysDept(id)) {
			return JsonResult.ok("删除成功!");
		}
		return JsonResult.ok("删除失败!记录可能已经不存在...");
	}
	
	@ApiOperation(value = "添加部门", notes = "根据菜单内容保存部门")
	@ApiImplicitParam(name = "sysDept", value = "部门", dataType = "SysDept", dataTypeClass = SysDept.class)
	@PostMapping("/doSaveSysDept")
	public JsonResult doSaveSysDept(SysDept sysDept) {
		if (sysDeptService.saveSysDept(sysDept)) {
			return JsonResult.ok("保存成功!");
		}
		return JsonResult.error("保存失败!");
	}
	
	@ApiOperation(value = "更新部门", notes = "根据菜单内容更新部门")
	@ApiImplicitParam(name = "sysDept", value = "菜单", dataType = "SysDept", dataTypeClass = SysDept.class)
	@PostMapping("/doUpdateSysDept")
	public JsonResult doUpdateSysDept(SysDept sysDept) {
		if (sysDeptService.updateSysDept(sysDept)) {
			return JsonResult.ok("更新成功!");
		}
		return JsonResult.error("更新失败!");
	}
}

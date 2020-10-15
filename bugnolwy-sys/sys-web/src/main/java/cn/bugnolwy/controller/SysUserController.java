package cn.bugnolwy.controller;

import cn.bugnolwy.model.SysUser;
import cn.bugnolwy.model.SysUserRole;
import cn.bugnolwy.service.SysUserService;
import cn.bugnolwy.vo.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * 用户管理
 * HandlerAdapter类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@ApiOperation(value = "user_edit页面呈现", notes = "根据id展示用户信息")
	@ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", dataTypeClass = SysUser.class)
	@GetMapping("/doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {
		return JsonResult.ok(sysUserService.findObjectById(id));
	}
	
	@ApiOperation(value = "分页查询用户", notes = "根据用户名模糊查询角色")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", dataTypeClass = SysUser.class),
			@ApiImplicitParam(name = "pageCurrent", value = "当前页", dataType = "Integer", dataTypeClass = Integer.class)
	})
	@GetMapping("/doFindPageObjects")
	public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
		return JsonResult.ok(sysUserService.findPageObjects(username, pageCurrent));
	}
	
	@ApiOperation(value = "用户禁用启用")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", dataTypeClass = SysUser.class),
			@ApiImplicitParam(name = "enabled", value = "用户状态", dataType = "Boolean", dataTypeClass = SysUser.class)
	})
	@PostMapping("/doEnableById")
	public JsonResult doEnableById(Integer id, Integer enabled) {
		if (sysUserService.enableById(id, enabled)) {
			return JsonResult.ok("状态修改成功!");
		}
		return JsonResult.error("状态修改失败!");
	}
	
	@ApiOperation(value = "保存用户", notes = "保存用户的信息和用户的角色id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sysUser", value = "用户", dataType = "String", dataTypeClass = SysUser.class),
			@ApiImplicitParam(name = "roleIds", value = "角色id参数", dataType = "Integer", dataTypeClass = SysUserRole.class)
	})
	@PostMapping("/doSaveObject")
	public JsonResult doSaveObject(SysUser sysUser, Integer[] roleIds) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().select("username");
		if (sysUserService.list(queryWrapper).parallelStream().map(SysUser::getUsername).
				collect(Collectors.toList()).contains(sysUser.getUsername())) {
			return JsonResult.error("用户名已存在,请修改!");
		} else if (sysUserService.saveObjects(sysUser, roleIds)) {
			return JsonResult.ok("保存成功!");
		}
		return JsonResult.error("保存失败!");
	}
	
	@ApiOperation(value = "更新用户", notes = "更新用户的信息和用户的角色id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sysUser", value = "用户", dataType = "String", dataTypeClass = SysUser.class),
			@ApiImplicitParam(name = "roleIds", value = "角色id参数", dataType = "Integer", dataTypeClass = SysUserRole.class)
	})
	@PostMapping("/doUpdateObject")
	public JsonResult doUpdateObject(SysUser sysUser, Integer[] roleIds) {
		if (sysUserService.updateObjects(sysUser, roleIds)) {
			return JsonResult.ok("更新成功!");
		}
		return JsonResult.error("更新失败!");
	}
	
	@ApiOperation(value = "修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "password", value = "密码", dataType = "String", dataTypeClass = SysUser.class),
			@ApiImplicitParam(name = "newPassword", value = "新密码", dataType = "String", dataTypeClass = SysUser.class),
			@ApiImplicitParam(name = "cfgPassword", value = "验证密码", dataType = "String", dataTypeClass = String.class)
	})
	@PostMapping("/doUpdatePassword")
	public JsonResult doUpdatePassword(String password, String newPassword, String cfgPassword) {
		if (sysUserService.updatePassword(password, newPassword, cfgPassword)) {
			return JsonResult.ok("密码修改成功!");
		}
		return JsonResult.error("密码修改失败!");
	}
}

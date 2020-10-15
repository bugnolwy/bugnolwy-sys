package cn.bugnolwy.controller;

import cn.bugnolwy.model.SysLog;
import cn.bugnolwy.service.SysLogService;
import cn.bugnolwy.vo.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理
 * HandlerAdapter类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Api(tags = "日志管理")
@RestController
@RequestMapping("/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	@ApiOperation(value = "分页查询日志", notes = "根据用户名模糊查询日志记录,按照创建时间降序排序")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "用户名", dataType = "String", dataTypeClass = SysLog.class),
			@ApiImplicitParam(name = "pageCurrent", value = "当前页", dataType = "Integer", dataTypeClass = Integer.class)
	})
	@GetMapping("/doFindPageObjects")
	public JsonResult findPageObjects(String username, Integer pageCurrent) {
		return JsonResult.ok(sysLogService.findPageObjects(username, pageCurrent));
	}
	
	
	@ApiOperation(value = "批量删除日志记录", notes = "根据接收的id参数进行批量删除日志记录")
	@ApiImplicitParam(name = "ids", value = "日志id参数", dataType = "Integer", dataTypeClass = SysLog.class)
	@DeleteMapping("/doDeleteObjects")
	public JsonResult doDeleteObjects(Integer[] ids) {
		if (sysLogService.deleteObjects(ids)) {
			return JsonResult.ok("删除成功!");
		}
		return JsonResult.error("删除失败!记录可能已经不存在...");
	}
	
}

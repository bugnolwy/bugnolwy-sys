package cn.bugnolwy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 控制层客户端响应数据
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
@AllArgsConstructor
public class JsonResult {
	/**
	 * 状态码
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	private Integer state;
	
	/**
	 * 状态信息
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	private String message;
	
	/**
	 * 控制层从业务获得数据
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	private Object data;
	
	private JsonResult(Integer state, String message) {
		this.state = state;
		this.message = message;
	}
	
	private JsonResult(Integer state, Object data) {
		this.state = state;
		this.data = data;
	}
	
	public JsonResult(Throwable throwable) {
		this.state = 500;
		this.message = throwable.getMessage();
	}
	
	public static JsonResult ok(String message) {
		return new JsonResult(200, message);
	}
	
	public static JsonResult ok(Object data) {
		return new JsonResult(200, data);
	}
	
	public static JsonResult ok(String msg, Object obj) {
		return new JsonResult(200, msg, obj);
	}
	
	public static JsonResult error(String msg) {
		return new JsonResult(500, msg);
	}
	
	public static JsonResult error(Object data) {
		return new JsonResult(500, data);
	}
}

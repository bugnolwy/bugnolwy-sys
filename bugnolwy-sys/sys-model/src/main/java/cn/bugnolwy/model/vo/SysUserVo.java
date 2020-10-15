package cn.bugnolwy.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户页面显示
 * vo类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
public class SysUserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@ApiModelProperty(value = "用户名")
	private String username;
	
	@ApiModelProperty(value = "邮箱")
	private String email;
	
	@ApiModelProperty(value = "手机号")
	private String phone;
	
	@ApiModelProperty(value = "用户状态")
	private Boolean enabled;
	
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime modifiedTime;
	
	@ApiModelProperty(value = "部门名称")
	private String deptName;
}

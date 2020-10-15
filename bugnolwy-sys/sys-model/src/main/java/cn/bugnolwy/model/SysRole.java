package cn.bugnolwy.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色表
 * 实体类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Role对象", description = "角色管理")
public class SysRole extends BaseModel {
	private static final long serialVersionUID = -356538509994150709L;
	
	@ApiModelProperty(value = "角色名称")
	private String name;
	
	@ApiModelProperty(value = "备注")
	private String note;
	
}

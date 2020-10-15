package cn.bugnolwy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门表
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
@ApiModel(value = "Dept对象", description = "部门管理")
public class SysDept extends BaseModel {
	private static final long serialVersionUID = 2134851025025637143L;
	
	@ApiModelProperty(value = "资源名称")
	private String name;
	
	@ApiModelProperty(value = "上级部门id")
	@TableField("parentId")
	private Integer parentId;
	
	@ApiModelProperty(value = "备注")
	private String note;
	
	@ApiModelProperty(value = "上级部门名称")
	@TableField(exist = false)
	private String parentName;
	
}

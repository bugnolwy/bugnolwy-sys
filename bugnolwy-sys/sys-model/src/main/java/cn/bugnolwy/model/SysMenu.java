package cn.bugnolwy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单表
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
@ApiModel(value = "Menus对象", description = "菜单管理")
public class SysMenu extends BaseModel {
	private static final long serialVersionUID = 3820536062098779606L;
	
	@ApiModelProperty(value = "资源名称")
	private String name;
	
	@ApiModelProperty(value = "资源URL")
	private String url;
	
	@ApiModelProperty(value = "类型 1:菜单 2:按钮")
	private Integer type;
	
	@ApiModelProperty(value = "父菜单ID")
	@TableField("parentId")
	private Integer parentId;
	
	@TableField(exist = false)
	private String parentName;
	
}

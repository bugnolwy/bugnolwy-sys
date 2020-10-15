package cn.bugnolwy.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色页面显示
 * vo类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
public class SysRoleVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@ApiModelProperty(value = "角色名称")
	private String name;
	
	@ApiModelProperty(value = "备注")
	private String note;
	
	@ApiModelProperty(value = "乐观锁")
	private Integer version;
	
	@ApiModelProperty(value = "菜单id")
	private List<Integer> menuIds;
}

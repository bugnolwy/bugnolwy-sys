package cn.bugnolwy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 角色与菜单对应关系表
 * 实体类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
public class SysRoleMenu {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	
	private Integer roleId;
	
	private Integer menuId;
}

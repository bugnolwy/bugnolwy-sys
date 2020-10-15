package cn.bugnolwy.mapper;

import cn.bugnolwy.model.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 角色菜单中间表
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	
	int insertSysRoleMenus(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);
}

package cn.bugnolwy.mapper;

import cn.bugnolwy.model.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 菜单管理
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	
	List<SysMenu> findSysMenus();
}

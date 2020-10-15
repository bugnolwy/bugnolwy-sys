package cn.bugnolwy.mapper;


import cn.bugnolwy.model.SysRole;
import cn.bugnolwy.model.vo.SysRoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 权限管理
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
	SysRoleVo findObjectById(Integer id);
	
}

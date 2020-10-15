package cn.bugnolwy.mapper;

import cn.bugnolwy.model.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色中间表
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	
	int insertSysUserRoles(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);
	
}

package cn.bugnolwy.mapper;

import cn.bugnolwy.model.SysUser;
import cn.bugnolwy.model.vo.LoginVo;
import cn.bugnolwy.model.vo.SysUserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	
	List<LoginVo> getLoginsById(Integer id);
	
	Map<String, Object> getObjectById(Integer id);
	
	IPage<SysUserVo> getSysUsersPage(Page<SysUser> page, QueryWrapper<SysUser> queryWrapper);
}

package cn.bugnolwy.service;

import cn.bugnolwy.mapper.LoginMapper;
import cn.bugnolwy.model.vo.LoginVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户权限认证
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Service
public class LoginService extends ServiceImpl<LoginMapper, LoginVo> {
	/**
	 * 获取角色拥有的url交给Security
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Cacheable(cacheNames = "BugnoLwy-userMenus")
	public List<LoginVo> getLoginFilterVos() {
		return baseMapper.getLoginFilterVos();
	}
	
	/**
	 * 根据角色id获取该角色的菜单
	 * 用于前端ui显示模块
	 *
	 * @param roleIds 角色id
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public List<LoginVo> getUserMenuNames(List<Integer> roleIds) {
		return baseMapper.getUserMenuNames(roleIds);
	}
}

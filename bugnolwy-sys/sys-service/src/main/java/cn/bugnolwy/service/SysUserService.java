package cn.bugnolwy.service;

import cn.bugnolwy.annotation.RequiredLog;
import cn.bugnolwy.mapper.SysUserMapper;
import cn.bugnolwy.mapper.SysUserRoleMapper;
import cn.bugnolwy.model.SysUser;
import cn.bugnolwy.model.SysUserRole;
import cn.bugnolwy.model.vo.SysUserVo;
import cn.bugnolwy.model.vo.UserMail;
import cn.bugnolwy.util.Assert;
import cn.bugnolwy.util.LoginUserUtil;
import cn.bugnolwy.util.PageProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 * 服务类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Transactional(timeout = 60, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
@CacheConfig(cacheNames = "BugnoLwy-user")
@Slf4j
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {
	@Autowired(required = false)
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Autowired
	private PageProperties pageProperties;
	
	// @Autowired
	// private RocketMQTemplate rocketmqtemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("username", username));
		if (sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在!");
		}
		sysUser.setLoginVoList(baseMapper.getLoginsById(sysUser.getId()));
		return sysUser;
	}
	
	@Transactional(readOnly = true)
	@Cacheable(key = "#id")
	public Map<String, Object> findObjectById(Integer id) {
		//  校验
		Assert.isArgumentValid(id == null || id < 1, "id值不正确");
		SysUser user = getById(id);
		Assert.isNull(user, "记录可能已经不存在");
		
		Map<String, Object> userMap = new HashMap<>(4);
		userMap.put("user", baseMapper.getObjectById(id));
		QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>()
				.eq("user_id", id).select("role_id");
		userMap.put("roleIds", sysUserRoleMapper.selectObjs(queryWrapper));
		return userMap;
	}
	
	@RequiredLog(operation = "查询用户")
	@Transactional(readOnly = true)
	public IPage<SysUserVo> findPageObjects(String username, Integer pageCurrent) {
		// 校验
		Assert.isArgumentValid(pageCurrent == null || pageCurrent < 1, "页码值不正确");
		
		// 条件
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>()
				.like(!StringUtils.isEmpty(username), "username", username)
				.orderByDesc("create_time");
		int total = count(queryWrapper);
		Assert.isServiceValid(total == 0, "没有找到对应记录");
		
		Page<SysUser> usersPage = new Page<>(pageCurrent, pageProperties.getSize(), total, true);
		return baseMapper.getSysUsersPage(usersPage, queryWrapper);
	}
	
	@RequiredLog(operation = "状态变更")
	public boolean enableById(Integer id, Integer enabled) {
		// 参数校验
		Assert.isArgumentValid(id == null || id < 1, "id值不正确");
		Assert.isArgumentValid(enabled != 1 && enabled != 0, "状态值不正确");
		
		UpdateWrapper<SysUser> wrapper = new UpdateWrapper<SysUser>()
				.eq("id", id).set("enabled", enabled);
		return update(wrapper);
	}
	
	@RequiredLog(operation = "新增用户")
	public boolean saveObjects(SysUser sysUser, Integer[] roleIds) {
		// 参数校验
		Assert.isNull(sysUser, "保存对象不能为空");
		Assert.isEmpty(sysUser.getUsername(), "用户名不能为空");
		Assert.isEmpty(sysUser.getPassword(), "密码不能为空");
		Assert.isArgumentValid(roleIds == null || roleIds.length == 0, "必须为用户分配权限");
		String email = sysUser.getEmail();
		UserMail userMail = new UserMail(sysUser.getUsername(),sysUser.getPassword(),email);
		if (Assert.isEmail(email)) {
			// log.info("已发送消息!");
			// rocketmqtemplate.sendOneWay("email-topic",userMail);
		}
		
		// 对密码进行加密
		sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
		if (save(sysUser)) {
			// 插入用户自身信息与角色的关系数据
			sysUserRoleMapper.insertSysUserRoles(sysUser.getId(), roleIds);
			return true;
		}
		return false;
	}
	
	@RequiredLog(operation = "更新用户")
	@CacheEvict(key = "#sysUser.id")
	public boolean updateObjects(SysUser sysUser, Integer[] roleIds) {
		// 参数校验
		Assert.isNull(sysUser, "保存对象不能为空");
		Assert.isEmpty(sysUser.getUsername(), "用户名不能为空");
		Assert.isArgumentValid(roleIds == null || roleIds.length == 0, "必须为用户分配权限");
		
		// 更新用户自身信息与用户角色的关系数据
		
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>()
				.eq("user_id", sysUser.getId()));
		sysUserRoleMapper.insertSysUserRoles(sysUser.getId(), roleIds);
		return updateById(sysUser);
	}
	
	@RequiredLog(operation = "修改密码")
	public boolean updatePassword(String password, String newPassword, String cfgPassword) {
		//参数校验
		Assert.isEmpty(password, "原密码不能为空");
		Assert.isEmpty(newPassword, "新密码不能为空");
		Assert.isArgumentValid(!newPassword.equals(cfgPassword), "两次新密码输入不一致");
		
		//判定原密码是否正确
		SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("id",
				LoginUserUtil.getCurrentSysUser().getId()));
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Assert.isArgumentValid(!passwordEncoder.matches(password,
				sysUser.getPassword()), "原密码不正确");
		
		//修改密码
		sysUser.setPassword(passwordEncoder.encode(password));
		return updateById(sysUser);
	}
}

package cn.bugnolwy.model;

import cn.bugnolwy.model.vo.LoginVo;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表
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
@ApiModel(value = "User对象", description = "用户管理")
public class SysUser extends BaseModel implements UserDetails {
	private static final long serialVersionUID = 1138534420870847335L;
	
	@ApiModelProperty(value = "用户名")
	private String username;
	
	@ApiModelProperty(value = "密码")
	private String password;
	
	@ApiModelProperty(value = "邮箱")
	private String email;
	
	@ApiModelProperty(value = "手机号")
	private String phone;
	
	@ApiModelProperty(value = "用户状态")
	@TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
	@Getter(value = AccessLevel.NONE)
	private Boolean enabled;
	
	@ApiModelProperty(value = "部门id")
	private Integer deptId;
	
	@TableField(exist = false)
	private List<LoginVo> loginVoList;
	
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}
	
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
	
	/**
	 * 获取用户的权限集合
	 *
	 * @return 用户的权限集合
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return loginVoList.parallelStream()
				.map(login -> new SimpleGrantedAuthority("ROLE_" + login.getName()))
				.collect(Collectors.toList());
	}
	
	/**
	 * 是否未过期 默认未过期
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * 是否未被锁定 默认未被锁定
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * 是否未过期 默认未过期
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * 用户是否可用
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}

package cn.bugnolwy.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 访问决策管理器
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		for (ConfigAttribute configAttribute : configAttributes) {
			String needRole = configAttribute.getAttribute();
			if ("ROLE_无权限用户".equals(needRole)) {
				if (authentication instanceof AnonymousAuthenticationToken) {
					throw new AccessDeniedException("尚未登录，请登录!");
				} else {
					return;
				}
			}
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(needRole)) {
					return;
				}
			}
			
		}
		throw new AccessDeniedException("权限不足，请联系管理员!");
	}
	
	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}

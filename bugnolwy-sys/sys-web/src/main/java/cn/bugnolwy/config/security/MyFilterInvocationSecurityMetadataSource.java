package cn.bugnolwy.config.security;

import cn.bugnolwy.model.vo.LoginVo;
import cn.bugnolwy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 筛选调用安全元数据源
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private LoginService loginService;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		List<LoginVo> loginVos = loginService.getLoginFilterVos();
		for (LoginVo loginVo : loginVos) {
			if (antPathMatcher.match(loginVo.getUrl(), requestUrl)) {
				return SecurityConfig.createList("ROLE_" +
						loginVo.getLogins().parallelStream()
								.map(LoginVo::getName)
								.collect(Collectors.toList()));
			}
		}
		return SecurityConfig.createList("ROLE_无权限用户");
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}

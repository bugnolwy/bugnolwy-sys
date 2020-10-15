package cn.bugnolwy.util;


import cn.bugnolwy.model.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Security登录用户
 * 工具类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public class LoginUserUtil {
	/**
	 * 从security中获取当前登录用户
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static SysUser getCurrentSysUser() {
		return ((SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
	/**
	 * 从security中获取当前登录用户的IP地址
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static String getCurrentIp() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		return details.getRemoteAddress();
	}
	
}

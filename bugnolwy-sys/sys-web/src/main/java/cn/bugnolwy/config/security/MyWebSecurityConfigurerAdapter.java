package cn.bugnolwy.config.security;

import cn.bugnolwy.vo.JsonResult;
import cn.bugnolwy.model.SysUser;
import cn.bugnolwy.service.SysUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

/**
 * Web安全配置器适配器
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Slf4j
@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;
	
	@Autowired
	private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(sysUserService);
		
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**", "/login");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		/**
		 * 根据每个url所需要的角色进行动态授权
		 *
		 * @author BugnoLwy
		 * @since 2020-99
		 */
		http.authorizeRequests()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						object.setAccessDecisionManager(myAccessDecisionManager);
						object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
						return object;
					}
				}).anyRequest().authenticated();
		/**
		 * 表单登录
		 *
		 * @author BugnoLwy
		 * @since 2020-9
		 */
		http.formLogin()
				.loginPage("/login").failureUrl("/login").permitAll()
				.loginProcessingUrl("/doLogin")
				.successHandler((request, response, authentication) -> {
					response.setContentType("application/json;charset=UTF-8");
					PrintWriter printWriter = response.getWriter();
					SysUser sysUser = (SysUser) authentication.getPrincipal();
					sysUser.setPassword("lwylwy777777@163.com");
					printWriter.write(new ObjectMapper().writeValueAsString(
							JsonResult.ok(sysUser)));
					printWriter.flush();
					printWriter.close();
				})
				.failureHandler((request, response, exception) -> {
					response.setContentType("application/json;charset=utf-8");
					PrintWriter printWriter = response.getWriter();
					JsonResult jsonResult = JsonResult.error(exception.getMessage());
					if (exception instanceof LockedException) {
						jsonResult.setMessage("账户已被锁定!");
					} else if (exception instanceof CredentialsExpiredException) {
						jsonResult.setMessage("密码已过期!");
					} else if (exception instanceof AccountExpiredException) {
						jsonResult.setMessage("账户已过期!");
					} else if (exception instanceof DisabledException) {
						jsonResult.setMessage("账户已被禁用!");
					} else if (exception instanceof BadCredentialsException) {
						jsonResult.setMessage("用户名或者密码输入错误，请重新输入!");
					} else {
						jsonResult.setMessage("系统正在维护中!");
					}
					printWriter.write(new ObjectMapper().writeValueAsString(jsonResult));
					printWriter.flush();
					printWriter.close();
				});
		
		/**
		 * "记住我"功能
		 *
		 * @author BugnoLwy
		 * @since 2020-9
		 */
		http.rememberMe().rememberMeParameter("rememberMe").tokenValiditySeconds(30 * 60);
		
		/**
		 * session会话，存储在redis里
		 *
		 * @author BugnoLwy
		 * @since 2020-9
		 */
		http.sessionManagement().sessionFixation().migrateSession()
				.invalidSessionUrl("/doLogin");
		
		/**
		 * 退出
		 * @author BugnoLwy
		 * @since 2020-9
		 * */
		http.logout().logoutUrl("/doLogout").logoutSuccessUrl("/login");
	}
}


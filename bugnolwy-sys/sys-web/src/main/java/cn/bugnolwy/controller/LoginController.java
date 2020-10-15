package cn.bugnolwy.controller;

import cn.bugnolwy.model.vo.LoginVo;
import cn.bugnolwy.service.LoginService;
import cn.bugnolwy.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

/**
 * 登录及页面,使用thymeleaf模版显示
 * HandlerAdapter类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/doIndexUI")
	public String doIndexUI(Model model) {
		model.addAttribute("username",
				LoginUserUtil.getCurrentSysUser().getUsername());
		
		model.addAttribute("userMenus",
				loginService.getUserMenuNames(
						LoginUserUtil.getCurrentSysUser()
								.getLoginVoList()
								.parallelStream()
								.map(LoginVo::getId)
								.collect(Collectors.toList())));
		return "starter";
	}
	
	@GetMapping("/favicon.ico")
	@ResponseBody
	public String faviconico() {
		return "static/" + "favicon.ico";
	}
	
	@GetMapping("/doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	
	@GetMapping("/{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
		return "sys/" + moduleUI;
	}
	
}

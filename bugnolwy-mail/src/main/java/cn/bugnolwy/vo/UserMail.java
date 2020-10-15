package cn.bugnolwy.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 消息消费
 * vo类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy
 * @gitHub https://github.com/bugnolwy/bugnolwy
 * @since 2020-9
 */
public class UserMail implements Serializable {
	private String username;
	private String password;
	private String email;
	
	@JSONField(name = "username")
	public String getUsername() {
		return username;
	}
	
	@JSONField(name = "password")
	public String getPassword() {
		return password;
	}
	
	@JSONField(name = "email")
	public String getEmail() {
		return email;
	}
	
	public UserMail() {
	}
	
	public UserMail(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}

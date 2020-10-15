package cn.bugnolwy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 消息生产
 * vo类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy
 * @gitHub https://github.com/bugnolwy/bugnolwy
 * @since 2020-
 */
@Data
@AllArgsConstructor
public class UserMail {
	private String username;
	private String password;
	private String email;
}

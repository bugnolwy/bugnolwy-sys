package cn.bugnolwy.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用于用户的登录和角色验证
 * Vo类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
public class LoginVo implements Serializable {
	private static final long serialVersionUID = 73694248989299601L;
	private Integer id;
	private String url;
	private String name;
	private List<LoginVo> logins;
}

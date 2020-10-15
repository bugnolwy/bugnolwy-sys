package cn.bugnolwy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义断言工具类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public class Assert {
	/**
	 * Null值验证
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static void isNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 空值验证
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static void isEmpty(String content, String message) {
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 参数有效性验证
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static void isArgumentValid(boolean statement, String message) {
		if (statement) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 业务验证
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static void isServiceValid(boolean statement, String message) {
		if (statement) {
			throw new RuntimeException(message);
		}
	}
	
	/***
	 * 邮箱验证
	 *
	 * @author BugnoLwy
	 * @since 2020-9
	 */
	public static boolean isEmail(String email) {
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}
}

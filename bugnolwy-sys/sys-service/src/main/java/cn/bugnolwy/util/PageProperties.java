package cn.bugnolwy.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 设置每页数量
 * 工具类
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy
 * @gitHub https://github.com/bugnolwy/bugnolwy
 * @since 2020-9
 */
@Data
@ConfigurationProperties(prefix = "page.config")
@Configuration
public class PageProperties {
	private Integer size;
}

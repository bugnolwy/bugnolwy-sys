package cn.bugnolwy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Springboot
 * 启动类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */

// @EnableDiscoveryClient
@SpringBootApplication
public class BugnoLwyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BugnoLwyApplication.class, args);
	}
	
}

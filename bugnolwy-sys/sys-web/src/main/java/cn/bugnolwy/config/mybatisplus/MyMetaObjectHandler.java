package cn.bugnolwy.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis自动填充策略
 * MetaObjectHandler接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	
	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class)
				.strictInsertFill(metaObject, "modifiedTime", LocalDateTime::now, LocalDateTime.class);
	}
	
	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime::now, LocalDateTime.class);
	}
}

package cn.bugnolwy.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志表
 * 实体类
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
@Data
@NoArgsConstructor
@ApiModel(value = "Log对象", description = "日志管理")
public class SysLog implements Serializable {
	private static final long serialVersionUID = 8876920804134951849L;
	
	@TableId(type = IdType.AUTO)
	private Integer id;
	
	@ApiModelProperty(value = "创建用户")
	private String username;
	
	@ApiModelProperty(value = "用户操作")
	private String operation;
	
	@ApiModelProperty(value = "请求方法")
	private String method;
	
	@ApiModelProperty(value = "执行时长(毫秒)")
	private Long time;
	
	@ApiModelProperty(value = "IP地址")
	private String ip;
	
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	
}

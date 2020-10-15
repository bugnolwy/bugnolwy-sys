package cn.bugnolwy.mapper;

import cn.bugnolwy.model.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 部门管理
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {
	
	List<SysDept> getSysDepts();
}

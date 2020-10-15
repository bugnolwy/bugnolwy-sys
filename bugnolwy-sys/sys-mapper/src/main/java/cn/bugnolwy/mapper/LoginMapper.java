package cn.bugnolwy.mapper;

import cn.bugnolwy.model.vo.LoginVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 用户登录和角色验证
 * Mapper接口
 *
 * @author BugnoLwy
 * @email lwylwy777777@163.com
 * @gitee https://gitee.com/bugnolwy/bugnolwy-sys
 * @gitHub https://github.com/bugnolwy/bugnolwy-sys
 * @since 2020-9
 */
public interface LoginMapper extends BaseMapper<LoginVo> {
	List<LoginVo> getLoginFilterVos();
	
	List<LoginVo> getUserMenuNames(List<Integer> roleIds);
}

package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Task;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 获取task的信息
     */
    public List look_task(@Param("condition") String condition);
}

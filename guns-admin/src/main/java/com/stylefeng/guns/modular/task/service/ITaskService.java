package com.stylefeng.guns.modular.task.service;

import com.stylefeng.guns.modular.system.model.Task;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
public interface ITaskService extends IService<Task> {
    /**
     * 获取task的信息
     */
    public List look_task(@Param("condition") String condition);
}

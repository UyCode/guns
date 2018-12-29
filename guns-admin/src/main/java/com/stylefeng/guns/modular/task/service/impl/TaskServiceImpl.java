package com.stylefeng.guns.modular.task.service.impl;

import com.stylefeng.guns.modular.system.model.Task;
import com.stylefeng.guns.modular.system.dao.TaskMapper;
import com.stylefeng.guns.modular.task.service.ITaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    /**
     * 获取task的信息
     */
    public List look_task(@Param("condition") String condition){
        return this.baseMapper.look_task(condition);
    }

}

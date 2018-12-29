package com.stylefeng.guns.modular.teacher.service;

import com.stylefeng.guns.modular.system.model.Teacher;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
public interface ITeacherService extends IService<Teacher> {
    public List<Map<String,Object>> look_teacher(@Param("condition")String condition);
}

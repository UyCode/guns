package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.Teacher;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    public List<Map<String,Object>> look_teacher(@Param("condition")String condition);
}

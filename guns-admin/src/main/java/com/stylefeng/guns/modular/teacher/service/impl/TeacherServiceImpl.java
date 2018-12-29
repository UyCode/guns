package com.stylefeng.guns.modular.teacher.service.impl;

import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.system.dao.TeacherMapper;
import com.stylefeng.guns.modular.teacher.service.ITeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Override
    public List<Map<String,Object>> look_teacher(String condition){return this.baseMapper.look_teacher(condition);}
}

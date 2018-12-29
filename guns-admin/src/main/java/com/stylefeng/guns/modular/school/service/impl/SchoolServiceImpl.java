package com.stylefeng.guns.modular.school.service.impl;

import com.stylefeng.guns.modular.system.model.School;
import com.stylefeng.guns.modular.system.dao.SchoolMapper;
import com.stylefeng.guns.modular.school.service.ISchoolService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
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
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {
    /**
     * 获取学校的信息
     */
    @Override
    public List<Map<String,Object>>look_school(String condition){return this.baseMapper.look_school(condition);}


    /**
     * 获取学校的个数
     */
    public int  schoolcount(@Param("scname")String scname){
        return  this.baseMapper.schoolcount(scname);
    }
}

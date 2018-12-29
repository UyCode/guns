package com.stylefeng.guns.modular.college.service.impl;

import com.stylefeng.guns.modular.system.model.College;
import com.stylefeng.guns.modular.system.dao.CollegeMapper;
import com.stylefeng.guns.modular.college.service.ICollegeService;
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
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements ICollegeService {
    /**
     * 获取学院的信息
     */
    @Override
    public List<Map<String,Object>>look_college(String condition){return this.baseMapper.look_college(condition);}


    /**
     * 获取学院的信息
     */
    public List lookcollege(@Param("condition")String condition){
        return this.baseMapper.lookcollege(condition);
    }

    /**
     * 获取学院的个数
     */
    public int  collgecount(@Param("coname") String coname){
        return  this.baseMapper.collgecount(coname);
    }
}

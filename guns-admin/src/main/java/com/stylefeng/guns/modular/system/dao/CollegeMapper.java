package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.College;
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
public interface CollegeMapper extends BaseMapper<College> {
    /**
     * 获取学院的信息
     */
    public List<Map<String,Object>>look_college(@Param("condition")String condition);

    /**
     * 获取学院的信息
     */
    public List  lookcollege(@Param("condition")String condition);


    /**
     * 获取学院的个数
     */
    public int  collgecount(@Param("coname") String coname);
}

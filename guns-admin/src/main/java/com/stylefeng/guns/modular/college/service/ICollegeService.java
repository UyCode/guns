package com.stylefeng.guns.modular.college.service;

import com.stylefeng.guns.modular.system.model.College;
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
public interface ICollegeService extends IService<College> {

    public List<Map<String,Object>>look_college(@Param("condition")String condition);


    /**
     * 获取学院的信息
     */
    public List lookcollege(@Param("condition")String condition);


    /**
     * 获取学院的个数
     */
    public int  collgecount(@Param("coname") String coname);


}

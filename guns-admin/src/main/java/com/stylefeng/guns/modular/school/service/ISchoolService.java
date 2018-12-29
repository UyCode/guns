package com.stylefeng.guns.modular.school.service;

import com.stylefeng.guns.modular.system.model.School;
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
public interface ISchoolService extends IService<School> {
    /**
     * 获取学校的信息
     */
    public List<Map<String,Object>>look_school(@Param("condition")String condition);


    /**
     * 获取学校的个数
     */
    public int  schoolcount(@Param("scname")String scname);
}

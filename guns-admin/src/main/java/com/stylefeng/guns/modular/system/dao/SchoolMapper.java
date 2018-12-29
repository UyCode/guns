package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.School;
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
public interface SchoolMapper extends BaseMapper<School> {
    /**
     * 获取学校的信息
     */
    public List<Map<String,Object>>look_school(@Param("condition")String condition);

    /**
     * 获取学校的个数
     */
    public int  schoolcount(@Param("scname")String scname);

}

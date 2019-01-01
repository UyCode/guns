package com.stylefeng.guns.modular.school.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.School;
import com.stylefeng.guns.modular.school.service.ISchoolService;

/**
 * school控制器
 *
 * @author fengshuonan
 * @Date 2018-11-23 20:39:27
 */
@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController {

    private String PREFIX = "/school/school/";

    @Autowired
    private ISchoolService schoolService;

    /**
     * 跳转到school首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "school.html";
    }

    /**
     * 跳转到添加school
     */
    @RequestMapping("/school_add")
    public String schoolAdd() {
        return PREFIX + "school_add.html";
    }

    /**
     * 跳转到修改school
     */
    @RequestMapping("/school_update/{schoolId}")
    public String schoolUpdate(@PathVariable Integer schoolId, Model model) {
        School school = schoolService.selectById(schoolId);
        model.addAttribute("item",school);
        LogObjectHolder.me().set(school);
        return PREFIX + "school_edit.html";
    }

    /**
     * 获取school列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(condition==null || condition.equals("")) {
            condition="";
        }
        return schoolService.look_school(condition);
    }

    /**
     * 新增school
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(School school) {
        if(schoolService.schoolcount(school.getScname())!=0){
            return  "学校名称重复";
        }
        if(schoolService.equals("")){
            return  "学校不能为空！";
        }
        schoolService.insert(school);
        return "添加成功";
    }

    /**
     * 删除school
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer schoolId) {
        schoolService.deleteById(schoolId);
        return SUCCESS_TIP;
    }

    /**
     * 修改school
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(School school) {
        schoolService.updateById(school);
        return SUCCESS_TIP;
    }

    /**
     * school详情
     */
    @RequestMapping(value = "/detail/{schoolId}")
    @ResponseBody
    public Object detail(@PathVariable("schoolId") Integer schoolId) {
        return schoolService.selectById(schoolId);
    }

    /**
     * 查找学校里面学校名称
     */
    @RequestMapping(value = "/look_school")
    @ResponseBody
    public Object look_school() {
        return schoolService.look_school(null);
    }

}

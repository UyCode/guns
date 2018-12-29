package com.stylefeng.guns.modular.college.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.College;
import com.stylefeng.guns.modular.college.service.ICollegeService;

/**
 * college控制器
 *
 * @author fengshuonan
 * @Date 2018-11-23 21:24:57
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {

    private String PREFIX = "/college/college/";

    @Autowired
    private ICollegeService collegeService;

    /**
     * 跳转到college首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "college.html";
    }

    /**
     * 跳转到添加college
     */
    @RequestMapping("/college_add")
    public String collegeAdd() {
        return PREFIX + "college_add.html";
    }

    /**
     * 跳转到修改college
     */
    @RequestMapping("/college_update/{collegeId}")
    public String collegeUpdate(@PathVariable Integer collegeId, Model model) {
        College college = collegeService.selectById(collegeId);
        model.addAttribute("item",college);
        LogObjectHolder.me().set(college);
        return PREFIX + "college_edit.html";
    }

    /**
     * 获取college列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(condition==null) {
            condition="";
        }
        return collegeService.look_college(condition);
    }

    /**
     * 获取college列表2
     */
    @RequestMapping(value = "/lookcollege")
    @ResponseBody
    public Object listt(String condition) {

        System.out.print(collegeService.lookcollege(null)+"aaa");
        return collegeService.lookcollege(null);
    }


    /**
     * 新增college
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(College college) {
        if(collegeService.collgecount(college.getConame())!=0){
            return "学院重复";
        }

        collegeService.insert(college);
        return "添加成功";
    }

    /**
     * 删除college
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer collegeId) {
        collegeService.deleteById(collegeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改college
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(College college) {
        collegeService.updateById(college);
        return SUCCESS_TIP;
    }

    /**
     * college详情
     */
    @RequestMapping(value = "/detail/{collegeId}")
    @ResponseBody
    public Object detail(@PathVariable("collegeId") Integer collegeId) {
        return collegeService.selectById(collegeId);
    }

    /**
     * 查找学院里面学院名称
     */
    @RequestMapping(value = "/look_college")
    @ResponseBody
    public Object look() {

        System.out.print(collegeService.look_college(null)+"666");
        return collegeService.look_college(null);
    }
}

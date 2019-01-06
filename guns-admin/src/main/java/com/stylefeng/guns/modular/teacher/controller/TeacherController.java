package com.stylefeng.guns.modular.teacher.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Teacher;
import com.stylefeng.guns.modular.teacher.service.ITeacherService;

/**
 * teacher控制器
 *
 * @author fengshuonan
 * @Date 2018-11-23 20:00:07
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    Teacher tstate = new Teacher();
    int newtstate = tstate.status;

    private String PREFIX = "/teacher/teacher/";

    @Autowired
    private ITeacherService teacherService;

    /**
     * 跳转到teacher首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacher.html";
    }

    /**
     * 跳转到添加teacher
     */
    @RequestMapping("/teacher_add")
    public String teacherAdd() {
        return PREFIX + "teacher_add.html";
    }

    /**
     * 跳转到修改teacher
     */
    @RequestMapping("/teacher_update/{teacherId}")
    public String teacherUpdate(@PathVariable Integer teacherId, Model model) {
        Teacher teacher = teacherService.selectById(teacherId);
        model.addAttribute("item",teacher);
        LogObjectHolder.me().set(teacher);
        return PREFIX + "teacher_edit.html";
    }
    /**
     * 跳转到修改teacher
     */
    @RequestMapping("/zstp")
    public String zstp() {
        return PREFIX + "zstp.html";
    }

    /**
     * 获取teacher列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if(condition==null) {
            condition="";
        }
        return teacherService.look_teacher(condition);
    }

    /**
     * 新增teacher
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teacher teacher) {
        teacherService.insert(teacher);
        if(newtstate == 0) {
            return "添加失败！";
        }
        else {
            return "添加成功！";
        }
    }

    /**
     * 删除teacher
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teacherId) {
        teacherService.deleteById(teacherId);
        return SUCCESS_TIP;
    }

    /**
     * 修改teacher
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Teacher teacher) {

        teacherService.updateById(teacher);
        if(newtstate == 0) {
            return "修改失败！";
        }
        else {
            return "修改成功！";
        }
    }

    /**
     * teacher详情
     */
    @RequestMapping(value = "/detail/{teacherId}")
    @ResponseBody
    public Object detail(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.selectById(teacherId);
    }
}

package com.stylefeng.guns.modular.task.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Task;
import com.stylefeng.guns.modular.task.service.ITaskService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * task控制器
 *
 * @author fengshuonan
 * @Date 2018-11-23 21:11:22
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    private String PREFIX = "/task/task/";

    @Autowired
    private ITaskService taskService;

    /**
     * 跳转到task首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "task.html";
    }

    /**
     * 跳转到添加task
     */
    @RequestMapping("/task_add")
    public String taskAdd() {
        return PREFIX + "task_add.html";
    }

    /**
     * 跳转到修改task
     */
    @RequestMapping("/task_update/{taskId}")
    public String taskUpdate(@PathVariable Integer taskId, Model model) {
        Task task = taskService.selectById(taskId);
        model.addAttribute("item",task);
        LogObjectHolder.me().set(task);
        return PREFIX + "task_edit.html";
    }

    /**
     * 获取task列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return taskService.look_task(condition);
    }


    /**
     * 新增task
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Task task) {
        System.out.print(task.getEtime()+"666");
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        task.setSettime(date);
        if(task.getStime().before(date)){
            return "开始时间应在当前时间之后,请重新设置";
        }
        taskService.insert(task);
        return "创建成功";
    }

    /**
     * 删除task
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer taskId) {
        taskService.deleteById(taskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改task
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Task task) {
        taskService.updateById(task);
        return SUCCESS_TIP;
    }

    /**
     * task详情
     */
    @RequestMapping(value = "/detail/{taskId}")
    @ResponseBody
    public Object detail(@PathVariable("taskId") Integer taskId) {
        return taskService.selectById(taskId);
    }
}

package com.stylefeng.guns.modular.feedback.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Feedback;
import com.stylefeng.guns.modular.feedback.service.IFeedbackService;

/**
 * feedback控制器
 *
 * @author fengshuonan
 * @Date 2018-11-23 20:46:25
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    private String PREFIX = "/feedback/feedback/";

    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 跳转到feedback首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "feedback.html";
    }

    /**
     * 跳转到添加feedback
     */
    @RequestMapping("/feedback_add")
    public String feedbackAdd() {
        return PREFIX + "feedback_add.html";
    }

    /**
     * 跳转到修改feedback
     */
    @RequestMapping("/feedback_update/{feedbackId}")
    public String feedbackUpdate(@PathVariable Integer feedbackId, Model model) {
        Feedback feedback = feedbackService.selectById(feedbackId);
        model.addAttribute("item",feedback);
        LogObjectHolder.me().set(feedback);
        return PREFIX + "feedback_edit.html";
    }

    /**
     * 获取feedback列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return feedbackService.selectList(null);
    }

    /**
     * 新增feedback
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Feedback feedback) {
        feedbackService.insert(feedback);
        return SUCCESS_TIP;
    }

    /**
     * 删除feedback
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer feedbackId) {
        feedbackService.deleteById(feedbackId);
        return SUCCESS_TIP;
    }

    /**
     * 修改feedback
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Feedback feedback) {
        feedbackService.updateById(feedback);
        return SUCCESS_TIP;
    }

    /**
     * feedback详情
     */
    @RequestMapping(value = "/detail/{feedbackId}")
    @ResponseBody
    public Object detail(@PathVariable("feedbackId") Integer feedbackId) {
        return feedbackService.selectById(feedbackId);
    }
}

package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.duanxin.MobileMessageCheck;
import com.stylefeng.guns.core.util.duanxin.MobileMessageSend;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.PrintWriter;

/**
 * 激活控制器
 *
 * @author ebrayim
 * @Date 2018年11月21日
 */

@Controller
public class ActivationController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转到激活页面
     */
    @RequestMapping(value = "/activation", method = RequestMethod.GET)
    public String activation() {
        if (ShiroKit.isAuthenticated()||ShiroKit.getUser()!= null) {
            return REDIRECT + "/";
        } else {
            return "/activation.html";
        }
    }


    /**
     * 获取短信验证码
     */
    @RequestMapping(value = "/activation/achieve", method = RequestMethod.GET)
    @ResponseBody
    public void achieve(String phone) {

        try {
            PrintWriter out = getHttpServletResponse().getWriter();
            String str = MobileMessageSend.sendMsg(phone);
            System.out.println(str);
            if ("success".equals(str)) {
                int i= 1;
                out.print(i);
            } else {
                int j = 0;
                out.print(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 点击激活时执行的动作
     */
    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    public String activationVali(Model model){
        User user = null;

        //获取激活表单数据
        String username = super.getPara("username").trim();
        String phone = super.getPara("phone").trim();
        String phonekaptcha = super.getPara("phonekaptcha").trim();
        String password = super.getPara("password").trim();



        //根据获取到的用户名获取user
        user = userService.getByAccount(username);
        System.out.println("-----------------------------");
        System.out.println(user);
        System.out.println("-----------------------------");
        //验证用户是否已经激活
        int activation = user.getActivation();
        System.out.println("-----------------------------");
        System.out.println(activation);
        System.out.println("-----------------------------");
        if(activation!=0){
            model.addAttribute("xxx", "该账号已经激活，请直接登陆使用！");
            return "/login.html";
        }


        //接收验证码
        try {
            String str = MobileMessageCheck.checkMsg(phone, phonekaptcha);
            if ("success".equals(str)) {
                System.out.println("验证成功！");
            } else {
                System.out.println("验证失败！");
                model.addAttribute("xxx", "短信验证失败，无法修改！");
                return "/activation.html";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (user != null) {
            if (phone.equals(user.getPhone())) {
                System.out.println(phone);
                Integer userId = user.getId();
                String newMd5 = ShiroKit.md5(password, user.getSalt());
                user.setPassword(newMd5);
                user.setActivation(1);
                user.updateById();
                model.addAttribute("yyy", "用户密码修改成功！");
                return "/login.html";
            }
        } else {
                model.addAttribute("xxx", "用户名不正确，无法修改！");
                return "/activation.html";
        }

        return "/activation.html";

    }

}

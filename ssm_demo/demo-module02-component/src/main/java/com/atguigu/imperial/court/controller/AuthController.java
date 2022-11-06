package com.atguigu.imperial.court.controller;

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.service.api.MemorialsService;
import com.atguigu.imperial.court.util.ImperialCourtConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author liuyang
 * @create 2022-11-04 10:45
 */


@Controller
public class AuthController {

    @Autowired
    private EmpService empService;

    @Autowired
    private MemorialsService memorialsService;

    @RequestMapping(value = "/auth/login",method = RequestMethod.GET)
    public String doLogin(String loginAccount, String loginPassword, Model model,HttpSession session){
        // 1、尝试查询登录信息
        Emp emp = empService.getEmpByLogin(loginAccount,loginPassword);
        // 2、判断登录是否成功
        if (emp == null){
            // 3、如果登录失败则回到登录页面显示提示消息
            model.addAttribute("message", ImperialCourtConst.LOGIN_FAILED_MESSAGE);
            return "index";
        }else {

            // 4、如果登录成功则将登录信息存入 Session 域
            session.setAttribute("loginInfo", emp);
            return "redirect:/show/memorials";
        }
    }



    @RequestMapping(value = "/show/memorials",method = RequestMethod.GET)
    public String showMemorials(Model model){
        //获取所有奏折信息
        List<Memorials> list = memorialsService.getAllMemorials();

        model.addAttribute("list",list);

        return "memorials-list";
    }



    @RequestMapping(value = "/show/detail/{memorialsId}",method = RequestMethod.GET)
    public String showMemorials(@PathVariable("memorialsId") String memorialsId,Model model){
        Memorials memorial = memorialsService.getMemorialById(memorialsId);
        // 获取当前奏折对象的状态
        Integer status = memorial.getMemorialsStatus();


        if(status == 0){
            // 更新奏折状态：数据库修改
            memorialsService.updateMemorialsStatus(memorialsId);
            // 更新奏折状态：当前对象修改
            memorial.setMemorialsStatus(1);
        }

        model.addAttribute("memorial",memorial);
        return "memorials-detail";
    }



    @RequestMapping(value = "/work/reply",method =  RequestMethod.PUT)
    public String feedBack(String memorialsId,String feedbackContent){

        memorialsService.feedBack(memorialsId,feedbackContent);

        return "redirect:/show/memorials";
    }


}

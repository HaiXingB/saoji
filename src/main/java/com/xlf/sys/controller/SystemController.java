package com.xlf.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class SystemController {

    /**
     * 跳转到登陆页面
     * */
    @RequestMapping("toLogin")
    public String tologin(){
        return "system/index/login";
    }

    /**
     * 跳转到首页
     * */
    @RequestMapping("index")
    public String index(){
        return "system/index/index";
    }


    /**
     *
     * @return 跳转到工作台
     */
    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/index/deskManager";
    }

    /**
     *
     * @return 跳转到日志管理
     */
    @RequestMapping("/toLoginfoManager")
    public String toLoginfoManager(){
        return "system/loginfo/loginfoManager";
    }

    /**
     *
     * @return 跳转到公告管理
     */
    @RequestMapping("/toNoticeManager")
    public String toNoticeManager(){
        return "system/notice/noticeManager";
    }
}

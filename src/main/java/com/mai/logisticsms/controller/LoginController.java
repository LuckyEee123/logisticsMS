package com.mai.logisticsms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Mai
 * @Date: 2022/5/22 16:05
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public String login() {
        return "OK";
    }

}

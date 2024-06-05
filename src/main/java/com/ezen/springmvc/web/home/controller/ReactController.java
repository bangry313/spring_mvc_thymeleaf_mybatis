package com.ezen.springmvc.web.home.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ReactController {
    @RequestMapping("/react/admin")
    public String home(Model model){
        log.info("요청 들어옴..");
//        return "forward:/react/index";
        return "forward:/react/index.html";
    }
}










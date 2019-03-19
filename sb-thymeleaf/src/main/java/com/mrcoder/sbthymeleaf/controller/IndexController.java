package com.mrcoder.sbthymeleaf.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/")
    public String index(Model model){
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        model.addAttribute("name", "hello world");
        return "index";
    }
}

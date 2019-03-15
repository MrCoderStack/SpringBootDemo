package com.mrcoder.sbjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

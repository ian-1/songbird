package com.portmoor.songbird.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    @ResponseBody
    public String show() {
        return "Hello World!";
    }
}

package com.hml.blog.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by minglu on 2017/8/22.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "Hello World!";
    }


}

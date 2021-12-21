package com.rat.controller;

import com.rat.model.TestModel;
import org.springframework.web.bind.annotation.*;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/17 13:19
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/getJson")
    public TestModel getJson(TestModel testModel) {
      return null;
    }

    @GetMapping("/getString")
    public String getString(String param) {
        return "you can do this";
    }
}

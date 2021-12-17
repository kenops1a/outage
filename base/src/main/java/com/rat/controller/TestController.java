package com.rat.controller;

import com.rat.model.TestModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

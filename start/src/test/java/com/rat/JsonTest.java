package com.rat;

import com.rat.info.ResultTool;
import org.junit.Test;

/**
 * @type: outage
 * @author: yaominc
 * @description: write_bug
 * @date: 2021/12/22 10:09
 */
public class JsonTest {

    @Test
    public void outJson() {
        System.out.println(ResultTool.failed().getClass());
        System.out.println(ResultTool.failed().toString().getClass());
    }
}

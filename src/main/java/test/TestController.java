package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2016/8/4.
 */
@Controller
public class TestController {
    @RequestMapping("hello.do")
    public String test(){
        return "hello";
    }
    @RequestMapping("indextest.do")
    public String tests(){
        return "test";
    }
}

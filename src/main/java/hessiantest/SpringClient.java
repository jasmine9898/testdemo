package hessiantest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/4/20.
 */
@Controller
public class SpringClient {
    @Resource
    private HessianHelloService hessianHelloService;
    @RequestMapping("/springhessianclient.do")
    @ResponseBody
    public String cc() {
        String msg="recived -> ";
        msg+=hessianHelloService.sayHello("spring_client");
        return msg;
    }
}


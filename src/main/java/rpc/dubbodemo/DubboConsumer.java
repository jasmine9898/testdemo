package rpc.dubbodemo;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DubboConsumer {

    @Resource
    HelloService dubboService;

    @RequestMapping("/dubbo2.do")
    @ResponseBody
    public String consumerTest2() {
        return "<html><head><title>dubbo</title><link rel=\"bookmark\" href=\"https://www.baidu.com/favicon.ico\" type=\"image/x-icon\" /></head><body>"+dubboService.sayHello("dubbo ")+"</body></html>";
    }
    @RequestMapping("/dubbo.do")
    @ResponseBody
    public String consumerTest() {
        return dubboService.sayHello("dubbo ");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo-consumer.xml"});
        context.start();
        HelloService demoService = (HelloService) context.getBean("dubboService");// 获取远程服务代理
        String hello = demoService.sayHello("main consumer");// 执行远程方法
        System.out.println(hello);// 显示调用结果
    }

}
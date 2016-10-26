package hessiantest;

import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

/**
 * Created by Administrator on 2016/4/20.
 */
@Controller
public class HessianClient {
    @RequestMapping("/hessianclient.do")
    @ResponseBody
    public String cc() {
        String msg="recived -> ";
        String url = "http://localhost:8080/testdemo/hessian/service";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            HessianHelloService service = (HessianHelloService) factory.create(HessianHelloService.class, url);
            msg+= service.sayHello("hesian ");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}


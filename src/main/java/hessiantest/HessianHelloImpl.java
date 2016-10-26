package hessiantest;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/20.
 */
public class HessianHelloImpl implements HessianHelloService{

        public String sayHello(String uname) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  "Hessian say:Hello " + uname + "  now time is :" + new Date();
        }
}

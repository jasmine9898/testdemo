package rpc.thriftdemo;

import java.awt.*;

/**
 * Created by Administrator on 2016/5/26.
 */
public class HelloImpl implements Hello.Iface {
    public String hi(){
       // System.out.println("hi ");
        return "hello";
    }
}

package rpc.thriftdemo;

import org.apache.thrift.TException;

public class HelloWorldImpl implements HelloWorldService.Iface {
	 
    public HelloWorldImpl() {
    }
 
    public String sayHello(String username){
        return "Hi," + username + " ,Welcome to the thrift's world !";
    }
 
}  

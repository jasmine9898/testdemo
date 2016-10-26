package rpc.xmlrpcdemo;

import org.springframework.stereotype.Service;

@Service
public class HelloHandlerImpl  implements HelloServicesHandler {
    public String execute(String uname) {
        
        return "hello "+uname+" , this is xmlrpc demo !";
    }

}

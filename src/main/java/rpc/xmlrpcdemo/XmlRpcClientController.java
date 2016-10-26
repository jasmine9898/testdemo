package rpc.xmlrpcdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XmlRpcClientController {

    
	@RequestMapping("/xmlrpc.do")
	@ResponseBody
    public String start(ModelMap modelMap,HttpServletResponse response){
    	String result="";
    	try {
            //配置客户端
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            //设置服务器端地址
            config.setServerURL(new URL("http://localhost:8080/testdemo/HelloHandler.do"));
            //创建XmlRpc客户端
            XmlRpcClient client = new XmlRpcClient();
            //绑定以上设置
            client.setConfig(config);
            //创建参数列表
            Vector<String> params = new Vector<String>();
            params.addElement("abc");
            //modelMap.addAttribute("sdsd", 123);
           
            //执行XML-RPC 请求
            result=(String) client.execute("HelloHandler.execute", params);
            
           } catch (MalformedURLException e) {
            e.printStackTrace();
        	result= "MalformedURLException!!!";    	

            } catch (XmlRpcException e) {
            e.printStackTrace();
        	result= "XmlRpcException!!!";    	
        }
    	return result;
    }
	
}
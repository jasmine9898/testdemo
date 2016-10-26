package rpc.xmlrpcdemo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class XmlRpcServerController{
	@Autowired
	private HelloHandlerImpl helloHandler;
    private XmlRpcServletServer server=null;
   	
    @RequestMapping("/HelloHandler.do")

	protected ModelAndView handleRequestInternal(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	server = new XmlRpcServletServer();
    	 PropertyHandlerMapping pmp = new PropertyHandlerMapping();
         pmp.addHandler("HelloHandler", HelloHandlerImpl.class);
         server.setHandlerMapping(pmp);
         XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl)server.getConfig();
         serverConfig.setEnabledForExtensions(true);
         serverConfig.setContentLengthOptional(false);
         server.execute(request, response);

    	  return null;
	}

    
}
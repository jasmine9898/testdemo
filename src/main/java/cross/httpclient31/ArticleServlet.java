package cross.httpclient31;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@WebServlet(name = "ArticleServlet", urlPatterns = { "/register/success" })
public class ArticleServlet extends HttpServlet{
	HttpClient client = new HttpClient();  
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer stringbuffer=new StringBuffer();
		String tag=request.getParameter("tag");
		GetMethod getMethod= new GetMethod("http://192.168.2.67:8080/test/user/checkUserInfo?p="+tag);
		try {
			client.executeMethod(getMethod);
			stringbuffer.append(getMethod.getResponseBodyAsString());
			getMethod.releaseConnection();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(stringbuffer.toString());
		PrintWriter out = response.getWriter();
		out.write(stringbuffer.toString()); 
	}
}

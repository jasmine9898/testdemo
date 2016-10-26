package cross.httpclient31;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class Httpclient3Test {
	HttpClient client = new HttpClient();

	String getUrlStr[] = {
			"http://localhost:8080/testdemo/mysqldbcp.do",
		//	"http://192.168.2.116:8080/test/mongo.do",
		//"http://192.168.2.56:8080/test/mongo.do",
	}; 

	@RequestMapping("/httpclient3.do")
	@ResponseBody
	public String get()throws Exception{

		String string = new String();
		for (int i = 0; i < getUrlStr.length; i++) {
			GetMethod getMethod = new GetMethod(getUrlStr[i]);
			client.executeMethod(getMethod);
			string+=getUrlStr[i]+ " ----: " + getMethod.getResponseBodyAsString() + "<br/>";
			getMethod.releaseConnection();
		}
		return string;
	}
	@RequestMapping("/httpclient3post.do")
	@ResponseBody
	public String post() throws Exception{
		String string = new String();
			HttpMethod postMethod = new PostMethod("http://localhost:8080/testdemo/mongo.do");
			postMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
			client.executeMethod(postMethod);
			string+=" ----: " + postMethod.getResponseBodyAsString() + "  <br/>";
			postMethod.releaseConnection();
		return string;
	}
	@RequestMapping("/httpclient3postparams.do")
	@ResponseBody
	public String PostParams() throws Exception{
		String string = "httpclient3postparams";
		PostMethod post = new PostMethod( "http://localhost:8080/testdemo/mongo.do?a=getpara" );
		NameValuePair name = new NameValuePair( "name" , "test" );
		NameValuePair pass = new NameValuePair( "password" , "1" );
		post.setRequestBody( new NameValuePair[] { name,pass});
		client.executeMethod(post);
		post.releaseConnection();
		return string;
	}

	@RequestMapping(value="/httpclient3error.do")
	@ResponseBody
	public String httpclient3error(){
		StringBuffer stringBuffer=new StringBuffer();

	/*200*/
		GetMethod getMethod; /*= new GetMethod("http://localhost:8080/testdemo/mongo.do" );
		getMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,2000 );

		try {
			client.executeMethod(getMethod);
			stringBuffer.append("received:"+getMethod.getStatusCode()+"   ");
			stringBuffer.append(getMethod.getResponseBodyAsString()+"<br/>");
			Header[] hearders = getMethod.getResponseHeaders();
			if (hearders != null){
				for (Header header:	hearders) {
					stringBuffer.append(header.getName()).append(": ").append(header.getValue()+"<br>");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		stringBuffer.append("<br><br>");*/
		getMethod = new GetMethod("http://localhost1:8080/test?a=xxxx&b=yyyy" );
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,2000 );

		try {
			stringBuffer.append("---901--UnknownHostException  <br/>");
			client.executeMethod(getMethod);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*getMethod = new GetMethod("http://192.55.2.54:8080/test" );
		try {
			stringBuffer.append("---902--ConnectException  <br/>");
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,2000 );

			client.executeMethod(getMethod);
		} catch (IOException e) {
			e.printStackTrace();
		}
		getMethod = new GetMethod("http://localhost:8070/httpserver" );
		client.getHttpConnectionManager().getParams().setSoTimeout(50);

		try {
			stringBuffer.append("---903--SocketTimeoutException  <br/> ");
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,2000 );

			client.executeMethod(getMethod);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		getMethod = new GetMethod("https://192.168.2.106/info.php" );
		try {
			stringBuffer.append("---908--SSLHandshakeException  <br/>");
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,2000 );

			client.executeMethod(getMethod);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*getMethod = new GetMethod("http://localhost:8080/testdemo/mongo.do" );//https://192.168.2.106/info.php
		getMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		getMethod.addRequestHeader("Accept","image/bmp");
		try {
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,2000 );

			stringBuffer.append("---httperror--"+client.executeMethod(getMethod)+"  <br/>");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		getMethod.releaseConnection();

		return stringBuffer.toString();
	}

}

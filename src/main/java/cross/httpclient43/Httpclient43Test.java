package cross.httpclient43;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Httpclient43Test {

	public String getUrlStr[] = {
			"http://localhost:8080/testdemo/mysqldbcp.do",
	};
	@RequestMapping("/httpclient4.do")
	@ResponseBody
	public String get() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		StringBuffer stringBuffer=new StringBuffer();
		for (int i = 0; i < getUrlStr.length; i++) {
			HttpGet httpget = new HttpGet(getUrlStr[i]);
			//httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
			//RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1).setConnectTimeout(1).build();//设置请求和传输超时时间
			//httpget.setConfig(requestConfig);
			//httpget.addHeader("Accept","image/bmp");
			CloseableHttpResponse res = null;
			try {
				res = httpclient.execute(httpget);
				HttpEntity entity = res.getEntity();
				stringBuffer.append(getUrlStr[i]+"----"+EntityUtils.toString(entity)+"<br/>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}

	@RequestMapping("/httpclient4error.do")
	@ResponseBody
	public String httpclient4error(){
		StringBuffer sBuffer =new StringBuffer();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		/*200*/
		HttpGet httpget = new HttpGet("http://localhost:8080/testdemo/mongo.do");
		httpget.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		CloseableHttpResponse response = null;
		String html=new String();
		try {
			response = httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		try {
			html = EntityUtils.toString(entity);
			sBuffer.append("received:"+html+"<br>");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Header[] headers = response.getAllHeaders();
		for (int i=0; i<headers.length; i++) {
			sBuffer.append(headers[i]+"<br>");
		}
		sBuffer.append("<br><br>");

		httpget = new HttpGet("http://localhost1/info");
		try {
			httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sBuffer.append("---901-UnknownHostException---<br/> ");

		httpget = new HttpGet("http://192.55.2.54:8080/test");
		try {
			httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sBuffer.append("---902-ConnectException----<br/>");

		httpget = new HttpGet("http://localhost:8070/httpserver");
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1).setConnectTimeout(1).build();//设置请求和传输超时时间
		httpget.setConfig(requestConfig);
		try {
			httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sBuffer.append("---903-SocketTimeoutException---<br/> ");

		httpget = new HttpGet("svn://localhost/javaagent");
		CloseableHttpResponse res2 = null;
		try {
			httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sBuffer.append("---904-ClientProtocolException---<br/> ");

		httpget = new HttpGet("https://192.168.2.106/info.php");
		try {
			httpclient.execute(httpget);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sBuffer.append("---908-SSLHandshakeException---<br/>");

		httpget = new HttpGet("http://localhost:8080/testdemo/mongo.do");
		httpget.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		httpget.setHeader("Accept","image/bmp");

		try {
			sBuffer.append("---httperror--"+httpclient.execute(httpget).getStatusLine()+"<br/>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sBuffer.toString();
	}
}

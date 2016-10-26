package cross;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class UrlConnectionTest {
	@RequestMapping("url.do")
	@ResponseBody
	public String get(){
		StringBuffer sBuffer=new StringBuffer();
		String urlStr[] = {
				"http://localhost:8080/testdemo_war/dubbo.do",
				"http://localhost:8080/testdemo_war/thrift.do",

		};
		try {
        	for(int i=0;i<urlStr.length;i++){
            	URL url = new URL(urlStr[i]);
				URLConnection connection = url.openConnection();
            	connection.setDoInput(true);
            	connection.setDoOutput(true);
            	connection.connect();
            	Scanner in = new Scanner(connection.getInputStream());
			//	printResponseHeader(connection,sBuffer);
            	while (in.hasNextLine())
                	sBuffer.append(urlStr[i]+" ---------: "+in.nextLine()+"<br>");
        	}
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		return sBuffer.toString();
	}

	private static void printResponseHeader(URLConnection http, StringBuffer writer) throws UnsupportedEncodingException {
		Map<String, String> header = getHttpResponseHeader(http);
		for (Map.Entry<String, String> entry : header.entrySet()) {
			String key = entry.getKey() != null ? entry.getKey() + ":" : "";
			writer.append(key + entry.getValue()).append("\n");
		}
	}

	private static Map<String, String> getHttpResponseHeader(
			URLConnection http) throws UnsupportedEncodingException {
		Map<String, String> header = new LinkedHashMap<String, String>();
		for (int i = 0; ; i++) {
			String mine = http.getHeaderField(i);
			if (mine == null)
				break;
			header.put(http.getHeaderFieldKey(i), mine);
		}
		return header;
	}

	@RequestMapping("urlerror.do")
	@ResponseBody
	public String urlerror(String username,String password){
		StringBuffer stringBuffer=new StringBuffer();
		HttpURLConnection uRLConnection;
		try{
			URL url =new URL("http://localhost:8080/testdemo/mysqlc3p0.do");
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
			uRLConnection.setRequestMethod("POST");
			uRLConnection.connect();
			InputStream is = uRLConnection.getInputStream();
			BufferedReader br =new BufferedReader(new InputStreamReader(is));
			String readLine =null;
			while((readLine =br.readLine()) !=null){
				stringBuffer.append("received: :"+readLine);
			}
			stringBuffer.append("<br>");
			Map<String, List<String>> map = uRLConnection.getHeaderFields();
			for (String key : map.keySet()) {
				stringBuffer.append(key + ":" + map.get(key)+"<br>");
			}
			is.close();
			br.close();
			uRLConnection.disconnect();
			//return response;
		}catch(Exception e) {
			e.printStackTrace();
		}
		stringBuffer.append("<br><br>");

		/*try{
			stringBuffer.append("---901-UnknownHostException---<br/> ");
			URL url =new URL("http://localhost1/info.php");//https://192.168.2.106/info.php
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setRequestMethod("POST");
			uRLConnection.connect();
		}catch(Exception e) {
			e.printStackTrace();
		}

		try{
			stringBuffer.append("---902-ConnectException----<br/>");
			URL url =new URL("http://192.168.9.2/info");//https://192.168.2.106/info.php
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setRequestMethod("POST");
			uRLConnection.connect();
		}catch(Exception e) {
			e.printStackTrace();
		}*/

		try{
			stringBuffer.append("---903-SocketTimeoutException---<br/> ");
			URL url =new URL("http://localhost:8070/httpserver");//https://192.168.2.106/info.php
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setConnectTimeout(3000);
			uRLConnection.setReadTimeout(3000);
			uRLConnection.setRequestMethod("POST");
			uRLConnection.connect();
			String response="";
			InputStream is = uRLConnection.getInputStream();
			BufferedReader br =new BufferedReader(new InputStreamReader(is));
			String readLine =null;
			while((readLine =br.readLine()) !=null){
				response = response + readLine;
			}
			is.close();
			br.close();
			uRLConnection.disconnect();
			//return response;
		}catch(Exception e) {
			e.printStackTrace();
		}

	/*	try{
			stringBuffer.append("---MalformedURLException---<br/>");
			URL url =new URL("svn://localhost/javaagent");//https://192.168.2.106/info.php
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setRequestMethod("POST");
			uRLConnection.connect();
		}catch(Exception e) {
			e.printStackTrace();
		}

		try{
			stringBuffer.append("---908-SSLHandshakeException---<br/>");
			URL url =new URL("https://192.168.2.106/info.php");//https://192.168.2.106/info.php
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setRequestMethod("POST");
			uRLConnection.connect();
		}catch(Exception e) {
			e.printStackTrace();
		}

		try{
			URL url =new URL("http://localhost:8080/test/mongo.do");//https://192.168.2.106/info.php
			uRLConnection = (HttpURLConnection)url.openConnection();
			uRLConnection.setRequestMethod("POST");
			uRLConnection.setRequestProperty("Accept","image/bmp");
			uRLConnection.connect();
			stringBuffer.append("---httperror--"+uRLConnection.getResponseCode()+"<br/>");
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		return stringBuffer+"";
	}

	@RequestMapping("/urlsend.do")
	public void xxx() throws Exception{
		URL url = new URL("http://localhost:8080/test/ReadServlet");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Content-Type", "text/xml");

		OutputStream outputStream = httpURLConnection.getOutputStream();

		BufferedWriter bufferedWriter = new BufferedWriter(	new OutputStreamWriter(outputStream));

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("wlnmyr is very nice");
		stringBuffer.append("myr is a good man");

		bufferedWriter.write(stringBuffer.toString());
		bufferedWriter.flush();
		bufferedWriter.close();

		InputStream inputStream = httpURLConnection.getInputStream();

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {

			System.out.println(line);
		}
	}
}

package cross.httpclient31;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/5/31.
 */
public class HttpClient3Servlet extends HttpServlet {
    HttpClient client = new HttpClient();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      /*  GetMethod getMethod= new GetMethod("http://localhost:8080/test/hot");
        HttpClient client = new HttpClient();
        client.executeMethod(getMethod);*/
        PrintWriter out = response.getWriter();
       // out.write(getMethod.getResponseBodyAsString());
        URL url = new URL("http://localhost:8080/test/hot");
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        out.write(String.valueOf(connection.getInputStream()));
    }
}

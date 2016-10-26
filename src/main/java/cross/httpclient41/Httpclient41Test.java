package cross.httpclient41;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/8/1.
 */
@Controller
public class Httpclient41Test {

    @RequestMapping("httpclient41get.do")
    @ResponseBody
    public String get() {
        HttpClient httpclient = new DefaultHttpClient();
        StringBuffer stringBuffer=new StringBuffer();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://localhost:8080/testdemo/mongo.do");
            stringBuffer.append("executing request " + httpget.getURI());
            // 执行get请求.
            HttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            stringBuffer.append("--------------------------------------");
            // 打印响应状态
            stringBuffer.append(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
                stringBuffer.append("Response content length: "
                        + entity.getContentLength());
                // 打印响应内容
                stringBuffer.append("Response content: "
                        + EntityUtils.toString(entity));
            }
            stringBuffer.append("------------------------------------");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpclient.getConnectionManager().shutdown();
        }
        return stringBuffer.toString();
    }
    @RequestMapping("httpclient41post.do")
    @ResponseBody
    public String postForm() {
        StringBuffer stringBuffer=new StringBuffer();
        // 创建默认的httpClient实例.
        HttpClient httpclient = new DefaultHttpClient();
        // 创建httppost
        HttpPost httppost = new HttpPost(
                "http://localhost:8080/testdemo/mongo.do");
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("username", "admin"));
        formparams.add(new BasicNameValuePair("password", "123456"));
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            HttpResponse response;
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                stringBuffer.append("--------------------------------------");
                stringBuffer.append("Response content: "
                        + EntityUtils.toString(entity, "UTF-8"));
                stringBuffer.append("--------------------------------------");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            httpclient.getConnectionManager().shutdown();
        }
        return stringBuffer.toString();
    }

}


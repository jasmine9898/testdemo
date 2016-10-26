package cross.httpclient30;

/**
 * Created by admin on 2016/7/8.
 */
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Httpclient30Test {
    @RequestMapping(value="/httpclient30post.do")
    @ResponseBody
    public String hc30(){
        String url = "http://localhost:8080/test/mysqlc3p0.do";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        String response="received: ";
       /*
        NameValuePair[] data = { new NameValuePair("name", "admin"),
                new NameValuePair("password", "123456") };
        postMethod.setRequestBody(data);
        */
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
          /*  httpClient.getParams().setCookiePolicy(
                    CookiePolicy.BROWSER_COMPATIBILITY);*/

            postMethod.setRequestHeader("Referer", "http://www.ttttttt");
            postMethod.setRequestHeader("User-Agent", "test");

            httpClient.executeMethod(postMethod);
            // 获得登陆后的 Cookie
          /*  Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
            }*/
            // 进行登陆后的操作1581,1602,1603,1610,1609,1608,1607,1606,1605,1620,1619,1617,1616,1622,1626,1642,1648,1647,1657
            /*
            GetMethod getMethod = new GetMethod(dataUrl);
            // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
            getMethod.setRequestHeader("cookie", tmpcookies.toString());
            */
            // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
            // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外

           /*
            httpClient.executeMethod(getMethod);
              */
            // 打印出返回数据，检验一下是否成功
            response = postMethod.getResponseBodyAsString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }
    @RequestMapping(value="/httpclient301post.do")
    @ResponseBody
    public String hc301(){
        String url = "http://localhost:80810/test/springjdbc.do";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        String response="received: ";
        postMethod.setRequestHeader("Referer", "http://www.cc");
        postMethod.setRequestHeader("User-Agent", "test");
        try {
            httpClient.executeMethod(postMethod);
            response+= postMethod.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    @RequestMapping(value="/httpclient302post.do")
    @ResponseBody
    public String hc302(){
        String url = "http://localhost:80810/test/springjdbc.do";
        String url2 = "http://localhost:80810/test/springjdbc.do";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        PostMethod postMethod2 = new PostMethod(url2);
        String response="received: ";
        try {
            httpClient.executeMethod(postMethod);
            httpClient.executeMethod(postMethod2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response+"error";
    }
}


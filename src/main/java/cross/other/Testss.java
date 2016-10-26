package cross.other;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/24.
 */
@Controller
public class Testss {
    @RequestMapping("/testss.do")
    @ResponseBody
    public String xx(){
        String a="received :";
        try {
            URL url = new URL("http://localhost:8080/test/mongo.do");
            InputStream is = url.openStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l).append("/n");
            }
            System.out.println(bs.toString());
            a+=bs.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return a;
    }
}

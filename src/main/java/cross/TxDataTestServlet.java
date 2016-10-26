package cross;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/9/28.
 */
@Controller
public class TxDataTestServlet  {
    @RequestMapping("tx.do")
    @ResponseBody
    public String xx(){
        try {
            new UrlConnectionTest().get();
            new redistemplate.RedisTemplateController().xx();
            new db.mysql.hikariTest.HikariCPController().yyy();
            new mongodb.MongoSimpleTest().find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "xx";
    }
}

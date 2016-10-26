package db.mysql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
public class DbPoolController {
    @Resource
    JdbcTemplate mysqlc3p0jdbcTemplate;
    @Resource
    JdbcTemplate mysqldbcpjdbcTemplate;
    @Resource
    JdbcTemplate mysqlproxooljdbcTemplate;

    @RequestMapping(value="mysqlc3p0.do")
    @ResponseBody
    public String c3p0test() {
        return sqlexecute(mysqlc3p0jdbcTemplate,"mysqlc3p0");
    }

    @RequestMapping(value="mysqldbcp.do")
    @ResponseBody
    public String dbcptest() {
        return sqlexecute(mysqldbcpjdbcTemplate,"mysqldbcp");
    }

    @RequestMapping(value="mysqlproxool.do")
    @ResponseBody
    public String proxooltest() {
       return sqlexecute(mysqlproxooljdbcTemplate,"mysqlproxool");
    }

    public String sqlexecute(JdbcTemplate jdbcTemplate,String type){

        StringBuffer stringBuffer = new StringBuffer();
        jdbcTemplate.execute("delete from test_user where name='"+type+"';");
        jdbcTemplate.execute("insert into test_user(id,name,birthday) values(300,'"+type+"',now());");
        jdbcTemplate.execute("insert into test_user(id,name,birthday) values(300,'"+type+"',now());");

        List queryList = jdbcTemplate.queryForList("select * from test_user where name='"+type+"';");
        if (queryList != null) {
            Iterator it = queryList.iterator();
            while (it.hasNext()) {
                Map rece = (Map) it.next();
                stringBuffer.append(rece.get("name") + " " + rece.get("birthday"));
            }
        }
        return "result: " + stringBuffer;
    }

}

package db.mysql.hikariTest;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redistemplate.dao.UserDAO;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/7/21.
 */
@Controller
public class HikariCPController {
    @Resource
    HikariDataSource hikariCPDataSource;
    @RequestMapping("hikari2.do")
    @ResponseBody
    public String xxx() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Connection conn=hikariCPDataSource.getConnection();
            String sql4 = "delete from test_user where name like 'hikariDatasourcexxx'";  //test_user  test_user
            conn.prepareStatement(sql4).execute();

            String sql= "insert into test_user(id,name,birthday) values(2,'hikariDatasourcexxx',now())";
            conn.prepareStatement(sql).execute();
            String sql2 = "SELECT sleep(10),id,name,birthday FROM test_user WHERE name like 'hikariDatasourcexxx'";
            PreparedStatement preparedStatement=conn.prepareStatement(sql2);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                stringBuffer.append(resultSet.getInt("id")+"  ");
                stringBuffer.append(resultSet.getString("name")+" ");
                stringBuffer.append(resultSet.getString("birthday")+"<br>");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
    @Resource
    JdbcTemplate hikariCPDataSourceJdbcTemplate;
    @RequestMapping("hikari.do")
    @ResponseBody
    public String sss() {
        StringBuffer stringBuffer = new StringBuffer();
        hikariCPDataSourceJdbcTemplate.execute("delete from test_user where name='hikariDatasourcess';");
        hikariCPDataSourceJdbcTemplate.execute("insert into test_user(id,name,birthday) values(201,'hikariDatasourcesss',now())");
        List queryList=  hikariCPDataSourceJdbcTemplate.queryForList("SELECT sleep(10),id,name,birthday FROM test_user WHERE name like 'hikariDatasourcesss';");
        if (queryList != null) {
            Iterator it = queryList.iterator();
            while (it.hasNext()) {
                Map rece = (Map) it.next();
                stringBuffer.append(rece.get("name") + " " + rece.get("birthday"));
            }
        }
        return stringBuffer.toString();
    }
    public String yyy() {
        StringBuffer stringBuffer = new StringBuffer();
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        JdbcTemplate hikariCPDataSourceJdbcTemplate = (JdbcTemplate)ac.getBean("hikariCPDataSourceJdbcTemplate");
        hikariCPDataSourceJdbcTemplate.execute("delete from test_user where id=301;");

        hikariCPDataSourceJdbcTemplate.execute("insert into test_user(id,name,birthday) values(301,'hikariDatasourcesss',now())");
        List queryList=  hikariCPDataSourceJdbcTemplate.queryForList("SELECT sleep(10),id,name,birthday FROM test_user WHERE name like 'hikariDatasourcesss';");
        if (queryList != null) {
            Iterator it = queryList.iterator();
            while (it.hasNext()) {
                Map rece = (Map) it.next();
                stringBuffer.append(rece.get("name") + " " + rece.get("birthday"));
            }
        }
        return stringBuffer.toString();
    }
}

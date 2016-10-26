package db.mysql.hikariTest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;

/**
 * Created by admin on 2016/7/20.
 */
@Controller
public class HikariSimple {
    @RequestMapping("/hikari3.do")
    @ResponseBody
    public String test() {
        StringBuffer stringBuffer=new StringBuffer();
        HikariConfig config = new HikariConfig();
//        config.setMaximumPoolSize(3);
//        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.HikariDataSource");
//        config.addDataSourceProperty("serverName", "192.168.2.130");
//        config.addDataSourceProperty("port", "3306");
//        config.addDataSourceProperty("databaseName", "javatest");
//        config.addDataSourceProperty("user", "root");
//        config.addDataSourceProperty("password", "nbs2o13");

       config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://192.168.2.129:3306/javatest?user=root&password=nbs2o13&useUnicode=true&characterEncoding=utf8");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 500);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.setConnectionTestQuery("SELECT 1");
        config.setAutoCommit(true);
        //池中最小空闲链接数量
        config.setMinimumIdle(1);
        //池中最大链接数量
        config.setMaximumPoolSize(5);


    //HikariConfig config = new HikariConfig("/jdbc.properties");

        HikariDataSource ds = new HikariDataSource(config);
        try {
            Connection conn = ds.getConnection();
            Statement sm = conn.createStatement();
            String sqlString = "insert into test_user values(1,'hikariDatasource',now())";
            sm.execute(sqlString);
            String sql2 = "SELECT sleep(10),id,name,birthday FROM test_user WHERE name like ? limit ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, "%hikariDatasource%");
            preparedStatement.setInt(2, 1);
            String sql3 = "select sleep(80)";
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()) {
                stringBuffer.append(rs.getInt("id")+" ");
                stringBuffer.append(rs.getString("name")+" ");
                stringBuffer.append(rs.getString("birthday")+"<br>");
            }
            String sql4="delete from test_user where name like ?";  //test_user  test_user
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, "%hikariDatasource%");
            preparedStatement4.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}

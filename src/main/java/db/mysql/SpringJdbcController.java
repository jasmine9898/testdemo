package db.mysql;

import com.tingyun.api.agent.TingYun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
public class SpringJdbcController {
    @Resource
    private DataSource dataSourceMysql;

    @RequestMapping("mysql.do")
    public void cc(HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>spring - jdbc</title>");
            out.write(TingYun.getRUMHeader());
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>mysql-springjdbc-controller & api browser </h2>");
            Connection conn = dataSourceMysql.getConnection();
            Statement sm = conn.createStatement();
            String sql4 = "delete from test_user where name like ?";  //test_user  test_user
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, "%springjdbc%");
            preparedStatement4.execute();

            String sqlString = "insert into test_user (id,name,birthday)values(2,'springjdbc',now())";
            sm.execute(sqlString);
            String sql2 = "SELECT sleep(10),id,name,birthday FROM test_user WHERE name like ? limit ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, "%springjdbc%");
            preparedStatement.setInt(2, 1);
            String sql3 = "select sleep(80)";
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                response.getWriter().write(rs.getInt("id") + "<br>");
                response.getWriter().write(rs.getString("name") + "<br>");
                response.getWriter().write(rs.getString("birthday") + "<br>");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

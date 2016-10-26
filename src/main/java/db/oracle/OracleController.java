package db.oracle;

import com.tingyun.api.agent.TingYun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Controller
public class OracleController extends HttpServlet {
    @Resource
    private DataSource dataSourceOracle;

    @RequestMapping("/oracle.do")
    public void oraclejdbctest(HttpServletResponse response, PrintWriter out) {
        response.setContentType("text/html; charset=utf-8");
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>oracle</title>");
            out.write(TingYun.getRUMHeader());
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>oracle-springjdbc-controller & api browser</h2>");

            Connection conn = dataSourceOracle.getConnection();
            Statement sm = conn.createStatement();
            String sqlString = "insert into test_user values(1,'springjdbc',to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";
            sm.execute(sqlString);
            String sql2 = "SELECT * FROM test_user WHERE name like ? and rownum<=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, "%springjdbc%");
            preparedStatement.setInt(2, 5);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                out.println(rs.getInt("id"));
                out.println(rs.getString("name"));
                out.println(rs.getString("birthday"));
            }
            String sql4 = "delete from test_user where name like ?";
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, "%springjdbc%");
            preparedStatement4.execute();
            conn.close();
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

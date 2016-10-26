package db.oracle;

import com.tingyun.api.agent.TingYun;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/oracleservlet")
public class OracleServlet extends HttpServlet {
    //@Resource
//private DataSource dataSourceOracle;
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        DataSource dataSourceOracle = (DataSource) ctx.getBean("dataSourceOracle");
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>oracle</title>");
            out.write(TingYun.getRUMHeader());
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>oracle-springjdbc&api browser</h2>");

            Connection conn = dataSourceOracle.getConnection();
            Statement sm = conn.createStatement();
            String sqlString = "insert into test_user values(1,'springjdbc',to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";
            sm.execute(sqlString);
            String sql2 = "SELECT * FROM test_user WHERE name like ? and rownum<=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setString(1, "%springjdbc%");
            preparedStatement.setInt(2, 5);
            Boolean r = preparedStatement.execute();
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

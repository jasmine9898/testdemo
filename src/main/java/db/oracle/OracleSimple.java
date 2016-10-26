package db.oracle;

import oracle.jdbc.driver.OraclePreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/OracleSimple")
public class OracleSimple extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
        String username = "root";
        String password = "nbs2o13";
        String sql = "insert into test_user values (?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";
        try {
            Class.forName(driver);
            // new oracle.jdbc.driver.OracleDriver();
            Connection conn = DriverManager.getConnection(url, username, password);
            // Statement stat = conn.createStatement();
            OraclePreparedStatement pstmt = (OraclePreparedStatement)(conn.prepareStatement(sql));
            pstmt.setInt(1, 1);
            pstmt.setString(2, "PreparedStatement");
            pstmt.executeUpdate();
            System.out.println(pstmt.getClass().getName());
            String sql2 = "select a.name,b.name from t_user a,test_user b where rownum<=20";
          //  String sql2 = "SELECT * FROM test_user WHERE name like ? and rownum<=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
           // preparedStatement.setString(1, "%PreparedStatement%");
           // preparedStatement.setInt(2, 5);
            Boolean r=preparedStatement.execute();
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()&&response!=null) {
            	//response.getWriter().write(rs.getInt("id"));  
            	response.getWriter().write(rs.getString("name"));
            	//response.getWriter().write(rs.getString("birthday"));
            	response.getWriter().write("<br>");
            }
            String sql4="delete from test_user where name like ?";
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, "%PreparedStatement%");
            preparedStatement4.execute(); 
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
 
            e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }
 
    }
	public static void main(String[] args) {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.2.129:1521:orcl";
        String username = "root";
        String password = "nbs2o13";
        String sql = "insert into test_user values (?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'))";


        try {
            Thread.sleep(300000);

            Class.forName(driver);
            // new oracle.jdbc.driver.OracleDriver();
            Connection conn = DriverManager.getConnection(url, username, password);
            // Statement stat = conn.createStatement();

            OraclePreparedStatement pstmt = (OraclePreparedStatement) (conn.prepareStatement(sql));
            pstmt.setInt(1, 1);
            pstmt.setString(2, "PreparedStatement");
            pstmt.executeUpdate();
            System.out.println(pstmt.getClass().getName());
                String sql2 = "select a.name,b.name from t_user a,test_user b where rownum<=20";
            //  String sql2 = "SELECT * FROM test_user WHERE name like ? and rownum<=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
            // preparedStatement.setString(1, "%PreparedStatement%");
            // preparedStatement.setInt(2, 5);
            Boolean r = preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            String sql4 = "delete from test_user where name like ?";
            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
            preparedStatement4.setString(1, "%PreparedStatement%");
            preparedStatement4.execute();
            conn.close();
            Thread.sleep(200000);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        e.printStackTrace();
        } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}

package cross.other;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by admin on 2016/8/3.
 */
@WebServlet(urlPatterns = "/ReadServlet")
public class ReadServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("begin read");
        ServletInputStream inStream = request.getInputStream(); // 取HTTP请求流
        int size = request.getContentLength(); // 取HTTP请求流长度
        byte[] buffer = new byte[size]; // 用于缓存每次读取的数据
        byte[] in_b = new byte[size]; // 用于存放结果的数组
        int count = 0;
        int rbyte = 0;
        // 循环读取
        while (count < size)
        {
            rbyte = inStream.read(buffer); // 每次实际读取长度存于rbyte中 sflj
            for (int i = 0; i < rbyte; i++)
            {
                in_b[count + i] = buffer[i];
            }
            count += rbyte;
        }
        System.out.println("result:" + new String(in_b,0,in_b.length));

        response.setContentType("text/html");
        //注意响应中文数据时要设置
        response.setCharacterEncoding("GBK");
        PrintWriter out = response.getWriter();
        //回与响应数据
        out.write("您已经请求成功，这是响应数据!");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        this.doGet(request, response);
    }

}

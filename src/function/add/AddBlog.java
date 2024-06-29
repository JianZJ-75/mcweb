package function.add;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UtilTools;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:13
 */
@WebServlet(name = "AddBlog", urlPatterns = "/AddBlog")
public class AddBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获取博客信息
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String id = request.getParameter("id");
        String username = "";
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            Statement statement = connection.createStatement();
            // 获取用户名
            ResultSet res = statement.executeQuery("select username from user where id = " + id);
            while (res.next()) {
                username = res.getString(1);
            }
            // 创建博客
            PreparedStatement ps = connection.prepareStatement(UtilTools.sqlAddBlog);
            ps.setString(1, title);
            ps.setString(2, username);
            ps.setString(3, content);
            ps.setString(4, id);
            out.println("添加成功!");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

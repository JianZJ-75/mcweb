package function.detail;

import function.Blog;
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
 * @Date 2024/6/29 20:14
 */
@WebServlet(name = "DeleteBlog", urlPatterns = "/DeleteBlog")
public class DeleteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获得用户id和博客id
        String id = request.getParameter("id");
        String blogId = request.getParameter("blogId");
        String sql = "select * blog where userId = " + id + " and blogId = " + blogId;
        Blog blog = new Blog();
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            // 判断是否是该用户的博客
            while (res.next()) {
                blog.setTitle(res.getString("title"));
            }
            if (blog.getTitle() == null) {
                out.println("您没有删除的权限!");
            } else {
                PreparedStatement ps = connection.prepareStatement(UtilTools.sqlDel);
                ps.setString(1, blogId);
                ps.executeUpdate();
                out.println("删除成功!");
            }
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

package function.blog;

import com.alibaba.fastjson2.JSON;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:12
 */
@WebServlet(name = "LoadBlog", urlPatterns = "/LoadBlog")
public class LoadBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sql = "select * from blog";
        List<Blog> blogs = new ArrayList<>();
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                // 获取博客
                Blog blog = new Blog();
                blog.setId(res.getInt("id"));
                blog.setTitle(res.getString("title"));
                blog.setContent(res.getString("content"));
                blog.setAuthor(res.getString("username"));
                blog.setDate(res.getDate("postTime"));
                blogs.add(blog);
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println(JSON.toJSONString(blogs));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package function.author;

import function.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UtilTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:31
 */
@WebServlet(name = "RegisterCheck", urlPatterns = "/RegisterCheck")
public class RegisterCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        // 获取注册信息
//        String url = UtilTools.repoPhoto + request.getParameter("url");
        String url = UtilTools.repoPhoto + "1.jpg";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println(username);
        out.println(password);
        // 查询该用户名是否已经存在
        String sql = "select * from user where username=" + username;
        User user = new User();
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                user.setUsername(res.getString("username"));
            }
            if (user.getUsername() != null) {
                out.println("该用户名已经存在!");
            } else {
                PreparedStatement ps = connection.prepareStatement(UtilTools.sqlAdd);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setBinaryStream(3, new FileInputStream(url));
                ps.executeUpdate();
                out.println("注册完成");
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

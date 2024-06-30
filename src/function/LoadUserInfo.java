package function;

import com.alibaba.fastjson2.JSON;
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
 * @Date 2024/6/30 22:48
 */
@WebServlet(name = "LoadUserInfo", urlPatterns = "/LoadUserInfo")
public class LoadUserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sql = UtilTools.checkSqlById + request.getParameter("id");
        User user = new User();
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("pwd"));
                String base64Image = res.getString("photo");
                user.setPhoto(base64Image); // 设置用户的照片 Base64 编码
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println(JSON.toJSONString(user));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

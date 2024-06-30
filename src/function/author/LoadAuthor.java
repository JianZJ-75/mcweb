package function.author;

import com.alibaba.fastjson2.JSON;
import function.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UtilTools;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:09
 */
@WebServlet(name = "LoadAuthor", urlPatterns = "/LoadAuthor")
public class LoadAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sql = "select * from user";
        List<User> users = new ArrayList<>();
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                // 获取作者信息
                User user = new User();
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("pwd"));
                String base64Image = res.getString("photo");
//                byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
//                response.setContentType("image/jpeg"); // 根据实际情况调整
                user.setPhoto(base64Image); // 设置用户的照片 Base64 编码
                users.add(user);
//                response.getOutputStream().write(imageBytes);
//                InputStream content = res.getBinaryStream("photo");
//                // 更新头像
//                UtilTools.loadPhoto(user.getAbsoluteUrl(), content);
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println(JSON.toJSONString(users));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

package function.backstage;

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
 * @Date 2024/6/30 23:16
 */
@WebServlet(name = "DeleteUser", urlPatterns = "/DeleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String sql = UtilTools.deleteSql;
        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("删除成功!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

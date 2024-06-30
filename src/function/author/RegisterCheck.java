package function.author;

import function.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.Part;

import jakarta.servlet.http.HttpServletResponse;
import util.UtilTools;

import java.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:31
 */
@WebServlet(name = "RegisterCheck", urlPatterns = "/RegisterCheck")
@MultipartConfig
public class RegisterCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Part filePart = request.getPart("photo");

        if (filePart == null) {
            System.out.println("文件部分为空");
        } else {
            System.out.println("文件部分已接收");
        }

        InputStream fileContent = filePart.getInputStream();
        byte[] bytes = fileContent.readAllBytes();
        String base64Photo = Base64.getEncoder().encodeToString(bytes);

        try {
            Class.forName(UtilTools.className);
            Connection connection = DriverManager.getConnection(UtilTools.url, UtilTools.user, UtilTools.password);

            // Check if the username already exists
            String checkSql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            ResultSet res = checkStmt.executeQuery();

            if (res.next()) {
                out.println("{\"message\": \"Username already exists\"}");
            } else {
                // Insert new user
                String insertSql = "INSERT INTO user (username, pwd, photo) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.setString(3, base64Photo);
                insertStmt.executeUpdate();

                System.out.println(insertSql);
                out.println("{\"message\": \"User created successfully\"}");
            }

            res.close();
            checkStmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("{\"message\": \"Internal server error\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType == null || !contentType.startsWith("multipart/form-data")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("{\"message\": \"Invalid content type\"}");
            return;
        }

        // 继续处理请求
        doGet(request, response);
    }
}

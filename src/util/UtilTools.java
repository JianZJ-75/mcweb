package util;

import java.io.*;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:17
 */
public class UtilTools {

    // jdbc驱动类名
    public static final String className = "com.mysql.cj.jdbc.Driver";
    // 数据库地址
    public static final String url = "jdbc:mysql://182.254.157.244:3306/web6";
    // 数据库账号
    public static final String user = "usr";
    // 数据库密码
    public static final String password = "123456";
    // SQL查找用户
    public static final String checkSql = "select * from user where username = ?";
    public static final String checkSqlById = "select * from user where id = ";
    // SQL删除用户
    public static final String deleteSql = "delete from user where username = ?";
    // SQL添加新用户
    public static final String sqlAdd = "insert into user(username, pwd, photo) values (?,?,?)";
    // SQL删除博客
    public static final String sqlDel = "delete from blog where blogId = ?";
    // SQL添加博客
    public static final String sqlAddBlog = "insert into blog(title, username, content, postTime, userId) values(?,?,?,now(),?)";

}

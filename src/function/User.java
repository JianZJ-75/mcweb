package function;

import util.UtilTools;

import java.io.File;
import java.io.IOException;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:34
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String photo; // 添加 photo 字段

    public User() {
    }

    public User(int id, String username, String password, String photo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", photo = " + photo + "}";
    }
}
package function;

import util.UtilTools;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:34
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String url;

    public User() {
    }

    public User(int id, String username, String password, String url) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.url = url;
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
        url = UtilTools.repo + this.username + ".jpg";
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
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", url = " + url + "}";
    }
}
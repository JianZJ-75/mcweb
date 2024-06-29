package function;

import java.sql.Date;

/**
 * @Author JianZJ
 * @Date 2024/6/29 23:44
 */
public class Blog {
    private int id;
    private String title;
    private String author;
    private String content;
    private Date date;

    public Blog() {
    }

    public Blog(int id, String title, String author, String content, Date date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.date = date;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return "Blog{id = " + id + ", title = " + title + ", author = " + author + ", content = " + content + ", date = " + date + "}";
    }
}
package function;

import java.io.*;

/**
 * @Author JianZJ
 * @Date 2024/6/29 20:38
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\Git\\demo1\\webworkspace\\src\\resource\\test\\75.jpg"));
        byte[] buf = new byte[1024];
        int len;
        String content = "";
        while ((len = bis.read(buf)) != -1) {
            content += new String(buf, 0, len);
        }
        System.out.println(content);
        bis.close();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\Git\\demo1\\webworkspace\\src\\resource\\test\\tmp.jpg"));
        bos.write(content.getBytes());
        bos.close();
    }
}
package Webmagic.csdnblog.com;

/***Created by moyongzhuo
 *On 2017/10/11  ***13:18.
 ******/
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CsdnBlogDao {

    private Connection conn = null;
    private Statement stmt = null;

    public  CsdnBlogDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/spider?user=root&password=xiemo&useUnicode=true&characterEncoding=UTF8";
            conn = (Connection)DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接数据库成功");
    }

    public int add(CsdnBlog csdnBlog) {
        try {
            String sql = "INSERT INTO `spider`.`csdnblog` (`id`, `title`, `date`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, csdnBlog.getId());
            ps.setString(2, csdnBlog.getTitle());
            ps.setString(3, csdnBlog.getDate());
            ps.setString(4, csdnBlog.getTags());
            ps.setString(5, csdnBlog.getCategory());
            ps.setInt(6, csdnBlog.getView());
            ps.setInt(7, csdnBlog.getComments());
            ps.setInt(8, csdnBlog.getCopyright());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

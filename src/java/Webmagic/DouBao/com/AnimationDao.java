package Webmagic.DouBao.com;

import Webmagic.csdnblog.com.CsdnBlog;
import org.junit.Test;

import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/16  ***13:16.
 ******/
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class AnimationDao {

    private Connection conn = null;
    private Statement stmt = null;

    public  AnimationDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/spider?user=root&password=xiemo&useUnicode=true&characterEncoding=UTF8";
            conn = (Connection) DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("连接数据库成功");
    }

    public int add(Animation animation) {
        try {
           // String sql = "INSERT INTO `spider`.`animationdao` (`id`, `title`, `date`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            String sql = "INSERT INTO `spider`.`animationdao` ( 'name','entityUrl','director','writer','cast','type','GuanWang','countryArea','language','issue'," +
                    "'duration','imdbNum','ratingNum','ranking','locationName','summariness','award')" +
                    "  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, animation.getName());
            ps.setString(2, animation.getEntityUrl());
            ps.setString(3, animation.getDirector());
            ps.setString(4, animation.getWriter());
            ps.setString(5, animation.getCast());
            ps.setString(6, animation.getType());
            ps.setString(7, animation.getGuanWang());
            ps.setString(8, animation.getCountryArea());
            ps.setString(9, animation.getLanguage());
            ps.setString(10, animation.getIssue());
            ps.setString(11, animation.getDuration());
            ps.setString(12, animation.getImdbNum());
            ps.setString(13, animation.getRatingNum());
            ps.setString(14, animation.getRanking());
            ps.setString(15, animation.getLocationName());
            ps.setString(16, animation.getSummariness());
            ps.setString(17, animation.getAward());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
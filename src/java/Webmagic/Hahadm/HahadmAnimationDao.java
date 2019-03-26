package Webmagic.Hahadm;

import Webmagic.csdnblog.com.CsdnBlog;

import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/21  ***19:41.
 ******/
public class HahadmAnimationDao {

    private Connection conn = null;
    private Statement stmt = null;

    public  HahadmAnimationDao() {
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

    public int add(HahadmAnimation hahadmAnimation) {
        try {
            String sql = "INSERT INTO `spider`.`haha450` (`id`,`hahawebname`,`tag`,`year`, `director`, `Content`,`goal`,`mentotal`, `original`,`screenwriter`,`allname`, `dubbing`, `role`,`area`,`languge`,`url`,`vediourl`,`hhimageurl`,`contentdetail`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            //"INSERT INTO `spider`.`HahadmTest` (`hahawebname`, `director`, `Content`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hahadmAnimation.getId());
            ps.setString(2, hahadmAnimation.getHahawebname());
            ps.setString(3, hahadmAnimation.getTag());
            ps.setString(4, hahadmAnimation.getYear());
            ps.setString(5, hahadmAnimation.getDirector());
            ps.setString(6, hahadmAnimation.getContent());
            ps.setFloat(7, hahadmAnimation.getGoal());
            ps.setInt(8, hahadmAnimation.getMentotal());

            ps.setString(9, hahadmAnimation.getOriginal());
            ps.setString(10, hahadmAnimation.getScreenwriter());
            ps.setString(11, hahadmAnimation.getAllname());
            ps.setString(12, hahadmAnimation.getDubbing());
            ps.setString(13, hahadmAnimation.getRole());
            ps.setString(14, hahadmAnimation.getArea());
            ps.setString(15, hahadmAnimation.getLanguge());

            ps.setString(16, hahadmAnimation.getUrl());
            ps.setString(17, hahadmAnimation.getVediourl());
            ps.setString(18, hahadmAnimation.getHhimageurl());
            ps.setString(19, hahadmAnimation.getContentdetail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

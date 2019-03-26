package Webmagic.Kt51Animation;


import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/24  ***11:53.
 ******/
public class Kt51AnimatonDao {

    private Connection conn = null;
    private Statement stmt = null;

    public  Kt51AnimatonDao() {
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

    public int add(Kt51Animation hahadmAnimation) {
        try {
            String sql = "INSERT INTO `spider`.`kt51_animationall_1024` (`id`,`hahawebname`,`url`,`content`, `uptime`, `year`,`original`,`language`,`tag`, `mainrole`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            //"INSERT INTO `spider`.`HahadmTest` (`hahawebname`, `director`, `Content`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hahadmAnimation.getId());
            ps.setString(2, hahadmAnimation.getHahawebname());
            ps.setString(3, hahadmAnimation.getUrl());
            ps.setString(4, hahadmAnimation.getContent());
            ps.setString(5, hahadmAnimation.getUptime());
            ps.setString(6, hahadmAnimation.getYear());
            ps.setString(7, hahadmAnimation.getOriginal());
            ps.setString(8, hahadmAnimation.getLanguage());

            ps.setString(9, hahadmAnimation.getTag());
            ps.setString(10, hahadmAnimation.getMainrole());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

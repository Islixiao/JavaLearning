package Webmagic.Hahadm;

import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/21  ***20:46.
 ******/
public class IIHahadmAnimationDao {

    private Connection conn = null;
    private Statement stmt = null;

    public  IIHahadmAnimationDao() {
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
    //private int goal;//评分
    //private int mentotal;//评分人数
    //private int id;
    public int add(IIHahadmAnimation iihahadmAnimation) {
        try {
            String sql = "INSERT INTO `spider`.`HahadmTestInt` (`hahawebname`, `director`, `Content`, `goal`, `mentotal`, `id`) VALUES (?, ?, ? , ?, ?, ?);";
            //"INSERT INTO `spider`.`HahadmTest` (`hahawebname`, `director`, `Content`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, iihahadmAnimation.getHahawebname());
            ps.setString(2, iihahadmAnimation.getDirector());
            ps.setString(3, iihahadmAnimation.getContent());
            ps.setFloat(4, iihahadmAnimation.getGoal());
            ps.setInt(5, iihahadmAnimation.getMentotal());
            ps.setInt(6, iihahadmAnimation.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

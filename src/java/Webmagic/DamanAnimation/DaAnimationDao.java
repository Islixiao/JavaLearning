package Webmagic.DamanAnimation;

/***Created by moyongzhuo
 *On 2017/10/23  ***19:45.
 ******/
 import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/21  ***19:41.
 ******/
public class  DaAnimationDao {

    private Connection conn = null;
    private Statement stmt = null;

    public  DaAnimationDao() {
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

    public int add(DaAnimation hahadmAnimation) {
        try {
            String sql = "INSERT INTO `spider`.`daman_comic1_1024` (`id`,`damanwebname`,`content`,`imgurl`, `url`, `vediourl`,`mainrole`,`area`, `tag`,`language`,`year`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            //"INSERT INTO `spider`.`HahadmTest` (`hahawebname`, `director`, `Content`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, hahadmAnimation.getId());
            ps.setString(2, hahadmAnimation.getDamanwebname());
            ps.setString(3, hahadmAnimation.getContent());
            ps.setString(4, hahadmAnimation.getImgurl());
            ps.setString(5, hahadmAnimation.getUrl());
            ps.setString(6, hahadmAnimation.getVediourl());
            ps.setString(7, hahadmAnimation.getMainrole());
            ps.setString(8, hahadmAnimation.getArea());
            ps.setString(9, hahadmAnimation.getTag());

            ps.setString(10, hahadmAnimation.getLanguage());
            ps.setString(11, hahadmAnimation.getYear());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

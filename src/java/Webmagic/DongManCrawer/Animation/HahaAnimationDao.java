package Webmagic.DongManCrawer.Animation;


import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/22  ***0:07.
 ******/
public class HahaAnimationDao {
    private Connection conn = null;
    private Statement stmt = null;

    public HahaAnimationDao() {
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

    public int add(HahaAnimation hahaAnimation) {
        try {
            String sql = "INSERT INTO `spider`.`vedio` (`id`, `hahawebname`, `tag`, `year`, `director', 'original', 'Screenwriter', 'allname', 'dubbing', 'role', 'area', 'languge', 'content', 'contentdetail', 'goal', 'mentotal', 'url', 'vediourl', 'hhimageurl') VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?);";
            //, `date`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,hahaAnimation.getId());
            ps.setString(2,hahaAnimation.getHahawebname());
            ps.setString(3,hahaAnimation.getTag());
            ps.setString(4,hahaAnimation.getYear());
            ps.setString(5,hahaAnimation.getDirector());
            ps.setString(6,hahaAnimation.getOriginal());
            ps.setString(7,hahaAnimation.getScreenwriter());
            ps.setString(8,hahaAnimation.getAllname());
            ps.setString(9,hahaAnimation.getDubbing());
            ps.setString(10,hahaAnimation.getRole());
            ps.setString(11,hahaAnimation.getArea());
            ps.setString(12,hahaAnimation.getLanguge());
            ps.setString(13,hahaAnimation.getContent());
            ps.setString(14,hahaAnimation.getContentdetail());
            ps.setFloat(15,hahaAnimation.getGoal());
            ps.setInt(16,hahaAnimation.getMentotal());
            //ps.setString(16,baiduanimation.getAllname());
            ps.setString(17,hahaAnimation.getUrl());
            ps.setString(18,hahaAnimation.getVediourl());
            ps.setString(19,hahaAnimation.getHhimageurl());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

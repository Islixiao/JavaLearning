package Webmagic.donghua.dmzj.com;

/***Created by moyongzhuo
 *On 2017/10/23  ***12:08.
 ******/
    import Webmagic.csdnblog.com.CsdnBlog;

import java.sql.*;

    /***Created by moyongzhuo
     *On 2017/10/21  ***19:41.
     ******/
    public class  DmzjAnimationDao {

        private Connection conn = null;
        private Statement stmt = null;

        public  DmzjAnimationDao() {
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

        public int add(DmzjAnimation hahadmAnimation) {
            try {
                String sql = "INSERT INTO `spider`.`20171023_dmzj_animationall_1823` (`id`,`hahawebname`,`antag`,`japanname`, `allname`, `year`,`state`,`tag`, `original`,`screenwriter`,`company`, `website`, `content`,`contentdetail`,`goal`,`mentotal`,`url`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                //"INSERT INTO `spider`.`HahadmTest` (`hahawebname`, `director`, `Content`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, hahadmAnimation.getId());
                ps.setString(2, hahadmAnimation.getHahawebname());
                ps.setString(3, hahadmAnimation.getAntag());
                ps.setString(4, hahadmAnimation.getJapanname());
                ps.setString(5, hahadmAnimation.getAllname());
                ps.setString(6, hahadmAnimation.getYear());
                ps.setString(7, hahadmAnimation.getState());
                ps.setString(8, hahadmAnimation.getTag());

                ps.setString(9, hahadmAnimation.getOriginal());
                ps.setString(10, hahadmAnimation.getScreenwriter());
                ps.setString(11, hahadmAnimation.getCompany());
                ps.setString(12, hahadmAnimation.getWebsite());
                ps.setString(13, hahadmAnimation.getContent());
                ps.setString(14, hahadmAnimation.getContentdetail());
                ps.setString(15, hahadmAnimation.getGoal());

                ps.setString(16, hahadmAnimation.getMentotal());
                ps.setString(17, hahadmAnimation.getUrl());
                return ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1;
        }

}

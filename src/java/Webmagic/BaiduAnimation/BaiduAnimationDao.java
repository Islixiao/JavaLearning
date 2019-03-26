package Webmagic.BaiduAnimation;

import Webmagic.csdnblog.com.CsdnBlog;

import java.sql.*;

/***Created by moyongzhuo
 *On 2017/10/18  ***20:49.
 ******/
public class BaiduAnimationDao {
    private Connection conn = null;
    private Statement stmt = null;

    public BaiduAnimationDao() {
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

    public int add(BaiduAnimation baiduanimation) {
        try {
            String sql = "INSERT INTO `spider`.`hahaAnimation` (`id`+`hahawebname` +`tag` + `year`+ `director'+ 'original' + 'Screenwriter' + 'allname' + 'dubbing' + 'role' + 'area' + 'languge' + 'content' + 'contentdetail' + 'goal' + 'mentotal' + 'url' + 'vediourl' + 'hhimageurl') VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?);";
                    //, `date`, `tags`, `category`, `view`, `comments`, `copyright`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,baiduanimation.getId());
            ps.setString(2,baiduanimation.getHahawebname());
            ps.setString(3,baiduanimation.getTag());
            ps.setString(4,baiduanimation.getYear());
            ps.setString(5,baiduanimation.getDirector());
            ps.setString(6,baiduanimation.getOriginal());
            ps.setString(7,baiduanimation.getScreenwriter());
            ps.setString(8,baiduanimation.getAllname());
            ps.setString(9,baiduanimation.getDubbing());
            ps.setString(10,baiduanimation.getRole());
            ps.setString(11,baiduanimation.getArea());
            ps.setString(12,baiduanimation.getLanguge());
            ps.setString(13,baiduanimation.getContent());
            ps.setString(14,baiduanimation.getContent());
            ps.setString(15,baiduanimation.getMentotal());
            //ps.setString(16,baiduanimation.getAllname());
            ps.setString(16,baiduanimation.getUrl());
            ps.setString(17,baiduanimation.getVediourl());
            ps.setString(18,baiduanimation.getHahawebname());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

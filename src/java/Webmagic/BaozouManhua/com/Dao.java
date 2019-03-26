package Webmagic.BaozouManhua.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/***Created by moyongzhuo
 *On 2017/10/10  ***21:05.
 ******/
public class Dao {

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/spider";
        // //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
        String username = "root";
        String password = "xiemo";
        Connection conn = null;
        try {
            Class.forName(driver); // classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static int insert(BaozouNews news) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into baozou (author,time,content) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, news.getAuthor());
            pstmt.setString(2, news.getTime());
            pstmt.setString(3, news.getContent());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
/***
 try {

 //第一步：注册链接驱动

 Class.forName("com.mysql.jdbc.Driver");

 // new com.mysql.jdbc.Driver();//注册链接驱动

 //第二步：连接数据库

 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "enweytest");

 //第三步：创建数据库语句对象

 stmt = conn.createStatement();

 //第四步：执行数据库语句 会返回ResultSet对象

 rs = stmt.executeQuery("select * from user");

 //第五步：遍历数据库

 while(rs.next()) {

 System.out.println(rs.getString("id"));

 }
 ***/
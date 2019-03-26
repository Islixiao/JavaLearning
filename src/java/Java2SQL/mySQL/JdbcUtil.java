package Java2SQL.mySQL;

/***Created by moyongzhuo
 *On 2018/2/11  ***18:01.
 ******/

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public final class JdbcUtil {
    private static String url;//数据库链接字符串
    private static String user;//用户名
    private static String pass;//密码
    private static String driver;//数据库驱动
    //加载类时，先将jdbc链接数据库信息获取并赋值
    static {
        Properties prop = new Properties();
        InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\JavaConnect\\mySQL\\mysql.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        pass = prop.getProperty("pass");
        driver = prop.getProperty("driver");
    }
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //取得链接
    public static Connection getMySqlConnection(){
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //关闭结果集
    public static  void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭封装SQl命令的对象
    public static  void close(Statement state){
        if(state!=null){
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭链接
    public static  void close(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sqlstr = "select * from employee";
        try {
            conn = JdbcUtil.getMySqlConnection();
            state = conn.createStatement();
            rs = state.executeQuery(sqlstr);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("username");
                //String sex = rs.getString("sex");
                //float salary = rs.getFloat("salary");
               System.out.println(id + ":" + name + ":" );
            }
        } catch (Exception e) {
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(state);
            JdbcUtil.close(conn);
        }
    }
}

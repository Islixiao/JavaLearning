//package Java2SQL;
//
//import java.sql.*;
//import Java2SQL.ExcelUtil;
///***Created by moyongzhuo
// *On 2018/3/1  ***13:35.
// ******/
//public class DailryUsed {
//    public static String dbs = "on_linedata_11";
//    public static void main(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
//        //jdbc:mysql://localhost:3306/spider?user=root&password=xiemo&useUnicode=true&characterEncoding=UTF8
//        Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bak?rewriteBatchedStatements=true&user=root&password=xiemo&characterEncoding=UTF8");
//        Statement stmt = conn.createStatement();//创建一个Statement对象
//        //一个月31天
//        ResultSet rs1 = stmt.executeQuery(sqlmac_mouth);//创建数据对象
//        System.out.println("#####################一个月31天######################");
//        while (rs1.next()) {
//            String kkt = rs1.getString("日期") + "\t" + rs1.getInt("mac数");
//            System.out.println(kkt);
//        }
//        ExcelUtil.write(excelAsyncWrite, excel_result_path, contents, "BAIKE");
//
//
//        //rs.last();//对rs的操作应马上操作,操作完后再从数据库得到rst,再对rst操作
//        //一天24小时
//        Statement stmt2 = conn.createStatement();//创建一个Statement对象
//        ResultSet rs2 = stmt2.executeQuery(sqlday_24);//创建数据对象
//        System.out.println("#####################一天24小时######################");
//        while (rs2.next()) {
//            String kkt = rs2.getString("小时") + "\t" + rs2.getInt("总数");
//            System.out.println(kkt);
//        }
//
//        //rates比率
//        System.out.println("#####################rates比率######################");
//        Statement stmt3 = conn.createStatement();//创建一个Statement对象
//        ResultSet rs3 = stmt3.executeQuery(sqlnull_rates);//创建数据对象
//        while (rs3.next()) {
//            String kkt = rs3.getString("数据频次范围") + "\t" + rs3.getString("总数") + "\t" + rs3.getString("null数");
//            System.out.println(kkt);
//        }
//
//        //星期
//        Statement stmt4 = conn.createStatement();//创建一个Statement对象
//        ResultSet rs4 = stmt4.executeQuery(sqlmac_week);//创建数据对象
//        System.out.println("#####################星期######################");
//        while (rs4.next()) {
//            String kkt =rs4.getString("星期") + "\t" + rs4.getString("mac数");
//                    System.out.println(kkt);
//        }
//
//        //null
//        Statement stmt5 = conn.createStatement();//创建一个Statement对象
//        ResultSet rs5 = stmt5.executeQuery(sqlnull_nums);//创建数据对象
//        System.out.println("#####################null######################");
//        while (rs5.next()) {
//            String kkt = rs5.getString("日期") + "\t" + rs5.getString("mac总数") + "\t" + rs5.getString("null总数") + "\t" + rs5.getString("语句总数");
//            System.out.println(kkt);
//        }
//    }
//
//    public static String sqlmac_mouth = "SELECT DATE_FORMAT(data_time ,'%d' ) AS '日期', COUNT(DISTINCT mac) AS 'mac数' \n" +
//            "\tFROM "+dbs+" \n" +
//            "\tGROUP BY DATE_FORMAT(data_time ,'%d' )";
//
//    public static String sqlmac_week=" SELECT\n" +
//            "  DATE_FORMAT(a.data_time, '%w') AS '星期',\n" +
//            "  COUNT(DISTINCT w_d_m) / COUNT(DISTINCT w_d) AS 'mac数'\n" +
//            "FROM\n" +
//            "  (SELECT\n" +
//            "    *,\n" +
//            "    CONCAT(\n" +
//            "      DATE_FORMAT(data_time, '%w'),\n" +
//            "      '_',\n" +
//            "      DATE_FORMAT(data_time, '%d'),\n" +
//            "      mac\n" +
//            "    ) AS w_d_m,\n" +
//            "    CONCAT(\n" +
//            "      DATE_FORMAT(data_time, '%w'),\n" +
//            "      '_',\n" +
//            "      DATE_FORMAT(data_time, '%d')\n" +
//            "    ) w_d\n" +
//            "  FROM\n" +
//            dbs +"\n"+
//            " ) a\n" +
//            "\n" +
//            "GROUP BY DATE_FORMAT(a.data_time ,'%w');";
//
//    public static String sqlday_24 = "SELECT\n" +
//            "  DATE_FORMAT(a.data_time, '%H') AS '小时',\n" +
//            "  COUNT(DISTINCT h_d_m) AS '总数'\n" +
//            "FROM\n" +
//            "  (SELECT\n" +
//            "    *,\n" +
//            "    CONCAT(\n" +
//            "      DATE_FORMAT(data_time, '%H'),\n" +
//            "      \"_\",\n" +
//            "      DATE_FORMAT(data_time, '%d'),\n" +
//            "      \"_\",\n" +
//            "      mac\n" +
//            "    ) AS h_d_m\n" +
//            "  FROM\n" +
//            dbs +
//            "\n) a\n" +
//            "GROUP BY DATE_FORMAT(a.data_time, '%H');";
//
//    public static String sqlnull_rates = "SELECT\n" +
//            "  (a.c - a.c % 500) AS '数据频次范围',\n" +
//            "  SUM(a.c) AS '总数',\n" +
//            "  SUM(a.nullNumber) AS 'null数'\n" +
//            "FROM\n" +
//            "  (SELECT\n" +
//            "    mac,\n" +
//            "    COUNT(1) AS c,\n" +
//            "    SUM(\n" +
//            "      (\n" +
//            "        CASE\n" +
//            "          WHEN domain = 'null'\n" +
//            "          THEN 1\n" +
//            "          ELSE 0\n" +
//            "        END\n" +
//            "      )\n" +
//            "    ) AS 'nullNumber'\n" +
//            "  FROM\n" +
//              dbs+"\n" +
//            "  GROUP BY mac) a\n" +
//            "GROUP BY (a.c - a.c % 500)";
//
//    public static String sqlnull_nums = "SELECT\n" +
//            "  a.day AS '日期',\n" +
//            "  (\n" +
//            "    CASE\n" +
//            "      WHEN a.week = '0'\n" +
//            "      THEN 7\n" +
//            "      ELSE a.week\n" +
//            "    END\n" +
//            "  ) AS '星期',\n" +
//            "  a.mac_count AS 'mac总数',\n" +
//            "  a.nullNumber AS 'null总数',\n" +
//            "  a.all_count AS '语句总数',\n" +
//            "  CONCAT(\n" +
//            "    a.nullNumber / a.all_count * 100,\n" +
//            "    '%'\n" +
//            "  ) AS '错误率'\n" +
//            "FROM\n" +
//            "  (SELECT\n" +
//            "    DATE_FORMAT(data_time, '%d') AS 'day',\n" +
//            "    DATE_FORMAT(data_time, '%w') AS 'week',\n" +
//            "    COUNT(DISTINCT mac) AS 'mac_count',\n" +
//            "    SUM(\n" +
//            "      (\n" +
//            "        CASE\n" +
//            "          WHEN domain = 'null'\n" +
//            "          THEN 1\n" +
//            "          ELSE 0\n" +
//            "        END\n" +
//            "      )\n" +
//            "    ) AS 'nullNumber',\n" +
//            "    COUNT(1) AS 'all_count'\n" +
//            "  FROM\n" +
//            dbs +"\n"+
//            "  GROUP BY DATE_FORMAT(data_time, '%d')) a;";
//
//}

import BigDataOperation.DailryUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/***Created by moyongzhuo
 *On 2018/4/10  ***8:50.
 ******/
public class testUtil {
    //这里填写月份，需要配，不重复就好
    public static String month = "10";
    //日志txt文件地址，需要配
    //public static String pathtxt = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\data12\\"+month+".txt";
    public static String pathtxt = "X:\\语义云二期等\\用户数据\\2017_"+ month +".txt";
    //sql连接用到,需要配
    public static String sqltext = "jdbc:mysql://10.9.46.122:3306/video?rewriteBatchedStatements=true&user=root&password=changhong2017&characterEncoding=UTF8";
    //数据库里的表
    public static String tables = "on_linedata_all";//+ month;
    //public static String tables = "on_linedata_"+ month;
    //null数据
    public static String txtpath =  "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\data201802\\";
    //生成xls目录地址，需要改
    public static String excel_result_path = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\data201802\\"+tables+".xls";
    public static String twices = "6";

    //不取的mac地址
    static Set<String> set = new HashSet<>();
    static{
        set.add("wj-r3-45-ef-dg-3d");
        set.add("18:99:f5:5c:f7:74");
        set.add("00:e4:00:00:0e:f9");
        set.add("00:e4:00:00:10:ba");
        set.add("18:99:f5:ff:fe:09");
        set.add("18:99:f5:ff:ca:ae");
        set.add("00:e4:00:33:48:b1");
        set.add("18:99:f5:f5:1b:f4");
        set.add("18:99:f5:ff:b7:30");
        set.add("18:99:f5:ff:69:b5");
        set.add("18:99:f5:ff:7c:c2");
        set.add("18:99:f5:ff:9b:a1");
        set.add("18:99:f5:ff:76:7e");
        set.add("18:99:f5:ff:dd:b6");
        set.add("18:99:f5:f6:e6:22");
    }
    static Map<String, Integer> weeks = new java.util.HashMap<String, Integer>();
    static{
        weeks.put("星期一", 1);
        weeks.put("星期二", 2);
        weeks.put("星期三", 3);
        weeks.put("星期四", 4);
        weeks.put("星期五", 5);
        weeks.put("星期六", 6);
        weeks.put("星期日", 7);
    }



    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis()/1000;
        testUtil testUtil = new testUtil();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = (Connection) DriverManager.getConnection(sqltext);
         Date createAt = new Date();
         Long updateAt = new Date().getTime() / 1000;
         System.out.println(createAt+"\t"+updateAt);

        //导入数据，可取消
        //dailryUsed.importMysql(conn);
        //dailryUsed.importMysqlyear(conn);
//          dailryUsed.importMusicMysql(conn);
//         dailryUsed.mouth_macs(conn);
//        dailryUsed.mouth_days(conn);
//        dailryUsed.days_24(conn);
//          dailryUsed.days_24_week6(conn);
//          dailryUsed.days_24_week0(conn);
//        dailryUsed.week(conn);
//        dailryUsed.rates(conn);
//        dailryUsed.nulls(conn);

        //句式
//         dailryUsed.jushi_mouth(conn);
        //dailryUsed.jushi_mouthdays(conn);
//         dailryUsed.jushi_week(conn);
//         dailryUsed.jushi_hour(conn);
//         dailryUsed.jushi_hour_week(conn);
//         dailryUsed.jushi_hour_noweek(conn);
//         dailryUsed.jushi_jushi_music(conn);
//        dailryUsed.jushi_jushi_video(conn);
//        dailryUsed.jushi_jushi_null(conn);
//        dailryUsed.jushi_jushi_app(conn);
//        dailryUsed.jushi_jushi_tv(conn);

        //电影人物直接语句
        //dailryUsed.jushi_video(conn);
        //dailryUsed.jushi_figure(conn);
        //dailryUsed.jushi_null_times(conn);
        //dailryUsed.sentence_nulls(conn);

        //dailryUsed.fileReader("");
        //dailryUsed.sentence_music(conn);
        //dailryUsed.sentence_music(conn);

        //dailryUsed.sentence_all_null(conn);
        //dailryUsed.sentence_all_fluent(conn);

        //dailryUsed.sentence_all_video(conn);
        //dailryUsed.sentence_all_videonull(conn);

        //testUtil.sentence_all_100(conn);
        long end = System.currentTimeMillis()/1000;
        System.out.print("总共耗时： ");
        System.out.println(end-start);

        //rs.last();//对rs的操作应马上操作,操作完后再从数据库得到rst,再对rst操作

    }
    //一个月mac总数

    public static String sql_video_freq = "SELECT g.a,g.b FROM(\n" +
            "SELECT sentence a,count(sentence) b FROM " +
            tables +
            "\n" +
            "GROUP BY sentence)g\n" +
            "ORDER BY g.b DESC ";

    public static String sql_video_test = "SELECT sentence as 'a',count(sentence) as 'b' FROM " +
            tables +
            "\n";
    public void sentence_all_100(Connection conn) throws Exception{
        try {
            //null
            Statement stmt5 = conn.createStatement();//创建一个Statement对象
            //ResultSet rs5 = stmt5.executeQuery(sql_video_freq);//创建数据对象
            ResultSet rs5 = stmt5.executeQuery(sql_video_test);
            System.out.println("#####################video######################");

            List<String> list = new ArrayList<>();
            int tt = 0;
            int ff = 0;
            int dd =0;
            while (rs5.next()) {
                tt++;
                ff++;
                String sentence = rs5.getString("A");
                list.add(sentence);
                if(ff % 50000 == 0) {
                    dd++;
                    File filenull = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\video\\" + "all_VIDEO_freq" + tables + ".txt");
                    FileUtils.writeLines(filenull, list, true);
                    System.out.println(dd+"/t"+ff);
                    list.clear();
                    ff = 0;
                }

            }
            File filenull = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\video\\" + "all_VIDEO_freq" + tables + ".txt");
            FileUtils.writeLines(filenull, list, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }







    //数据导入
    @Test
    public void importMysqlyear() throws Exception{
        try {
            //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\moyongzhuo\\Desktop\\语义云三期\\twice_2_10_2.txt")));
            BufferedReader br = new BufferedReader(new FileReader(new File("X:\\语义云二期等\\语义云二期02-04\\日志分析\\日志1\\10-2数据统计.txt\n")));
            String str = "";
            int ii = 0;
            int kk = 0;
//            List<String> list = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                ii++;
                String[] ss = str.split("\t");
                if(ss[0].contains(" ")||ss[0].contains("\n")){
                    kk++;
                    System.out.println(ss[0]+"\t"+kk);
                }
//                if(ss[0].equals("不计官")){
//                    break;
//                }
//                list.add(ss[0]);
                kk++;
//                if(kk == 50000){
//                    kk=0;
//                    File filenull = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\freq\\" + "sentences_2" + ".txt");
//                    FileUtils.writeLines(filenull, list, true);
//                    list.clear();
//                }
            }
//            File filenull = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\BigDataOperation\\freq\\" + "sentences_2" + ".txt");
//            FileUtils.writeLines(filenull, list, true);
        }catch (Exception e){

        }
    }









}

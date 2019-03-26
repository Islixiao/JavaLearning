package Webmagic.DongManCrawer.Animation;

import Webmagic.Hahadm.PinyinTool;
import com.csvreader.CsvReader;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import mongoDBConnect.ExcelTest.StringUtils;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***Created by moyongzhuo
 *On 2017/10/26  ***15:03.
 ******/
public class HahaTextRead {

    @Test//20171026第二次使用
    public   void readHahaRoleData() {
        List<String> Haha = null;
        List<String> Role = null;
        List<String> willAddList = null;
        Set<String> willAddNameSet = new HashSet<>();
        try{
            Haha = FileUtils.readLines(new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\DongManCrawer\\Animation\\role16002.txt"));
            //Role = FileUtils.readLines(new File(""));
            //willAddList = FileUtils.readLines(new File("更改1021.txt"));
       /*   FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\1020竹简删除后相同的名字.txt"));
            for (String willAddStr : willAddList) {
                String[] array = willAddStr.split("\t");
                if (!array[0].isEmpty()) {
                    willAddNameSet.add(array[0]);
                }
            }*/
        } catch ( IOException e) {
            e.printStackTrace();
        }
        for (String line11 : Haha){
            if (StringUtils.isNotBlank(line11)) {
                if(line11.contains("count = 0")){
                   // String  linett = line11.substring(20,48);
                    String  clearName11 = line11.replaceAll("matched count = 0  momo|momo(.*)","");
                    System.out.println(clearName11);
                }
                if(line11.contains("clearnName")){
                    String clearName2 = line11.replaceAll("clearnName","");
                   // System.out.println(clearName2);
                }

            }
        }
    }













@Test//20171026第二次使用
  public   void readHahaData() {
        List<String> Haha = null;
        List<String> Role = null;
        List<String> willAddList = null;
        Set<String> willAddNameSet = new HashSet<>();
        try{
            Haha = FileUtils.readLines(new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\DongManCrawer\\Animation\\RoleInsertMongoDb数据_1026.txt"));
            //Role = FileUtils.readLines(new File(""));
            //willAddList = FileUtils.readLines(new File("更改1021.txt"));
       /*   FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\1020竹简删除后相同的名字.txt"));
            for (String willAddStr : willAddList) {
                String[] array = willAddStr.split("\t");
                if (!array[0].isEmpty()) {
                    willAddNameSet.add(array[0]);
                }
            }*/
        } catch ( IOException e) {
            e.printStackTrace();
        }
           for (String line11 : Haha){
           if (StringUtils.isNotBlank(line11)) {
                if(line11.contains("count = 1")){
                    String  linett = line11.substring(20,48);
                    String  clearName11 = linett.replaceAll("omo|momo(.*)","");
                    //System.out.println(clearName11);
                }
                if(line11.contains("clearnName")){
                    String clearName2 = line11.replaceAll("clearnName","");
                    System.out.println(clearName2);
                }

            }
        }
    }


    @Test//所有名字
    public void MongoRoleUpdata() throws Exception {
        long count = 0;
        int ii = 0;
        int aa = 0;
        try {
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            String csvFilePath = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\DongManCrawer\\Animation\\Hahadm_animation_role_1600.csv";
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            while (reader.readRecord()) {
                csvFileList.add(reader.getValues());
            }
            reader.close();
            for (int row = 0; row < 1646; row++) {
                // 取得第row行第0列的数据
                String hahaclearname = csvFileList.get(row)[3];//20年份; 17tag; 15role;  11language;  6导演;  3clearname
                String haharole = csvFileList.get(row)[15];
                String hahayear = csvFileList.get(row)[20];
                String hahatag = csvFileList.get(row)[17];
                System.out.println(hahaclearname);
            }
            System.out.println("count: "  +  count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}

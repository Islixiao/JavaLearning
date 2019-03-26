package Webmagic.AutoHome.sigit_data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***Created by moyongzhuo
 *On 2018/4/27  ***13:28.
 ******/
public class sigit_user {

    @Test//将省份连接上景区字符串
    public void get_province() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\5A景区.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                if(brand_id_fil.length()<="新疆   12".length()){
                    String[] provs = brand_id_fil.split("\t");
                    province = provs[0]+"\t\t"+provs[provs.length-1];
                    continue;
                }
                if(brand_id_fil.length()>"新疆   12".length()){
                    String[] sigits = brand_id_fil.split("20");
                    list_sigit.add(sigits[0].trim()+"\t\t"+ "20"+sigits[1].replace("年","").trim()+"\t\t"+ province);
                }
            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }



    @Test//获取景区里边的子景区或者是合在一起的景区
    public void get_sights() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                String alls = "";
                if(brand_id_fil.contains("(")||brand_id_fil.contains("（")) {
                    String[] sigits = brand_id_fil.split("\t\t");
                    if (sigits.length == 4) {
                        String[] sig = sigits[0].split("[(（]");
                        String othernames = sig[0];
                        String other = sig[1].replace(")", "").replace("）", "").trim();
                        alls = sig[0] + "\t\t" + sigits[1] + "\t\t" + sigits[2] + "\t\t" + sigits[3] + "\t\t" + other;
                    }
                }
                if(!brand_id_fil.contains("(")&&!brand_id_fil.contains("（")){
                    alls = brand_id_fil + "\t\t" + "gg";
                }
                list_sigit.add(alls);

            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_1_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }






    @Test//去除风景名胜区的后缀，山名等
    public void get_sights_other() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_1_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                String alls = "";
                String[] sigits = brand_id_fil.split("\t\t");
                String sig = sigits[0];
                String other1 = sig.replace("特别旅游区","").replace("佛文化景区","").replace("热带植物园","").replace("旅游度假区","").replace("博物馆","").replace("国家商务旅游示范区","").replace("风光带","").replace("广播电视塔","").replace("及周围寺庙景区","").replace("旅游景区","").replace("文化园区","").replace("文化旅游区","").replace("生态文化旅游区","").replace("风景名胜区","").replace("旅游风景区","").replace("旅游区","").replace("风景区","").replace("景区","");

                alls = sigits[0] +"\t\t"+other1+ "\t\t" + sigits[1] + "\t\t" + sigits[2] + "\t\t" + sigits[3] + "\t\t" + sigits[4];

                list_sigit.add(alls);

            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_22_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }









    @Test//获取真正景区的地名
    public void get_realname() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_22_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                String alls = "";
                String[] sigits = brand_id_fil.split("\t\t");
                String sig = sigits[1];
                String other1 = sig.replaceAll(".*市","");
                String other2 = other1.replaceAll(".*旗","");
                String other3 = other2.replaceAll(".*县","");
                String other4 = other3.replaceAll(".*区","");

                alls = sigits[0] +"\t\t"+other4+ "\t\t" + sigits[1] + "\t\t" + sigits[2] + "\t\t" + sigits[3] + "\t\t" + sigits[4]+ "\t\t" + sigits[5];

                list_sigit.add(alls);

            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_33_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test//传上真正的风景
    public void set_git() throws Exception{
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_33_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                String alls = "";
                String[] sigits = brand_id_fil.split("\t\t");
                String[] sig = sigits[0].split(sigits[1]);
                if(sig.length==2) {
                    String sigit_set = sigits[1] + sig[1];
                    alls = sigits[0] + "\t\t" + sigit_set + "\t\t" + sigits[1] + "\t\t" + sigits[2] + "\t\t" + sigits[3] + "\t\t" + sigits[4] + "\t\t" + sigits[5] + "\t\t" + sigits[6];
                }if(sig.length!=2) {
                    alls = sigits[0] + "\t\t" + sigits[1]+"景区" + "\t\t" + sigits[1] + "\t\t" + sigits[2] + "\t\t" + sigits[3] + "\t\t" + sigits[4] + "\t\t" + sigits[5] + "\t\t" + sigits[6];
                }

                list_sigit.add(alls);

            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_55_set_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }



















    @Test//获取最终的数据
    public void get_all() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_33_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                Collection<String> coll = new ArrayList<>();

                String alls = "";
                String[] sigits = brand_id_fil.split("\t\t");
                if(!sigits[6].contains("gg")){
                    String[] all = sigits[6].split("-");
                    for(int i=0; i<all.length;i++){
                        coll.add(all[i].trim());
                    }
                }
                coll.add(sigits[0]);
                coll.add(sigits[1]);
                coll.add(sigits[2]);

                alls = sigits[0] +"\t\t"+ sigits[1] +"\t\t"+ coll + "\t\t"  + sigits[3] + "\t\t" + sigits[4]+ "\t\t" + sigits[5];

                list_sigit.add(alls);

            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_44_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }















    @Test//获取最终的数据
    public void get_all_2() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_55_set_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                Collection<String> coll = new ArrayList<>();

                String alls = "";
                String[] sigits = brand_id_fil.split("\t\t");
                if(!sigits[7].contains("gg")){
                    String[] all = sigits[7].split("-");
                    for(int i=0; i<all.length;i++){
                        coll.add(all[i].trim());
                    }
                }
                coll.add(sigits[0]);
                coll.add(sigits[1]);
                coll.add(sigits[2]);
                coll.add(sigits[3]);

                alls = sigits[0] +"\t\t"+ sigits[2] +"\t\t"+ coll + "\t\t"  + sigits[4] + "\t\t" + sigits[5]+ "\t\t" + sigits[6];

                list_sigit.add(alls);

            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_66_set_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }











    @Test//对比自研、最短数据
    public void get_compare() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_66_set_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {

                String alls = "";
                String[] sigits = brand_id_fil.split("\t\t");
                list_sigit.add(sigits[2]);
            }

            String alldata = list_sigit.toString();
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\景区名字.txt");
            List<String> sigit_lzx = FileUtils.readLines(file1);
            for(String lzx: sigit_lzx) {
                String alls = lzx.trim();
                if(!alldata.contains(alls)){
                    jj++;
                    System.out.println(jj+"\t\t"+alls);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }


    }






    @Test//对比自研、最短数据
    public void get_gg() throws Exception{

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_66_set_省市_5A.txt");
            List<String> brand_id = FileUtils.readLines(file);
            List<String> list_sigit = new ArrayList<>();
            int jj = 0;
            String province = "";
            for(String brand_id_fil: brand_id) {
                String alls = "";
                alls = brand_id_fil.trim()+"\t\t"+ "gg";
                list_sigit.add(alls);
            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_data\\clean_77_set_省市_5A.txt");
            FileUtils.writeLines(file1, list_sigit, true);



        }catch(Exception e){
            e.printStackTrace();
        }


    }
//    "旗市区县"

}

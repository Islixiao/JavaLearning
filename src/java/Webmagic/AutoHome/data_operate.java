package Webmagic.AutoHome;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/***Created by moyongzhuo
 *On 2018/4/23  ***15:31.
 ******/
public class data_operate {

    @Test  //获取car_http_have
    public void data() {
        try {
            File file = new File("car_brand_0423.txt");
            List<String> llist = FileUtils.readLines(file);

            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                if(http.contains("www.autohome.com.cn")) {
                    String https = http.replaceAll(".*www|#levelsource(.*)", "");
                        if (!list_have.contains(https)) {
                            list_have.add(https);
                        }
                }
            }

            File file2 = new File("car_http_have.txt");
            FileUtils.writeLines(file2, list_have, true);

        } catch (Exception e) {
        }
    }


    //获取不重复的model
    @Test
    public void getmodel() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_brand_0423.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                for(String brand_model: ll2){
                    if(brand_model.contains(http)){
                        String[] mm = brand_model.split("mo_mo");
                        String model_11 = mm[0].replaceAll("a00", "微型车").replaceAll("a0", "小型车").replaceAll("a", "紧凑型车").replaceAll("b", "中型车").replaceAll("c", "中大型车").replaceAll("d", "大型车").replaceAll("suv", "SUV").replaceAll("mpv", "MPV").replaceAll("s", "跑车").replaceAll("p", "皮卡").replaceAll("mb", "微面").replaceAll("qk", "轻客");
                        String model_1 = model_11 + brand_model.replace(mm[0],"");
                        list_have.add(model_1);
                        break;
                    }

                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_model.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //获取进口，出口
    @Test
    public void getboard() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_borad.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;
                for (String brand_model : ll2) {
                    if (brand_model.contains(http)) {
                        String brand_model_2 = brand_model.replaceAll("car0_0-0.0_0.0-0-0-0-1-0-0-0-0", "国产").replaceAll("car0_0-0.0_0.0-0-0-0-3-0-0-0-0", "进口");
                        list_have.add(brand_model_2);
                        bbc++;
                        gggg++;
                        break;
                    }
                }
                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_board.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //5,7,2,4,6,8座位
    @Test
    public void getsit() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_sit.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;
                for (String brand_model : ll2) {
                    if (brand_model.contains(http)) {
                        String brand_model_2 = brand_model.replaceAll("car0_0-0.0_0.0-0-0-0-0-0-0-5-0", "5").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-0-7-0", "7").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-0-2-0", "2").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-0-4-0", "4").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-0-6-0", "6").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-0-8-0", "8");
                        list_have.add(brand_model_2);
                        bbc++;
                        gggg++;
                        break;
                    }
                }
                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_sit.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //结构，两相
    @Test
    public void getstructure() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_structure.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;
                for (String brand_model : ll2) {
                    if (brand_model.contains(http)) {
                        String brand_model_2 = brand_model.replaceAll("car0_0-0.0_0.0-0-0-1-0-0-0-0-0", "两厢").replaceAll("car0_0-0.0_0.0-0-0-2-0-0-0-0-0", "三厢").replaceAll("car0_0-0.0_0.0-0-0-3-0-0-0-0-0", "掀背").replaceAll("car0_0-0.0_0.0-0-0-4-0-0-0-0-0", "旅行版").replaceAll("car0_0-0.0_0.0-0-0-5-0-0-0-0-0", "硬顶敞篷车").replaceAll("car0_0-0.0_0.0-0-0-6-0-0-0-0-0", "软顶敞篷车").replaceAll("car0_0-0.0_0.0-0-0-7-0-0-0-0-0", "硬顶跑车").replaceAll("car0_0-0.0_0.0-0-0-8-0-0-0-0-0", "客车").replaceAll("car0_0-0.0_0.0-0-0-9-0-0-0-0-0", "货车");
                        list_have.add(brand_model_2);
                        bbc++;
                        gggg++;
                        break;
                    }
                }
                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_structure.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //能源： 汽油
    @Test
    public void get_engine() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_engine.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;
                for (String brand_model : ll2) {
                    if (brand_model.contains(http)) {
                        String brand_model_2 = brand_model.replaceAll("car0_0-0.0_0.0-0-0-0-0-1-0-0-0", "汽油").replaceAll("car0_0-0.0_0.0-0-0-0-0-2-0-0-0", "柴油").replaceAll("car0_0-0.0_0.0-0-0-0-0-3-0-0-0", "油电混合").replaceAll("car0_0-0.0_0.0-0-0-0-0-4-0-0-0", "纯电动").replaceAll("car0_0-0.0_0.0-0-0-0-0-5-0-0-0", "插电式混动");
                        list_have.add(brand_model_2);
                        bbc++;
                        gggg++;
                        break;
                    }
                }
                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_engine.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //V , L
    @Test
    public void get_oil() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_oil.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;
                for (String brand_model : ll2) {
                    if (brand_model.contains(http)) {
                        String brand_model_2 = brand_model.replaceAll("car0_0-0.0_1.0-0-0-0-0-0-0-0-0", "1").replaceAll("car0_0-1.1_1.6-0-0-0-0-0-0-0-0", "1.1-1.6").replaceAll("car0_0-1.7_2.0-0-0-0-0-0-0-0-0", "1.7-2").replaceAll("car0_0-2.1_2.5-0-0-0-0-4-0-0-0", "2.1-2.5").replaceAll("car0_0-2.6_3.0-0-0-0-0-5-0-0-0", "2.6-3").replaceAll("car0_0-3.1_4.0-0-0-0-0-4-0-0-0", "3.1-4").replaceAll("car0_0-4.0_0.0-0-0-0-0-0-0-0-0", "4");
                        list_have.add(brand_model_2);
                        bbc++;
                        gggg++;
                        break;
                    }
                }
                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_oil.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //国家
    @Test
    public void get_country() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_country.txt");
            List<String> ll2 = FileUtils.readLines(file2);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;
                for (String brand_model : ll2) {
                    if (brand_model.contains(http)) {
                        String brand_model_2 = brand_model.replaceAll("car0_0-0.0_0.0-0-0-0-0-0-1-0-0", "中国").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-2-0-0", "德国").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-3-0-0", "日本").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-4-0-0", "美国").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-5-0-0", "韩国").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-6-0-0", "法国").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-7-0-0", "英国").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-8-0-0", "意大利").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-9-0-0", "瑞典").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-10-0-0", "荷兰").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-11-0-0", "捷克").replaceAll("car0_0-0.0_0.0-0-0-0-0-0-12-0-0", "西班牙");
                        list_have.add(brand_model_2);
                        bbc++;
                        gggg++;
                        break;
                    }
                }
                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\brand_country.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }

    //所有整合
    @Test
    public void get_all() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_http_have.txt");
            List<String> llist = FileUtils.readLines(file);
            File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\brand_structure.txt");
            List<String> list_have = new ArrayList<>();

            List<String> struct = FileUtils.readLines(file2);
            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\brand_sit.txt");
            List<String> sit = FileUtils.readLines(file3);
            File file4 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\brand_borad.txt");
            List<String> borad = FileUtils.readLines(file4);
            File file5 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\brand_model.txt");
            List<String> model = FileUtils.readLines(file5);
            File file6 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\brand_engine.txt");
            List<String> engine = FileUtils.readLines(file6);
            int gggg = 0;
            for (String ll : llist) {
                String http = ll;
                int bbc = 0;

//
//                for (String brand_model : model) {
//                    if (brand_model.contains(http)) {
//                        String brand_model_2 = brand_model.split("","");
//
//                        break;
//                    }
//                }
//                for (String brand_model : struct) {
//                    if (brand_model.contains(http)) {
//                        String brand_model_2 = brand_model;
//                        bbc++;
//                        gggg++;
//                        break;
//                    }
//                }






                if (bbc == 0) {
                    System.out.println(http);
                }
            }

            File file_all = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_brand_all.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }


    @Test     //上传
    public void test_no_url() throws Exception {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("MoMo_Animation");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> collection = mongoDatabase.getCollection("AutoHome_car");
        System.out.println("集合 test 选择成功");
        try {

            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_fail.txt");
            List<String> llist = FileUtils.readLines(file);
            for (String ll : llist) {
                String http = ll;
                String https = http.replaceAll(".*www|#levelsource(.*)", "");

                Document filter = new Document();
                Pattern pattern = Pattern.compile("^.*"+https+".*$", Pattern.CASE_INSENSITIVE);
                BasicDBObject cond = new BasicDBObject();
                cond.put("car_url",pattern);

                //注意update文档里要包含"$set"字段
                MongoCursor<Document> mongoCursor11 = collection.find(cond).iterator();
                int gg_num = 0;
                File file2 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_get.txt");
                while (mongoCursor11.hasNext()) {
                    gg_num++;
                    Document item = mongoCursor11.next();
                    FileUtils.writeStringToFile(file2, ll + "\n", true);
                }
                if(gg_num >=2 ){}
                FileUtils.writeStringToFile(file2,  "\n", true);

            }

        } catch (Exception e) {
        }
    }




    //处理品牌和车型
    @Test
    public void get_brand_model() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\autohome_car_0425.txt");
            List<String> llist = FileUtils.readLines(file);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String car = ll;
                String[] ccr = car.split("\t\t");
                if(ccr.length==8) {
                    String brand = ccr[0];
                    String model = ccr[1];
                    String regEx ="[^\\u4e00-\\u9fa5a-zA-Z0-9,]";

                    String brand_new_1 = brand.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5,]+","");
                    String model_new_1 = model.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5,]+","");
                    list_have.add(brand_new_1 + "\t\t" + model_new_1);

                }
                if(ccr.length!=8) {
                    gggg++;
                    System.out.println(ll+ "\t\t" +gggg);
                }

            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\autohome_car_brand_model_0425.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }






    //处理品牌和车型
    @Test
    public void get_brand_model_26() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\autohome_car_0424.txt");
            List<String> llist = FileUtils.readLines(file);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String car = ll;
                String[] ccr = car.split("\t\t");
                if(ccr.length==7) {
                    String brand = ccr[0];
                    String model = ccr[1];
                    String model_1 = "";
                    if(model.contains(brand)){
                        if(model.length() == brand.length()){System.out.println(model);}
                        model_1 = model.replace(brand,"");
                    }else {
                        model_1 = model;
                    }
                    String regEx ="[^\\u4e00-\\u9fa5a-zA-Z0-9,]";

                    String brand_new_1 = brand.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]+","");
                    String model_new_1 = model_1.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]+","");
                    list_have.add(brand_new_1 + "\t\t" + model_new_1);

                }
                if(ccr.length!=7) {
                    gggg++;
                    System.out.println(ll+ "\t\t" +gggg);
                }

            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\clean_brand_model_0426.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }




    //处理品牌和车型,清理数据
    @Test
    public void get_brand_model_27() {
        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\car_22_brand_series_20180426.txt");
            List<String> llist = FileUtils.readLines(file);
            List<String> list_have = new ArrayList<>();
            int gggg = 0;
            for (String ll : llist) {
                String car = ll;
                String[] ccr = car.split("\t\t");
                if(ccr.length==7) {
                    String brand = ccr[0];
                    String model = ccr[1];
                    String model_1 = "";
                    if(model.contains(brand)){
                        if(model.length() == brand.length()){System.out.println(model);}
                        model_1 = model.replace(brand,"");
                    }else {
                        model_1 = model;
                    }
                    String regEx ="[^\\u4e00-\\u9fa5a-zA-Z0-9,]";

                    String brand_new_1 = brand.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]+","");
                    String model_new_1 = model_1.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]+","");
                    list_have.add(brand_new_1 + "\t\t" + model_new_1);

                }
                if(ccr.length!=7) {
                    gggg++;
                    System.out.println(ll+ "\t\t" +gggg);
                }

            }

            File file3 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\operater_data\\clean_brand_model_0426.txt");
            FileUtils.writeLines(file3, list_have, true);

        } catch (Exception e) {
        }
    }


}

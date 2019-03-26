package Webmagic.AutoHome;
import com.arangodb.entity.BaseDocument;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/23  ***18:45.
 ******/
public class DaAutoHomeProcessor implements PageProcessor{
    private static int myid = 0;
    int size = 0;
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(10000).setCharset("gbk")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

    @Override
    public Site getSite() {
        return site;
    }
    @Override
    public void process(Page page) {
        DaAutoHome daAnimation = new DaAutoHome();
        Html html = page.getHtml();
//        File file = new File("");
//        FileUtils.w
        myid++;
        size++;

        int hhid = myid;//class="intro_l"

        // String damanwebname = html.xpath("//meta[@property=\"og:title\"]/@content").get();//title
//        String content = html.xpath("//meta[@property=\"og:description\"]/@content").get();//title
//        String imgurl = html.xpath("//meta[@property=\"og:image\"]/@content").get();//imgurl
//        String url = html.xpath("//meta[@property=\"og:url\"]/@content").get();//imgurl
//        String vediourl = html.xpath("//meta[@property=\"og:video\"]/@content").get();//imgurl
//        String mainrole11 = html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//imgurl
//        String mainrole = mainrole11.replaceAll(",", " / ");
//        String area = html.xpath("//meta[@name=\"og:video:area\"]/@content").get();//imgurl
//        String tag11 = html.xpath("//meta[@name=\"og:video:class\"]/@content").get();//imgurl
//        String tag1111 = tag11.replaceAll(" ", " / ");
//        String tag = tag1111.replaceAll(",", " / ");
//        String language = html.xpath("//meta[@property=\"og:video:language\"]/@content").get();//imgurl

        try {
            String https = page.getUrl().get();
            String car_model_title = html.xpath("//div[@class='subnav']/div[@class='subnav-title']/div[@class='subnav-title-name']/a/text()").get();
            String title_old = car_model_title.replace("<h1>", "").replace("</h1>", "").trim();

            String title = title_old.substring(0, title_old.length() - 1);
            String car_model_title_son = html.xpath("//div[@class='subnav-title']/div[@class='subnav-title-name']/a/h1/text()").get();
            //中型suv
            List<Selectable> nodes1 = html.xpath("//div[@class='path fn-clear']/div[@class='breadnav fn-left']/a").nodes();
            String categorys = "";
            int categorys_nums = 0;
            for (Selectable item : nodes1) {
                if(categorys_nums == 1) {
                    Selectable item_2 = item;
                    categorys = item_2.xpath("//a/text()").get();
                }
                categorys_nums++;
            }

            List<Selectable> nodes2 = html.xpath("//div[@class='autoseries-info']/dl/dt").nodes();
            String price_grey = "";
            String area_mallprice = "";
            String series_price = "";
            for (Selectable item2 : nodes2) {
                Selectable item_3 = item2;
                if(item_3.toString().contains("新车指导价：")){
                    price_grey=item_3.toString().replaceAll("</?[^>]+>","").replaceAll("\n","").replaceAll("新车指导价：","").trim().replace("  ("," / ").replace(")","");
                }
                if(item_3.toString().contains("车商城报价：")){
                    area_mallprice=item_3.toString().replaceAll("</?[^>]+>","").replaceAll("\n","").trim();
                }
                if(item_3.toString().contains("二手车价格：")){
                    series_price=item_3.toString().replaceAll("</?[^>]+>","").replaceAll("\n","").trim();
                }
            }

            List<Selectable> nodes3 = html.xpath("//div[@class='autoseries-info']/dl/dd").nodes();
            String car_color = "";
            String car_engine = "";
            String car_gear_box = "";
            String car_endurance = "";
            String car_electricize = "";
            for (Selectable item3 : nodes3) {
                Selectable item_3 = item3;
                if(item_3.toString().contains("车身颜色：")){
                    car_color=item_3.toString().replaceAll("</?[^>]+>","").trim().replaceAll("\n","a").replaceAll("aaaaa"," ").replaceAll("aaaaaaa"," ").replaceAll(" ","").trim();
                }
                if(item_3.toString().contains("发动机：")){
                    car_engine=item_3.toString().replaceAll("</?[^>]+>","").replaceAll("\n","").trim();
                }
                if(item_3.toString().contains("变速箱：")){
                    car_gear_box = item_3.toString().replaceAll("</?[^>]+>","").replaceAll("&nbsp;&nbsp;","").replaceAll("\n","").trim();
                }
                if(item_3.toString().contains("续航里程：")){
                    car_endurance = item_3.toString().replaceAll("</?[^>]+>","").replaceAll("&nbsp;&nbsp;","").replaceAll("\n","").trim();
                }
                if(item_3.toString().contains("充电时长：")){
                    car_electricize = item_3.toString().replaceAll("</?[^>]+>","").replaceAll("&nbsp;&nbsp;","").replaceAll("\n","").trim().replace("  电动机"," / 电动机");
                }

            }
            System.out.println(car_gear_box);
            List<Selectable> nodes4 = html.xpath("//div[@class='koubei-score']/div").nodes();
            String car_score = "";
            String car_break = "";
            for (Selectable item4 : nodes4) {
                Selectable item_3 = item4;
                if(item_3.toString().contains("用户评分：")){
                    String car_score_old=item_3.toString().replaceAll("</?[^>]+>","").replaceAll("\n","").replaceAll(" ","").trim();
                    String[] score = car_score_old.split("分");
                    if(score.length == 3) {
                        car_score = score[0] + "分" + score[1] + "分" + " / " + score[2];
                    }
                }
                if(item_3.toString().contains("新车百车故障数：")){
                    car_break=item_3.toString().replaceAll("</?[^>]+>","").replaceAll("\n","").replaceAll(" ","").trim().replace("级别均值"," / 级别均值");
                }
            }

            //大车系
            List<Selectable> nodes5 = html.xpath("//div[@class='interval01-list-cars']/span").nodes();
            List<String> car_interval = new ArrayList<>();
            for (Selectable item5 : nodes5) {
                Selectable item_3 = item5;
                     String car_interval_one=item_3.toString().replaceAll("</?[^>]+>","");
                     car_interval.add(car_interval_one.replaceAll(" "," / "));
            }
            //小车系
            List<Selectable> nodes6 = html.xpath("//ul[@class='interval01-list']").nodes();
            List<String> car_interval_small = new ArrayList<>();
            for (Selectable item6 : nodes6) {
                Selectable item_3 = item6;
                List<Selectable> nodes61 = item_3.xpath("//li").nodes();
                String car_small_s ="";
                for (Selectable item4 : nodes61) {
                    Selectable item_41 = item4;
                    String car_small_one = item_41.toString().replaceAll("</?[^>]+>","").trim().replaceAll("\n"," ").replaceAll("                                 口碑     图片     视频     配置             对比","");
                    car_interval_small.add(car_small_one.trim().replace("                                       "," / ").replace("            "," / ").replace("9挡"," / 9挡").replace("6挡"," / 6挡").replace("7挡"," / 7挡").replace("8挡"," / 8挡").replace("5挡"," / 5挡").replace("4挡"," / 4挡").replace("3挡"," / 3挡").replace("       免税"," / 免税").replace("电动车"," / 电动车").replace("      电动"," / 电动 / "));

                }
                car_interval_small.add("\n");

            }
            //单项排行
            List<Selectable> nodes7 = html.xpath("//div[@class='koubei-con-rank']/table/tbody/tr").nodes();
            List<String> car_one_ranks = new ArrayList<>();
            for (Selectable item7 : nodes7) {
                Selectable item_3 = item7;
                String car_one_rank = item_3.toString().replaceAll("</?[^>]+>","").trim().replaceAll("\n","").replaceAll(" ","");
                car_one_ranks.add(car_one_rank.replaceAll("第"," / 第").replace("空间","空间: ").replace("动力","动力: ").replace("操控","操控: ").replace("油耗","油耗: ").replace("舒适性","舒适性: ").replace("外观","外观: ").replace("内饰","内饰: ").replace("性价比","性价比: "));
            }
            //不同车的排行
            List<Selectable> nodes8 = html.xpath("//div[@class='koubei-con-rival']/table/tbody/tr").nodes();
            List<String> car_other_ranks = new ArrayList<>();
            for (Selectable item7 : nodes8) {
                Selectable item_3 = item7;
                if(!item_3.toString().contains("口碑排行用户评分")) {
                    String car_one_rank = item_3.toString().replaceAll("</?[^>]+>", "").trim().replaceAll("\n", "").replaceAll(" ", "");
                    car_other_ranks.add(car_one_rank.replace("(口碑)", "(口碑): "));
                }

            }
            car_other_ranks.remove("口碑排行用户评分");
            List<Selectable> nodes9 = html.xpath("//ul[@class='cxbox-con-list']/li").nodes();
            List<String> car_url = new ArrayList<>();
            car_url.add("车型：  "+ https);
            for (Selectable item7 : nodes9) {
                Selectable item_3 = item7;
                if(item_3.toString().contains("油耗/能耗")||item_3.toString().contains("动力")){
                    List<Selectable> nodes61 = item_3.xpath("//a").nodes();
                    String car_small_s ="";
                    for (Selectable item4 : nodes61) {
                        Selectable item_41 = item4;
                        if(item_41.toString().contains("油耗/能耗")||item_41.toString().contains("动力")) {
                            String dd_url = item_41.xpath("//a/@href").get();
                            String car_dd = item_41.toString().replaceAll("</?[^>]+>", "").trim().replaceAll("\n", " ").replaceAll("  ", " ");
                            car_url.add(car_dd+":"+ "  http:"+dd_url);
                        }
                    }
                }
            }

            List<Selectable> nodes10 = html.xpath("//div[@class='uibox-con carpic']/ul").nodes();
            List<String> car_color_inside = new ArrayList<>();
            int color_nums = 0;
            for (Selectable item4 : nodes10) {
                Selectable item_3 = item4;
                if(item_3.toString().contains("色")){
                        List<Selectable> nodes_color = item_3.xpath("//li/a/@title").nodes();
                        String car_color_in = "";
                        for(Selectable color : nodes_color) {
                            car_color_in =  color.toString().trim();
                            car_color_inside.add(car_color_in);
                        }
                    color_nums++;
                }
            }
            String car_color_new = car_color.replaceAll("aaaaaaaa"," / ").replaceAll("aaaaaaa"," / ").replaceAll("车身颜色：","").replaceAll("更多颜色","");
            String color_s = car_color_new.replaceAll("aaaaa", "").trim();


            String dd="";
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("MoMo_Animation");
            System.out.println("Connect to database successfully");
            MongoCollection<Document> collection = mongoDatabase.getCollection("AutoHome_car_0423");
            System.out.println("集合 test 选择成功");
            Document document = new Document("myid", myid).
                    append("car_company", title).
                    append("name", car_model_title_son).
                    append("categorys", categorys).
                    append("price_grey", price_grey).
                    append("car_color_out", color_s.replace("a","")).
                    append("car_color_in",car_color_inside).
                    append("car_engine", car_engine.replace("发动机：","").trim().replace("  "," / ")).
                    append("car_gear_box", car_gear_box.replace("  车身结构："," / 车身结构：")).
                    append("car_endurance",car_endurance).
                    append("car_electricize",car_electricize).
                    append("car_score", car_score).
                    append("car_break", car_break).
                    append("car_interval", car_interval).
                    append("car_interval_small", car_interval_small).
                    append("car_one_ranks", car_one_ranks).
                    append("car_other_ranks", car_other_ranks).
                    append("car_url", car_url)
                    ;
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            mongoClient.close();

        }catch (Exception e){}





//
//        try {
//            int op = 0;
//            List<String> one_car = new ArrayList<>();
//            List<Selectable> nodes = html.xpath("//div[@class='uibox-con rank-list rank-list-pic']/dl").nodes();
//            for (Selectable item : nodes) {
//                Selectable item_2 = item;
//                List<Selectable> cars = item_2.xpath("//dd/ul/li").nodes();
//                String Car_brand = item_2.xpath("//dt/div/a/text()").get();
//                //List<String> one_car = new ArrayList<>();
//                for (Selectable model : cars) {
//                    String Car_https = model.xpath("//h4/a/@href").get();
//                    String Car_model = model.xpath("//h4/a/text()").get();
//                    String cart = "轻客"+"mo_mo"+Car_brand + "mo_mo" + Car_model + "mo_mo" + Car_https;
//                    one_car.add(cart);
//                    op++;
//                    System.out.println(op+"mo_mo"+cart);
//                }
////            ll.add(one_car);
//            }
//            File file = new File("D:\\workspace\\java\\WebHTTPTest\\src\\main\\java\\Webmagic\\AutoHome\\car_https.txt");
//            FileUtils.writeLines(file, one_car, true);
//        }catch (Exception e){}

    }

   public static void main(String[] args) throws IOException,InterruptedException{
        try {
//        public void test1()throws IOException {
            int username = 25000;
            long startTime, endTime;
//        for (; username >= 1000; username--) {
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();
            //"https://www.autohome.com.cn/2951/?pvareaid=105126"
            //a00/a0/a/b/c/d/suv/mpv/s/p"https://www.autohome.com.cn/3948/#levelsource=000000000_0&pvareaid=101594"

//            String pathtxt = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_https.txt";
            String pathtxt = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_fail.txt";
            BufferedReader br = new BufferedReader(new FileReader(new File(pathtxt)));
            List<String> list = new ArrayList<>();
            String str = "";
            int calcul = 0;
            while ((str = br.readLine()) != null) {
                String[] car_model = str.split("mo_mo");
                if (car_model[3].length() > 10) {
                    String urls = "http:" + car_model[3];
                    Spider.create(new DaAutoHomeProcessor()).addUrl(urls).thread(5).run();
                    endTime = System.currentTimeMillis();
                    System.out.println("第几个： " + calcul + "  " + "爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
                }
                Thread.sleep(1000);

                // 连接到 mongodb 服务
                MongoClient mongoClient = new MongoClient("localhost", 27017);
                // 连接到数据库
                MongoDatabase mongoDatabase = mongoClient.getDatabase("MoMo_Animation");
                System.out.println("Connect to database successfully");
                MongoCollection<Document> collection = mongoDatabase.getCollection("AutoHome_car");
                Document filter = new Document();
                filter.append("name", car_model[2]);
                //注意update文档里要包含"$set"字段
                Document update = new Document();
                update.append("$set", new Document("car_type", car_model[0]));
                UpdateResult result = collection.updateOne(filter, update);
                Document update1 = new Document();
                update1.append("$set", new Document("car_brabd", car_model[1]));
                UpdateResult result1 = collection.updateOne(filter, update1);
                if(result.getMatchedCount()==0){
                    File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_fail_0423.txt");
                    FileUtils.writeStringToFile(file,str+"\n",true );
                }

                calcul++;
            }
        }catch (Exception e){}


//        }

//
//        File file = new File("");
//        FileUtils.readLines(file);
    }



    @Test
    private void fileReadernull() throws IOException {
        String pathtxt = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_https.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(pathtxt)));
        List<String> list = new ArrayList<>();
        String str = "";
        int calcul = 0;
        while((str = br.readLine())!=null){
            String[] car_model = str.split("mo_mo");
            if(car_model[3].length() > 10) {
                String url = "https:" + car_model[3];
                Spider.create(new DaAutoHomeProcessor()).addUrl(url).thread(5).run();
            }
        }

    }




}
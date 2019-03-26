package Webmagic.AutoHome;


import org.apache.commons.io.FileUtils;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/***Created by mo
 *On 2018/4/23  ***10:04.
 ******/
public class CarAutoHome implements PageProcessor {
    private static int myid = 0;
    int size = 0;
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(100000).setCharset("gbk")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    Calendar now = Calendar.getInstance();
    String time = new SimpleDateFormat("HHmm").format(now.getTime());

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        DaAutoHome daAnimation = new DaAutoHome();
        Html html = page.getHtml();
        myid++;
        size++;

        try {
            String url = page.getUrl().get();
            String label = url.replaceAll("https://www.autohome.com.cn/","").replace("/","");
            int op = 0;
            List<String> one_car = new ArrayList<>();
            List<Selectable> nodes = html.xpath("//div[@class='uibox-con rank-list rank-list-pic']/dl").nodes();
            for (Selectable item : nodes) {
                Selectable item_2 = item;
                List<Selectable> cars = item_2.xpath("//dd/ul/li").nodes();
                String Car_brand = item_2.xpath("//dt/div/a/text()").get();
                //List<String> one_car = new ArrayList<>();
                for (Selectable model : cars) {
                    String Car_https = model.xpath("//h4/a/@href").get();
                    String Car_model = model.xpath("//h4/a/text()").get();
                    String cart = label + "mo_mo" + Car_brand + "mo_mo" + Car_model + "mo_mo" + Car_https;
                    one_car.add(cart);
                    op++;
                    System.out.println(op + "mo_mo" + cart);
                }
            }

//            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_brand_0423.txt");//汽车品牌

//            File file = new File("D:\\data\\AutoHome"+ time+".txt");//进口，出口
           File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\origit_data\\car_country.txt");//进口，出口
            FileUtils.writeLines(file, one_car, true);
        } catch (Exception e) {
        }

    }

//    public static void main(String[] args) throws IOException, InterruptedException {
    @Test//获取品牌，车系等
    public void brand_model(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();
            //"https://www.autohome.com.cn/2951/?pvareaid=105126"
            //a00/a0/a/b/c/d/suv/mpv/s/p"https://www.autohome.com.cn/3948/#levelsource=000000000_0&pvareaid=101594"

            List<String> list = new ArrayList<>();
            list.add("a00");
            list.add("a0");
            list.add("a");
            list.add("b");
            list.add("c");
            list.add("d");
            list.add("suv");
            list.add("mpv");
            list.add("s");
            list.add("p");
            list.add("mb");
            list.add("qk");
            for(String list_car :list){
                String https = "https://www.autohome.com.cn/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }

        } catch (Exception e) { }
    }


    @Test//获取进口或者是出口数据
    public void car_board(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_0.0-0-0-0-1-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-3-0-0-0-0");

            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }

        } catch (Exception e) { }
    }


    @Test//获取座位号等
    public void brand_sit(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_0.0-0-0-0-0-0-0-5-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-0-7-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-0-4-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-0-2-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-0-6-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-0-8-0");

            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }



    @Test//结构：两箱，3相
    public void brand_structure(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_0.0-0-0-1-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-2-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-3-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-4-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-5-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-6-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-7-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-8-0-0-0-0-0");
            list.add("0_0-0.0_0.0-0-0-9-0-0-0-0-0");
            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }



    @Test//能源：汽油
    public void brand_engine(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_0.0-0-0-0-0-1-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-2-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-3-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-4-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-5-0-0-0");

            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }


    @Test//能源：汽油
    public void brand_gas(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_0.0-0-0-0-0-1-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-2-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-3-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-4-0-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-5-0-0-0");

            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }


    @Test//汽油量,V,L
    public void brand_oil(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_1.0-0-0-0-0-0-0-0-0");
            list.add("0_0-1.1_1.6-0-0-0-0-0-0-0-0");
            list.add("0_0-1.7_2.0-0-0-0-0-0-0-0-0");
            list.add("0_0-2.1_2.5-0-0-0-0-4-0-0-0");
            list.add("0_0-2.6_3.0-0-0-0-0-5-0-0-0");
            list.add("0_0-3.1_4.0-0-0-0-0-4-0-0-0");
            list.add("0_0-4.0_0.0-0-0-0-0-0-0-0-0");
            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }


    @Test//价格等
    public void brand_price(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("1_5-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("5_10-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("10_15-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("15_20-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("20_25-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("25_30-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("30_35-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("35_50-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("50_70-0.0_0.0-0-0-0-0-0-0-0-0");
            list.add("70_100-0.0_0.0-0-0-0-0-0-0-0-0");

            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }


    @Test//国家等
    public void brand_country(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();

            List<String> list = new ArrayList<>();
            list.add("0_0-0.0_0.0-0-0-0-0-0-1-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-2-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-3-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-4-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-5-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-6-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-7-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-8-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-9-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-10-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-11-0-0");
            list.add("0_0-0.0_0.0-0-0-0-0-0-12-0-0");

            for(String list_car :list){
                String https = "https://www.autohome.com.cn/car/"+ list_car +"/";
                Spider.create(new CarAutoHome()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println(list_car + "\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }
        } catch (Exception e) { }
    }



}
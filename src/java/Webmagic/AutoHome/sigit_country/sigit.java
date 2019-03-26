package Webmagic.AutoHome.sigit_country;

/***Created by moyongzhuo
 *On 2018/4/28  ***10:26.
 ******/


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
public class sigit implements PageProcessor {
    private static int myid = 0;
    int size = 0;
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(100000).setCharset("utf-8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    Calendar now = Calendar.getInstance();
    String time = new SimpleDateFormat("HHmm").format(now.getTime());

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        myid++;
        size++;

        try {
            String url = page.getUrl().get();
            int op = 0;
            List<String> one_car = new ArrayList<>();
            List<Selectable> nodes = html.xpath("//div[@class='main1_right_m']").nodes();
            for (Selectable item : nodes) {
                Selectable item_2 = item;
                String name = item_2.xpath("//div[@class='main1_right_m1']/a/text()").get();
                String province = item_2.xpath("//div[@class='main1_right_m2']/text()").get();
                String year = item_2.xpath("//div[@class='main1_right_m3']/text()").get();
                String all = name+"\t\t"+province+"\t\t"+year;
                one_car.add(all);
            }

//            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\car_brand_0423.txt");//汽车品牌

//            File file = new File("D:\\data\\AutoHome"+ time+".txt");//进口，出口
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\sigit_country\\sigits_country.txt");//进口，出口
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
            for(int i = 1; i<18; i++){
                String https = "http://www.cnta.gov.cn/was5/web/search?page="+String.valueOf(i)+"&channelid=242887&orderby=-AYEAR&perpage=15&outlinepage=5&searchscope=&timescope=&timescopecolumn=&orderby=-AYEAR&andsen=&total=&orsen=&exclude=&searchword2=&AADDRESS=&AYEAR=";
                Spider.create(new sigit()).addUrl(https).thread(5).run();
                endTime = System.currentTimeMillis();
                System.out.println("爬取耗时："+"\t"+ (endTime-startTime) +"\t");
                Thread.sleep(10000);
            }

        } catch (Exception e) { }
    }



}

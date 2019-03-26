package Webmagic.Hahadm;

import mongoDBConnect.ExcelTest.StringUtils;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***Created by moyongzhuo
 *On 2017/10/21  ***20:54.
 ******/
public class IIHahadmAnimationPageProcessor implements PageProcessor {
    private static int size = 0;// 共抓取到的文章数量
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    // private Site site = Site.me().setRetryTimes(3).setSleepTime(100);//.setCharset("utf8");
    private Site site = Site.me().setRetryTimes(8).setSleepTime(10000).setTimeOut(10000).setCharset("utf8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        IIHahadmAnimation iihahadmAnimation = new IIHahadmAnimation();
        Html html = page.getHtml();
        size++;
        String hahawebname = html.xpath("//meta[@property=\"og:title\"]/@content").get();//标题
        String hhdirector = html.xpath("//meta[@name=\"og:video:director\"]/@content").get();//导演
        String hhcontent = html.xpath("//meta[@property=\"og:description\"]/@content").get();//剧情
        float hhgoal = Float.parseFloat(html.xpath("//meta[@name=\"og:video:score\"]/@content").get());//得分
        int  hhmentotal = Integer.parseInt(html.xpath("//div[@class=\"c-rbox\"]/div[@class=\"c-share\"]/div[@class=\"ui-rate\"]/dl/dd/span[@class=\"votes\"]/text()").get());//人数
        int hhid = size;

        System.out.println(hahawebname);
        System.out.println(hhdirector);
        System.out.println(hhcontent);
        System.out.println(hhgoal);
        System.out.println(hhmentotal);
        System.out.println(hhid);

       iihahadmAnimation.setHahawebname(hahawebname);
       iihahadmAnimation.setDirector(hhdirector);
        iihahadmAnimation.setContent(hhcontent);
        iihahadmAnimation.setGoal(hhgoal);
        iihahadmAnimation.setMentotal(hhmentotal);
        iihahadmAnimation.setId(hhid);
        new IIHahadmAnimationDao().add(iihahadmAnimation);
    }

    public static void main(String[] args) throws Exception {
        PinyinTool tool = new PinyinTool();
        String username = "秦时明月";
        String username1 = tool.toPinYin(username, "", PinyinTool.Type.LOWERCASE);
        long startTime1, endTime1;
        System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
        startTime1 = System.currentTimeMillis();
        Spider.create(new IIHahadmAnimationPageProcessor()).addUrl("http://www.hahadm.com/v/" + username1).thread(5).run();
        endTime1 = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取耗时约" + ((endTime1 - startTime1) / 1000) + "秒，已保存到数据库，请查收！");
        Thread.currentThread().sleep(10000);
    }
}
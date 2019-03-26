package Webmagic.DouBao.com;

/***Created by moyongzhuo
 *On 2017/10/16  ***18:41.
 ******/
import java.util.List;

import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * CSDN博客爬虫
 *
 * @describe 可以爬取指定用户的csdn博客所有文章，并保存到数据库中。
 * @date 2016-4-30
 *
 * @author steven
 * @csdn rensihui
 * @website lyf.soecode.com
 */
public class OneAnimationProcessor implements PageProcessor {

    private static String username = "rensihui";// 设置csdn用户名
    private static int size = 0;// 共抓取到的文章数量
    private  static String a = null ;
    private  static  String b = null ;

    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    // private Site site = Site.me().setRetryTimes(3).setSleepTime(100);//.setCharset("utf8");
    private Site site = Site.me().setRetryTimes(10000).setSleepTime(1000).setTimeOut(10000)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        if (page.getUrl().regex("https://movie.douban.com/2129039/").match()) {
            b = page.getHtml().xpath("//div[@id='wrapper']//span[@property='v:itemreviewed']/text()").get();
            a = page.getHtml().xpath("//div[@id='wrapper']//span[@class='year']/text()").get();
            System.out.println("animation: "+ a );
            System.out.println("animation: "+ b );
        }
    }
    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
        startTime = System.currentTimeMillis();
        // 从用户博客首页开始抓，开启5个线程，启动爬虫addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
        Spider.create(new OneAnimationProcessor()).addUrl("https://movie.douban.com/2129039/").thread(5).run();
        System.out.println("animation: "+ a );
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共抓取" + size + "篇文章，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
    }
}


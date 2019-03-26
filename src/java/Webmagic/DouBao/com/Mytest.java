package Webmagic.DouBao.com;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;

/***Created by moyongzhuo
 *On 2017/10/16  ***14:23.
 ******/
public class Mytest implements PageProcessor {
    //抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(1000);
    //用户数量
    private static String username = "subject";// 设置csdn用户名
    private static int num = 0;
    //搜索关键词

    /**
     * process 方法是webmagic爬虫的核心<br>
     * 编写抽取【待爬取目标链接】的逻辑代码在html中。
     */
    @Override
    public void process(Page page) {

        //1. 如果是用户列表页面 【入口页面】，将所有用户的详细页面的url放入target集合中。
       if(page.getUrl().regex("https://movie.douban.com/tag/#/?sort=R&range=0,10&tags=%E5%8A%A8%E7%94%BB").match()){
               page.addTargetRequests(page.getHtml().xpath("//div[@id='list-wp']").links().all());
               /***
               page.addTargetRequests(page.getHtml().xpath("//div[@id='article_list']").links()// 限定文章列表获取区域
                       .regex("/" + username + "/article/details/\\d+")
                       .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// 巧用替换给把相对url转换成绝对url
                       .all());
                ***/
            // page.addTargetRequests(page.getHtml().xpath("//ul[@class='list users']/li/div/div[@class='body']/div[@class='line']").links().all());

        }
        //2. 如果是用户详细页面
        else{
            num++;//用户数++
            /*实例化ZhihuUser，方便持久化存储。*/
            Animation animation = new Animation();
            animation.setName(page.getHtml().xpath("//div[@class='list-wp']//span[@class='link_title']/a/text()").get());

            String a=null;
            a = page.getHtml().xpath("//div[@id='content']//span[@class='year']/text()").get();
            System.out.println("a: " + a);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        long startTime ,endTime;
        System.out.println("========知乎用户信息小爬虫【启动】喽！=========");
        startTime = new Date().getTime();
        //入口为：【https://www.zhihu.com/search?type=people&q=xxx 】，其中xxx 是搜索关键词
        Spider.create(new Mytest()).addUrl("https://movie.douban.com/tag/#/?sort=R&range=0,10&tags=%E5%8A%A8%E7%94%BB").thread(5).run();
        endTime = new Date().getTime();
        System.out.println("========知乎用户信息小爬虫【结束】喽！=========");
        System.out.println("一共爬到"+num+"个用户信息！用时为："+(endTime-startTime)/1000+"s");
    }
}

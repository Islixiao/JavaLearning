package Webmagic.DouBao.com;

/***Created by moyongzhuo
 *On 2017/10/16  ***19:02.
 ******/
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

class MyProcessor implements PageProcessor {
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(10).setSleepTime(10000);
    private static int count =0;
    private static String a = null;
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
            String name = page.getHtml().xpath("//div[@id='wrapper']/div[@id='content']/h1/span[@property=\"v:itemreviewed\"]/text()").get();
            String year = page.getHtml().xpath("//div[@id='wrapper']/div[@id='content']/h1/span[@class='year']/text()").toString();
            String doubananimationpath = "//div[@id='wrapper']/div[@id='content']/div[@class='grid-16-8 clearfix']/div[@class='article']/" +
                    "div[@class='indent clearfix']/div[@class=\"subjectwrap clearfix\"]/div[@class='subject clearfix']/div[@id='info']/";
            String actor = page.getHtml().xpath(doubananimationpath + "span[@class='actor']/span[@class=\"pl\"]/text()").get();
            List<String> director = page.getHtml().xpath(doubananimationpath + "span[@class='actor']/span[@class=\"pl\"]/span[@class=\"attrs\"]/a/text()").all();
            if(page.getHtml().xpath(doubananimationpath + "span[@class='actor']/span[@class=\"pl\"]/text()").toString() == "导演") {
                          String director1 = page.getHtml().xpath(doubananimationpath + "span/span[@class='attrs']/a/text()").toString();
                 }
            String witer = page.getHtml().xpath(doubananimationpath + "//span[@class='pl']/span[@class=\"attrs\"]/a/text()").toString();
            String content2 = page.getHtml().xpath("//div[@id='wrapper']/div[@id='content']/div[@class='grid-16-8 clearfix']/div[@class='article']/div[@class=\"related-info\"]/div[@class='indent']/span[@class='pl']/a/text()").toString();
            String content3 = page.getHtml().xpath("//div[@id='wrapper']/div[@id='content']/div[@class='grid-16-8 clearfix']/div[@class='article']/div[@class=\"related-info\"]/div[@class='indent']/span[@class=\"all hidden\"]/text()").toString();
            String content = page.getHtml().xpath("//div[@id='wrapper']/div[@id='content']/div[@class='grid-16-8 clearfix']/div[@class='article']/div[@class=\"related-info\"]/h2/i/text()").toString();

            String urll= page.getHtml().xpath("//div[@id='wrapper']/div[@id='content']/div[@class='grid-16-8 clearfix']/div[@class='article']/div[@class=\"related-info\"]/div[@class='indent']/span[@class='pl']/a[@herf]").get();
        //page.getHtml().xpath("//div[@id='content']/h1/span[@id=\"recommendations-bd\"]/dl/dd/a/@href").all();
            //a = page.getHtml().xpath("//div[@id='wrapper']/div/div/div/span[@id=\"recommendations-bd\"]/dl/dd/a/@href").get();
           // System.out.println("a: " + a);
        System.out.println("urll: " + urll);
            System.out.println("name: " + name);
            System.out.println("year: " + year);
            System.out.println("actor: " + actor);
             System.out.println("content: " + content);
            System.out.println("content2: " + content2);
            System.out.println("content3: " + content3);
            System.out.println("urll: " + urll);
            for(String l : director){
            System.out.println("director: " + l);
        }
        int count = 0;
        for(int c = 0; c < director.size(); c++)
        {
            System.out.println("director: " + director.get(c));
        }
            System.out.println("witer: " + witer);
    }

    public static void main(String[] args) {
        MyProcessor my = new MyProcessor();
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(my).addUrl("https://movie.douban.com/subject/2129039/").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }

}

package Webmagic.BaozouManhua.com;

import us.codecraft.webmagic.Spider;

/***Created by moyongzhuo
 *On 2017/10/10  ***21:05.
 ******/
public class TestBaozou  {
    public static void main(String[] args) {
        Spider.create(new BaoZouProcessor())
                // 从"http://baozoumanhua.com/text"开始抓
                .addUrl("http://baozoumanhua.com/text").addPipeline(new BaoZouPipeLine())
                // 开启5个线程抓取
                .thread(5)
                // 启动爬虫
                .run();
    }
}


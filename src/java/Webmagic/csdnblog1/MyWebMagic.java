package Webmagic.csdnblog1;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/11  ***19:10.
 ******/
public class MyWebMagic implements PageProcessor {

    private Site site = Site.me().setDomain("my.oschina.net");

    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyWebMagic()).addUrl("http://my.oschina.net/flashsword/blog")
                .addPipeline(new ConsolePipeline()).run();
    }

}

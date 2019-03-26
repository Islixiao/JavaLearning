package Webmagic.csdnblog1;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.example.OschinaBlog;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/11  ***19:13.
 ******/

@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
public class MyWebMagic_1 {
    @ExtractBy("//title")
    private String title;

    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    private String content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    public static void main(String[] args) {
        OOSpider.create(
                Site.me(),
                new ConsolePageModelPipeline(), OschinaBlog.class).addUrl("http://my.oschina.net/flashsword/blog").run();
    }
}

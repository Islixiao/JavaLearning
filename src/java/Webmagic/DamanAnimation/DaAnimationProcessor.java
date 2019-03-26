package Webmagic.DamanAnimation;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.IOException;
import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/23  ***18:45.
 ******/
public class DaAnimationProcessor implements PageProcessor{
    private static int myid = 6880;
    int size = 0;
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1000).setSleepTime(1000);
    @Override
    public Site getSite() {
        return site;
    }
    @Override
    public void process(Page page) {
        DaAnimation daAnimation = new DaAnimation();
        Html html = page.getHtml();
        myid++;
        size++;
        // String hahawebname = html.xpath("//div[@ class=\"wrap\"]/div[@class=\"middleright\"]/div[@class=\"middleright_mr\"" +
        // String hahawebname = html.xpath("//div[@class=\"tvversion\"]/a/span/h1/@text()").get();//标题
        // System.out.println("爬取结束，耗时约" + hahawebname + "秒");
        int hhid = myid;//class="intro_l"
        // String goal = html.xpath("//div[@class='intro_l']/div[@class='score']/div[@class='score_content']/div[@class='score_avg']/span/em/text()").get();//得分
        // String mentotal = html.xpath("//div[@class=\"score\"]/div[@class=\"score_content\"]/div[@class=\"score_total\"]/span/text()").get();//得分
        String damanwebname = html.xpath("//meta[@property=\"og:title\"]/@content").get();//title
        String content = html.xpath("//meta[@property=\"og:description\"]/@content").get();//title
        String imgurl = html.xpath("//meta[@property=\"og:image\"]/@content").get();//imgurl
        String url = html.xpath("//meta[@property=\"og:url\"]/@content").get();//imgurl
        String vediourl = html.xpath("//meta[@property=\"og:video\"]/@content").get();//imgurl
        String mainrole11 = html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//imgurl
        String mainrole = mainrole11.replaceAll(",", " / ");
        String area = html.xpath("//meta[@name=\"og:video:area\"]/@content").get();//imgurl
        String tag11 = html.xpath("//meta[@name=\"og:video:class\"]/@content").get();//imgurl
        String tag1111 = tag11.replaceAll(" ", " / ");
        String tag = tag1111.replaceAll(",", " / ");
        String language = html.xpath("//meta[@property=\"og:video:language\"]/@content").get();//imgurl

        //String year11 =  html.xpath("//div[@class=\"info\"]/p[@class=\"w260d\"]/text()").get();//year
        //String year1111 = year11.replaceAll("</?[^>]+>","");
        // String year = null;
        List<Selectable> nodes = html.xpath("//div[@class='info']/p[@class='w260d']").nodes();
        for (Selectable item : nodes) {
            String tmp = item.get();
            if (tmp.contains("出品年份")) {
                String year11 = tmp.replaceAll("</?[^>]+>", "");
                //replaceAll("<.*?>角色[：:]|<", ""));        //  导演：福田道生原作：椎桥宽编剧：子安秀明
                String year = year11.replaceAll(".*?出品年份[：:]", "");
                daAnimation.setYear(year);
                System.out.println("year: " + year);
            }

        }
        System.out.println("id: " + hhid);
        System.out.println("damanwebname: " + damanwebname);
        System.out.println("content: " + content);
        System.out.println("imgurl: " + imgurl);
        System.out.println("url: " + url);
        System.out.println("vediourl: " + vediourl);
        System.out.println("mainrole: " + mainrole);
        System.out.println("area: " + area);
        System.out.println("tag: " + tag);
        System.out.println("langue: " + language);

        daAnimation.setId(hhid);
        daAnimation.setDamanwebname(damanwebname);
        daAnimation.setContent(content);
        daAnimation.setImgurl(imgurl);
        daAnimation.setUrl(url);
        daAnimation.setVediourl(vediourl);
        daAnimation.setMainrole(mainrole);
        daAnimation.setArea(area);
        daAnimation.setTag(tag);
        daAnimation.setLanguage(language);
//        new DaAnimationDao().add(daAnimation);
    }

    public static void main(String[] args) throws IOException{
        int username = 9233;
        long startTime, endTime;
        for (; username >= 1; username--) {
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();
            Spider.create(new DaAnimationProcessor()).addUrl("http://www.daman.cc/comic1/" + username + "/").thread(5).run();
            endTime = System.currentTimeMillis();
            System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
        }

    }

}

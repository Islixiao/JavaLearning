package Webmagic.BaiduAnimation;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***Created by moyongzhuo
 *On 2017/10/18  ***20:55.
 ******/
public class BaiduAnimationPageProcessor implements PageProcessor {

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1000).setSleepTime(10000);
    private static int count =0;
    private static String a = null;
    public String hhoriginal = null;
    public String hhScreenwriter = null;
    public String anothername = null;
    public String Realname ="1234";
    public int id1 = -1;
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {

//        String doubananimationpath = "//div[@id='wrapper_wrapper']/div[@id='container']/div[@id='content_left']/div[@class=\"result-op c-container\"]/" +
//                "div[@class=\"c-border\"]/div[@class=\"op_exactqa_main\"]/div[@class=\"op_exactqa_body\"]/div[@class=\"op_exactqa_itemsArea c-row \"]/" +
//                "div[@class=\"op_exactqa_item c-gap-bottom c-span6 \"]/p[@class=\"c-gap-top-small\"]/a/text()";
//        List<String> name = page.getHtml().xpath(doubananimationpath).all();
//        // System.out.println("a: " + a);
//        System.out.println("name: " + name.get(1));
/*Pattern pattern = Pattern.compile("<.*?>角色[：:].*?<");
        String html = page.getHtml().toString();
        Matcher matcher = pattern.matcher(html);
        if(matcher.find()) {
            System.out.println(matcher.group().replaceAll("<.*?>角色[：:]|<", ""));
        }*/

/**
        String path1 = "//div[@class='mbak']/div[@ class=\"width\"]/div[@class=\"oh hReview-aggregate\"]/div[@class=\"c-video\"]/" +
                "div[@class=\"info item\"]/";
        String path2 = "//div[@class=\"mbak\"]/div[@ class=\"width\"]/div[@class=\"oh hReview-aggregate\"]/div[@class=\"c-rbox\"]/" ;
        String title1 = "//div[@class=\"title\"]/h1/text();";
        String tag1 = "//div[@class=\"detail oh\"]/p;";

        String html1 =  page.getHtml().xpath(path1+title1).toString();
        String tag2  =  page.getHtml().xpath(path1+tag1).toString().replaceAll("</?[^>]+>", "");
        System.out.println("name: " + html1);
        System.out.println("tag2: " + tag2);

**/
        //====
         /***
        Html html2 = page.getHtml();
        List<Selectable> nodes = html2.xpath("//div[@class='detail oh']/p").nodes();
        for(Selectable item : nodes){
            String tmp = item.get();

            System.out.println("==================="+item.get().replaceAll("</?[^>]+>",""));
        }    private String realname;// 标题
          private String hahawebname;// 标题
          private String tag;//类别
          private String year;//播放时间
          private String director;//导演
          private String original;//原作
          private String Screenwriter;//编剧
          private String dubbing;//配音
          private String role;//角色
          private String area;//地区
          private String languge;//语言
          private String content;//剧情简介
          private double goal;//评分
          private int mentotal;//评分人数
        ***/
         id1++;
        BaiduAnimation baiduAnimation = new BaiduAnimation();
        Html html = page.getHtml();

        List<Selectable> nodes = html.xpath("//div[@class='detail oh']/p").nodes();
        for(Selectable item : nodes){
            String tmp = item.get();
            if(tmp.contains("原作：")) {
                String hhoriginal = tmp.replaceAll("</?[^>]+>","");
                //replaceAll("<.*?>角色[：:]|<", ""));        //  导演：福田道生原作：椎桥宽编剧：子安秀明
                String hhoriginal1 =hhoriginal.replaceAll(".*?原作[：:]|编剧(.*)","");
                String hhScreenwriter1 =hhoriginal.replaceAll(".*?编剧[：:]| ","");
                System.out.println(hhoriginal1);
                System.out.println(hhScreenwriter1);
                baiduAnimation.setOriginal(hhoriginal1);//圆桌
                baiduAnimation.setScreenwriter(hhScreenwriter1);//编剧
            }
            if(tmp.contains("别名：")) {
                String anothername = tmp.replaceAll("</?[^>]+>","");
                System.out.println(anothername);
                baiduAnimation.setAllname(anothername);
            }
            System.out.println("==================="+item.get().replaceAll("</?[^>]+>",""));
        }

        String hahawebname = html.xpath("//meta[@property=\"og:title\"]/@content").get();//标题
        String hhtag = html.xpath("//meta[@name=\"og:video:class\"]/@content").get();//类别
        String hhyear =html.xpath("//meta[@name=\"og:video:release_date\"]/@content").get();//首播时间
        String hhdirector = html.xpath("//meta[@name=\"og:video:director\"]/@content").get();//导演

       // String hhoriginal = html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//圆桌
        //String hhScreenwriter = html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//编剧

        String hhdubbing = html.xpath("//meta[@name=\"og:video:voice_actor\"]/@content").get();//配音
        String hhrole =html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//配音
        String hharea = html.xpath("//meta[@name=\"og:video:area\"]/@content").get();//地区
        String hhlanguge = html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//语言
        String hhcontent = html.xpath("//meta[@property=\"og:description\"]/@content").get();//剧情
        String hhgoal = html.xpath("//meta[@name=\"og:video:score\"]/@content").get();//得分

        String hhmentotal = html.xpath("//div[@class=\"c-rbox\"]/div[@class=\"c-share\"]/div[@class=\"ui-rate\"]/dl/dd/span[@class=\"votes\"]/text()").get();//人数
        String hhurl = html.xpath("//meta[@property=\"og:url\"]/@content").get();//url
        String hhvediourl = html.xpath("//meta[@name=\"og:video\"]/@content").get();//urlvedio
        System.out.println(hahawebname);
        System.out.println(hhtag);
        System.out.println(hhyear);
        System.out.println(hhdirector);
        System.out.println(hhoriginal);
        System.out.println(hhScreenwriter);
        System.out.println(hhdubbing);
        System.out.println(hharea);
        System.out.println(hhlanguge);
        System.out.println(hhcontent);
        System.out.println(hhgoal);
        System.out.println(hhmentotal);
        System.out.println(hhurl);
        System.out.println(hhvediourl);
        System.out.println(anothername);

        baiduAnimation.setId(id1);
        baiduAnimation.setRealname(Realname);//原名
        baiduAnimation.setHahawebname(hahawebname);
        baiduAnimation.setTag(hhtag);//类别
        baiduAnimation.setYear(hhyear);
        baiduAnimation.setDirector(hhdirector);
        baiduAnimation.setOriginal(hhoriginal);//圆桌
        baiduAnimation.setScreenwriter(hhScreenwriter);//编剧

        baiduAnimation.setDubbing(hhdubbing);//配音
        baiduAnimation.setRole(hhrole);
        baiduAnimation.setArea(hharea);//地区
        baiduAnimation.setLanguge(hhlanguge);
        baiduAnimation.setContent(hhcontent);

        baiduAnimation.setGoal(hhgoal);
        baiduAnimation.setMentotal(hhmentotal);//人数
        //baiduAnimation.setAllname(anothername);
        baiduAnimation.setUrl(hhurl);
        baiduAnimation.setVediourl(hhvediourl);
//        new BaiduAnimationDao().add(baiduAnimation);
    }


        // 把list转换为string，用,分割
        public static String listToString(List<String> stringList) {
            if (stringList == null) {
                return null;
            }
            StringBuilder result = new StringBuilder();
            boolean flag = false;
            for (String string : stringList) {
                if (flag) {
                    result.append(",");
                } else {
                    flag = true;
                }
                result.append(string);
            }
            return result.toString();
        }



    public static void main(String[] args) {
        BaiduAnimationPageProcessor my = new BaiduAnimationPageProcessor();
        long startTime, endTime;
        System.out.println("开始爬取...");
        startTime = System.currentTimeMillis();
        Spider.create(my).addUrl("http://www.hahadm.com/v/HuaTouGuiZhiSunOAD/").thread(5).run();
        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
    }
}

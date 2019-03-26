package Webmagic.donghua.dmzj.com;

import Webmagic.BaiduAnimation.BaiduAnimation;
import Webmagic.BaiduAnimation.BaiduAnimationDao;
import Webmagic.BaiduAnimation.BaiduAnimationPageProcessor;
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
 *On 2017/10/23  ***12:09.
 ******/
public class DmzjAnimationProcessor implements PageProcessor {
    int myid = 0;
    int size =10;
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1000).setSleepTime(1000);
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        DmzjAnimation dmzjAnimation = new DmzjAnimation();
        Html html = page.getHtml();
        size++;
        myid++;
        // String hahawebname = html.xpath("//div[@ class=\"wrap\"]/div[@class=\"middleright\"]/div[@class=\"middleright_mr\"" +
       // String hahawebname = html.xpath("//div[@class=\"tvversion\"]/a/span/h1/@text()").get();//标题
       // System.out.println("爬取结束，耗时约" + hahawebname + "秒");
        int id = myid;
        dmzjAnimation.setId(id);
        String hahawebname = html.xpath("//div[@class=\"odd_anim_title_tnew\"]/div[@class=\"tvversion\"]/a/span[@class=\"anim_title_text\"]/h1/text()").get();//得分
        dmzjAnimation.setHahawebname(hahawebname);
        String goal = html.xpath("//div[@class=\"anim_star\"]/ul/li[@id=\"anim_score_info\"]/span[@class=\"points_text\"]/text()").get();//得分
        dmzjAnimation.setGoal(goal);
        String mentotalold = html.xpath("//div[@class=\"anim_star\"]/ul/li[@id=\"score_statistics\"]/span[@id=\"score_count_span\"]/text()").get();//人数
        String mentotal = mentotalold.replaceAll("人评分","");
        dmzjAnimation.setMentotal(mentotal);
        String content = html.xpath("//div[@class=\"odd_anim_title_mnew\"]/p/span[@id=\"gamedescshort\"]/text()").get();//内容
        dmzjAnimation.setContent(content);
        String contentdetail = html.xpath("//div[@class=\"odd_anim_title_mnew\"]/p/span[@id=\"gamedescall\"]/text()").get();//内容
        dmzjAnimation.setContentdetail(contentdetail);
        System.out.println("hahawebname: "+ hahawebname);
        System.out.println("goal: "+goal);
        System.out.println("mentotal: "+ mentotal);
        System.out.println("content: "+ content);
        System.out.println("contentdetail: "+ contentdetail);
        List<Selectable> nodes = html.xpath("//div[@class=\"anim_attributenew_text\"]/ul/li").nodes();
        for(Selectable item : nodes){
            String tmp = item.get();
            if(tmp.contains("动画种类")) {//动画种类 : 剧场版
                String antag11 = tmp.replaceAll("</?[^>]+>","");
                String antag = antag11.replaceAll("动画种类 : ","");
                System.out.println(antag);
                dmzjAnimation.setAntag(antag);
            }//日文名 : 暂无
            if(tmp.contains("日文名")) {
                String japanname11 = tmp.replaceAll("</?[^>]+>","");
                String japanname = japanname11.replaceAll("日文名 : ","");
                if(japanname.contains("暂无")){japanname = null;}
                System.out.println(japanname);
              dmzjAnimation.setJapanname(japanname);
            }//别名 : 大闹天宫 上下集 / The Monkey King
            if(tmp.contains("别名")) {
                String allname11 = tmp.replaceAll("</?[^>]+>","");
                String allname = allname11.replaceAll("别名 : ","");
                if(allname.contains("暂无")){allname = null;}
                System.out.println(allname);
                dmzjAnimation.setAllname(allname);
                //  baiduAnimation.setAllname(japanname);
            }//首播时间 : 暂无
            if(tmp.contains("首播")) {
                String year11 = tmp.replaceAll("</?[^>]+>","");
                String year1111 = year11.replaceAll("首播时间 : ","");
                String year = year1111;
                if(year1111.contains("暂无")){year = null;}
                System.out.println(year);
                dmzjAnimation.setYear(year);
                //  baiduAnimation.setAllname(japanname);
            }
            if(tmp.contains("播放状态")) {
                String state11 = tmp.replaceAll("</?[^>]+>","");
                String state1111 = state11.replaceAll("播放状态 : ","");
                String state = state1111;
                if(state.contains("暂无")){state = null;}
                System.out.println(state);
                dmzjAnimation.setState(state);
                //  baiduAnimation.setAllname(japanname);
            }
            if(tmp.contains("剧情类型")) {
                String tag11 = tmp.replaceAll("</?[^>]+>","");
                String tag1111 = tag11.replaceAll("剧情类型 : ","");
                String tag = tag1111.replaceAll(" "," / ");
                System.out.println(tag);
                dmzjAnimation.setTag(tag);
                //  baiduAnimation.setAllname(japanname);
            }//原作 : 暂无
            if(tmp.contains("原作")) {
                String original11 = tmp.replaceAll("</?[^>]+>","");
                String original1111 = original11.replaceAll("原作 :","");
                String original = original1111;
                if(original.contains("暂无")){original = null;}
                System.out.println(original);
                dmzjAnimation.setOriginal(original);
                //  baiduAnimation.setAllname(japanname);
            }//监督 : 万籁鸣 / 唐澄
            if(tmp.contains("监督")) {
                String screenwriter11 = tmp.replaceAll("</?[^>]+>","");
                String screenwriter1111 = screenwriter11.replaceAll("监督 :","");
                String screenwriter = screenwriter1111;
                if(screenwriter.contains("暂无")){screenwriter = null;}
                dmzjAnimation.setScreenwriter(screenwriter);
                System.out.println(screenwriter);
                //  baiduAnimation.setAllname(japanname);
            }//制作公司 : 上海美术电影制片厂
            if(tmp.contains("制作公司")) {
                String company11 = tmp.replaceAll("</?[^>]+>","");
                String company1111 = company11.replaceAll("制作公司 :","");
                company1111 = company1111 +" / "+ company1111 +"公司";
                String company = company1111;
                if(company.contains("暂无")){company = null;}
                System.out.println(company);
                dmzjAnimation.setCompany(company);
                //  baiduAnimation.setAllname(japanname);
            }//官方网站 : 暂无
            if(tmp.contains("官方网站")) {
                String website =  tmp.replaceAll(".*?href=|target(.*)","");
                if(website.contains("暂无")){website = null;}
                System.out.println(website);
                dmzjAnimation.setWebsite(website);
                //  baiduAnimation.setAllname(japanname);
            }
        }
        String url = "http://donghua.dmzj.com/donghua_info/"+size+".html";
        dmzjAnimation.setUrl(url);
//        new DmzjAnimationDao().add(dmzjAnimation);

/*
        List<Selectable> nodesuuu = html.xpath("//div[@class=\"anim_attributenew_text\"]/ul/li").nodes();
        StringBuffer  relevant = null;
        for(Selectable item : nodesuuu){
            String tmp = item.get();
            String oourl =  tmp.replaceAll(".*?href=|title(.*)","");
            String ooname =  tmp.replaceAll(".*?title=|>(.*)","");
            String oodata =  tmp.replaceAll(".*?</a>|</li>(.*)","");
            relevant.append(oourl).append(ooname).append(oodata).append("  ");
            System.out.println(ooname+oourl+oodata);
            }*/
    }



    public static void main(String[] args) {
        int username = 10;
        DmzjAnimationProcessor my = new DmzjAnimationProcessor();
        long startTime, endTime;
        System.out.println("开始爬取...");
        for(;username<=15000;username++) {
            startTime = System.currentTimeMillis();
            Spider.create(my).addUrl("http://donghua.dmzj.com/donghua_info/" + username + ".html").thread(5).run();
            endTime = System.currentTimeMillis();
            System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
        }
    }
}

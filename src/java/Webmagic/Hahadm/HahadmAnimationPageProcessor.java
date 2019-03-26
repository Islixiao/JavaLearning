package Webmagic.Hahadm;

import mongoDBConnect.ExcelTest.StringUtils;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***Created by moyongzhuo
 *On 2017/10/21  ***19:47.
 ******/
public class HahadmAnimationPageProcessor implements PageProcessor {
    private static int size = 259;// 共抓取到的文章数量
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    // private Site site = Site.me().setRetryTimes(3).setSleepTime(100);//.setCharset("utf8");
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(1000).setCharset("utf8")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        HahadmAnimation hahadmAnimation = new HahadmAnimation();
        Html html = page.getHtml();
        size++;
        int  hhid = size;
        hahadmAnimation.setId(hhid);
        String hahawebname = html.xpath("//meta[@property=\"og:title\"]/@content").get();//urlvedio
        hahadmAnimation.setHahawebname(hahawebname);
        String hhtag = html.xpath("//meta[@name=\"og:video:class\"]/@content").get();//类型
        hahadmAnimation.setTag(hhtag);//类别
        String hhyear =html.xpath("//meta[@name=\"og:video:release_date\"]/@content").get();//首播时间
        hahadmAnimation.setYear(hhyear);
        String hhdirector = html.xpath("//meta[@name=\"og:video:director\"]/@content").get();//配音;//导演
        hahadmAnimation.setDirector(hhdirector);
        String hhcontent = html.xpath("//meta[@property=\"og:description\"]/@content").get();//剧情
        hahadmAnimation.setContent(hhcontent);
        float hhgoal = Float.parseFloat(html.xpath("//meta[@name=\"og:video:score\"]/@content").get());//得分
        hahadmAnimation.setGoal(hhgoal);
        int  hhmentotal = Integer.parseInt(html.xpath("//div[@class=\"c-rbox\"]/div[@class=\"c-share\"]/div[@class=\"ui-rate\"]/dl/dd/span[@class=\"votes\"]/text()").get());//人数
        hahadmAnimation.setMentotal(hhmentotal);//人数

        //圓桌，編劇，別名
        List<Selectable> nodes111 = html.xpath("//div[@class='detail oh']/p").nodes();
        for(Selectable item : nodes111){
            String tmp = item.get();
            if(tmp.contains("原作：")) {
                String hhoriginal = tmp.replaceAll("</?[^>]+>","");
                //replaceAll("<.*?>角色[：:]|<", ""));        //  导演：福田道生原作：椎桥宽编剧：子安秀明
                String hhoriginal1 =hhoriginal.replaceAll(".*?原作[：:]|编剧(.*)","");
                String hhScreenwriter1 =hhoriginal.replaceAll(".*?编剧[：:]| ","");
                System.out.println(hhoriginal1);
                System.out.println(hhScreenwriter1);
                hahadmAnimation.setOriginal(hhoriginal1);//圆桌
                hahadmAnimation.setScreenwriter(hhScreenwriter1);//编剧
            }
            if(tmp.contains("别名：")) {
                String anothername = tmp.replaceAll("</?[^>]+>","");
                System.out.println(anothername);
                hahadmAnimation.setAllname(anothername);
            }
            // System.out.println("==================="+item.get().replaceAll("</?[^>]+>",""));
        }
        String hhdubbing = html.xpath("//meta[@name=\"og:video:voice_actor\"]/@content").get();//配音
        hahadmAnimation.setDubbing(hhdubbing);//配音
        String hhrole = html.xpath("//meta[@name=\"og:video:actor\"]/@content").get();//角色
        hahadmAnimation.setRole(hhrole);
        String hharea = html.xpath("//meta[@name=\"og:video:area\"]/@content").get();//地区
        hahadmAnimation.setArea(hharea);//地区
        String hhlanguge = html.xpath("//meta[@name=\"og:video:language\"]/@content").get();//语言
        hahadmAnimation.setLanguge(hhlanguge);

        String hhurl = html.xpath("//meta[@property=\"og:url\"]/@content").get();//url
        hahadmAnimation.setUrl(hhurl);
        String hhvediourl = html.xpath("//meta[@name=\"og:video\"]/@content").get();//urlvedio
        hahadmAnimation.setVediourl(hhvediourl);
        String hhimageurl = html.xpath("//meta[@property=\"og:image\"]/@content").get();//urlvedio
        hahadmAnimation.setHhimageurl(hhimageurl);

        //获取详细内容,剧情
        List<Selectable> nodes = html.xpath("//div[@class=\"mbak\"]/div[@class=\"width\"]/div[@id=\"c-detail\"]/p").nodes();
        String hhcontentdetail = null;
        String aa=  null;
        StringBuffer tmpbuffer = new StringBuffer();
        int ii = 0;
        for(Selectable item : nodes){
            ii++;
            String tmp = item.get().replaceAll("</?[^>]+>","");
            tmpbuffer.append(tmp);
            aa = tmpbuffer.toString();
            //System.out.println("!!!!!!!!!!!!!!!!!!"+aa);
            if((ii+1) == nodes.size()){
                hhcontentdetail = tmpbuffer.toString();
                hahadmAnimation.setContentdetail(aa);
                //System.out.println("________________________-"+hhcontentdetail);
            }
        }





       // String hhcontent = html.xpath("//div[@id=\"c-detail\"]/p/allText()").get();//类别//剧情
       // html.xpath("//meta[@name=\"og:video:class\"]/@content").get()
/*        List<Selectable> nodes = html.xpath("//div[@class='detail oh']/p").nodes();
        for(Selectable item : nodes){
            String tmp = item.get();
            if(tmp.contains("原作：")) {
                String hhoriginal = tmp.replaceAll("</?[^>]+>","");
                //replaceAll("<.*?>角色[：:]|<", ""));        //  导演：福田道生原作：椎桥宽编剧：子安秀明
                String hhoriginal1 =hhoriginal.replaceAll(".*?原作[：:]|编剧(.*)","");
                String hhScreenwriter1 =hhoriginal.replaceAll(".*?编剧[：:]| ","");

                System.out.println(hhoriginal1);
                System.out.println(hhScreenwriter1);
                hahadmAnimation.setHahawebname(hhoriginal1);//圆桌
                hahadmAnimation.setDirector(hhScreenwriter1);//编剧
            }
            if(tmp.contains("别名：")) {
                String anothername = tmp.replaceAll("</?[^>]+>","");
                System.out.println(anothername);
                hahadmAnimation.setContent(anothername);
            }
            System.out.println("==================="+item.get().replaceAll("</?[^>]+>",""));
        }*/

        System.out.println(hahawebname);
        System.out.println(hhdirector);
        System.out.println(hhcontent);
       // hahadmAnimation.setContent(hhcontent);

        new HahadmAnimationDao().add(hahadmAnimation);
    }
    public static void main(String[] args) throws Exception {
        PinyinTool tool = new PinyinTool();
        String username = "果宝特攻4";
        List<String> fithTest = null;
        List<String> willAddList = null;
        Set<String> willAddNameSet = new HashSet<>();
        long startTimetotal, endTimetotal;
        startTimetotal = System.currentTimeMillis();
        try {
            fithTest = FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\Webmagic\\Hahadm\\动漫之家排行450.txt"));
            willAddList = FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\Webmagic\\Hahadm\\1022haha450爬虫221TXT取name.txt"));
            // FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\1020竹简删除后相同的名字.txt"));
            for (String willAddStr : willAddList) {
                String []array = willAddStr.split("\t");
                if (!array[0].isEmpty()) {
                    willAddNameSet.add(array[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line11 : fithTest) {
            if (willAddNameSet.contains(line11)) {
                continue;
            }
            if (StringUtils.isNotBlank(line11)) {
                String pinyin = "}<>;'|])#@!%^&*  ><山 田君  与 7位魔女";
                String pinyin11 = line11.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
                String pinyin1111 = pinyin11.replaceAll( " " , "");
                String username1 = tool.toPinYin(pinyin1111, "", PinyinTool.Type.LOWERCASE);
                long startTime1, endTime1;
                System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
                startTime1 = System.currentTimeMillis();
                Spider.create(new HahadmAnimationPageProcessor()).addUrl("http://www.hahadm.com/v/" + username1).thread(5).run();
                endTime1 = System.currentTimeMillis();
                System.out.println("【爬虫结束】共抓取耗时约" + ((endTime1 - startTime1) / 1000) + "秒，已保存到数据库，请查收！");
            }
        }
        endTimetotal = System.currentTimeMillis();
        System.out.println("时间为： " + ((endTimetotal - startTimetotal) / 1000));
    }
}

package Webmagic.Kt51Animation;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/***Created by moyongzhuo
 *On 2017/10/24  ***11:54.
 ******/
public class Kt51AnimationProcessor implements PageProcessor {
    int myid = 0;
    int size =10;

    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1000).setSleepTime(1000).setCharset("gb2312");
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        Kt51Animation kt51Animation = new Kt51Animation();
        Html html = page.getHtml();
        size++;
        myid++;
        // String hahawebname = html.xpath("//div[@ class=\"wrap\"]/div[@class=\"middleright\"]/div[@class=\"middleright_mr\"" +
        // String hahawebname = html.xpath("//div[@class=\"tvversion\"]/a/span/h1/@text()").get();//标题
        // System.out.println("爬取结束，耗时约" + hahawebname + "秒");
        int id = myid;
        kt51Animation.setId(id);
        //dmzjAnimation.setId(id);
        System.out.println("id: "+ id);
        String hahawebname = html.xpath("//div[@class=\"w980 mt10 clearfix\"]/div[@class=\"intro_l\"]/div[@class=\"title\"]/h1/text()").get();//得分
        System.out.println("hahawebname: "+ hahawebname);
        kt51Animation.setHahawebname(hahawebname);
     /*   String goal = html.xpath("//div[@class=\"intro_r\"]/div[@class=\"score\"]/div[@class=\"score_content\"]/div[@class=\"score_avg\"]/span/em/text()").get();//得分
        System.out.println("goal: "+ goal);
        String mentotal = html.xpath("//div[@class=\"intro_r\"]/div[@class=\"score\"]/div[@class=\"score_content\"]/div[@class=\"score_total\"]/span/text()").get();//得分
        System.out.println("mentotal: "+ mentotal);*/

        List<Selectable> nodes11 = html.xpath("//div[@class='position']/a").nodes();
        for(Selectable item : nodes11) {
            String tmp = item.get();
            if (tmp.contains("detail")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String url11 = tmp.replaceAll(".*?detail/|html(.*)", "");
                String url1p = url11.substring(1,url11.length());
                String url = "http://www.kt51.com/view/"+url1p+ "html";
                System.out.println("url:"+url);
                // dmzjAnimation.setAntag(antag);
                kt51Animation.setUrl(url);
            }
        }
        String content11= html.xpath("//div[@class='info']/div[@class='w384 intro_s']").get();//内容
        String content1p =content11.replaceAll(">> 更多</a>-->","");
        String content111p =content1p.replaceAll("…&nbsp;","");
        String contentqq =content111p.replaceAll("</?[^>]+>","");
        String content =contentqq.replaceAll(" ","");
        kt51Animation.setContent(content);
        System.out.println("content: "+ content);
        List<Selectable> nodes = html.xpath("//div[@class='info']/div[@class='w260']").nodes();
        for(Selectable item : nodes){
            String tmp = item.get();
            if(tmp.contains("更新时间")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String uptime11 = tmp.replaceAll("</?[^>]+>","");
                String uptime = uptime11.replaceAll("更新时间：","");
                System.out.println(uptime);
               // dmzjAnimation.setAntag(antag);
                kt51Animation.setUptime(uptime);
            }
            if(tmp.contains("出品年份")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String year11 = tmp.replaceAll("</?[^>]+>","");
                String year = year11.replaceAll("出品年份：","");
                System.out.println(year);
                // dmzjAnimation.setAntag(antag);
                kt51Animation.setYear(year);
            }
            if(tmp.contains("原著作者")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String original11 = tmp.replaceAll("</?[^>]+>","");
                String original1111 = original11.replaceAll("原著作者：","");
                String original1p = original1111.replaceAll("&nbsp;&nbsp;\n"," / ");
                String original = original1p.substring(3,(original1p.length()-3));
         /*       try {
                    original = URLEncoder.encode(original, "UTF-8");
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }*/
                System.out.println(original);
                // dmzjAnimation.setAntag(antag);
                kt51Animation.setOriginal(original);
            }
            if(tmp.contains("对白语言")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String language11 = tmp.replaceAll("</?[^>]+>","");
                String language1111 = language11.replaceAll("对白语言：","");
                String language1p = language1111.replaceAll(" "," / ");
                String language = language1p.substring(3,language1p.length());
                System.out.println(language);
                // dmzjAnimation.setAntag(antag);
                kt51Animation.setLanguage(language);
            }
            if(tmp.contains("剧情类别")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String tag11 = tmp.replaceAll("</?[^>]+>","");
                String tag1111 = tag11.replaceAll("剧情类别：","");
                String tag1p= tag1111.replaceAll(" "," / ");
                String tag = tag1p.substring(3,tag1p.length());
                System.out.println(tag);
                // dmzjAnimation.setAntag(antag);
                kt51Animation.setTag(tag);
            }
            if(tmp.contains("主　　角")) {//<h4>更新时间：</h4>2017-05-25 23:57</div>
                String mainrole11 = tmp.replaceAll("</?[^>]+>","");
                String mainrole1111 = mainrole11.replaceAll("主　　角：","");
                String mainrole1p = mainrole1111.replaceAll("&nbsp;&nbsp;\n"," / ");
                String mainrole = mainrole1p.substring(1,(mainrole1p.length()-2));
               // String mainrole = mainrole1p.replaceAll(" "," / ");
                System.out.println("main: "+mainrole);
                // dmzjAnimation.setAntag(antag);
                kt51Animation.setMainrole(mainrole);
            }


        }

//        new Kt51AnimatonDao().add(kt51Animation); //导入数据库
        /***
        String url = "http://donghua.dmzj.com/donghua_info/"+size+".html";
        dmzjAnimation.setUrl(url);
        new DmzjAnimationDao().add(dmzjAnimation);
*/
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
        Kt51AnimationProcessor kt = new Kt51AnimationProcessor();
        int username = 5000;
        long startTime, endTime;
        System.out.println("开始爬取...");
        for(;username<=50000;username++) {
            startTime = System.currentTimeMillis();
            Spider.create(kt).addUrl("http://www.kt51.com/view/" + username + ".html").thread(1).run();
            endTime = System.currentTimeMillis();
            System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒");
        }
    }
}


package Webmagic.AutoHome

import org.bson.Document
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.Test

/***Created by moyongzhuo
 * On 2018/4/22  ***9:11.
 ******/
import javax.script.*;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

/**
 * 实现对脚本文件的定义
 * 运用此类需要最低JDK编译为1.6+
 * @author mr_yi
 * 下午3:57
 */
public class scriptDemo2 {

    public static void main(String[] args) throws Exception{
        Document d = Jsoup.connect("http://car.autohome.com.cn/config/series/2252.html").timeout(5000).get();
        Elements script = d.select("script");
        int  j=0;
        for(Element element : script){
                System.out.println(element.html());
        }

    }


    @Test
    public void autohome(){
//        String seriesId = carSeriesMatchBean.getAutohomeUri("https://car.autohome.com.cn/config/series/650.html");
        String seriesId = "650";
        String chexiUrl = "http://car.autohome.com.cn/" + seriesId + "/";

        Document chexingDoc = Jsoup.connect(chexiUrl).timeout(10000).get();
        Elements yearEles = chexingDoc.select("div.dropdown-content li a[data]");
        if (yearEles != null && yearEles.size() > 0) {
            Iterator<Element> yearIte = yearEles.iterator();
            while (yearIte.hasNext()) {
                Element yearEle = yearIte.next();
                String yearId = yearEle.attr("data");
                if(yearId == null || "".equals(yearId)) {continue;}
                String url = "http://www.autohome.com.cn/ashx/series_allSpec.ashx?s=" + seriesId + "&y=" + yearId;
                String response = HttpHelper.connect(url).get().html();
                System.out.print(response);
            }
        }
    }
}

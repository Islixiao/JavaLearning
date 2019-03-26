package Webmagic.Kt51Animation;

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
 *On 2017/10/24  ***11:53.
 ******/
public class Kt51Animation  {
    private int id;
    private String hahawebname;// 标题
    private String url;// 标题
    private String content;//剧情简介
    private String uptime;//最近动漫更新时间
    private String year;//出品时间
    private String original;//播放状态
    private String language;//对白
    private String tag;//类型
    private String mainrole;//主角


    @Override
    public String toString() {
        return "Kt51Animation{" +
                "id=" + id +
                ", hahawebname='" + hahawebname + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", uptime='" + uptime + '\'' +
                ", year='" + year + '\'' +
                ", original='" + original + '\'' +
                ", language='" + language + '\'' +
                ", tag='" + tag + '\'' +
                ", mainrole='" + mainrole + '\'' +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHahawebname() {
        return hahawebname;
    }

    public void setHahawebname(String hahawebname) {
        this.hahawebname = hahawebname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMainrole() {
        return mainrole;
    }

    public void setMainrole(String mainrole) {
        this.mainrole = mainrole;
    }






}
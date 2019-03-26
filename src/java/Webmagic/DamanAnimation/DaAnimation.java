package Webmagic.DamanAnimation;

/***Created by moyongzhuo
 *On 2017/10/23  ***18:44.
 ******/
public class DaAnimation {
    private     int id;
    private     String damanwebname;//title
    private     String content;//
    private     String imgurl;//imgurl
    private     String url;//
    private     String vediourl;//
    private     String mainrole;//
    private     String area;//
    private     String tag;//
    private     String language;//
    private     String year;


    @Override
    public String toString() {
        return "DaAnimation{" +
                "id=" + id +
                ", damanwebname='" + damanwebname + '\'' +
                ", content='" + content + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", url='" + url + '\'' +
                ", vediourl='" + vediourl + '\'' +
                ", mainrole='" + mainrole + '\'' +
                ", area='" + area + '\'' +
                ", tag='" + tag + '\'' +
                ", language='" + language + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDamanwebname() {
        return damanwebname;
    }

    public void setDamanwebname(String damanwebname) {
        this.damanwebname = damanwebname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVediourl() {
        return vediourl;
    }

    public void setVediourl(String vediourl) {
        this.vediourl = vediourl;
    }

    public String getMainrole() {
        return mainrole;
    }

    public void setMainrole(String mainrole) {
        this.mainrole = mainrole;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }










}

package Webmagic.DouBao.com;

/***Created by moyongzhuo
 *On 2017/10/16  ***11:18.
 ******/
public class Animation {
    private String name;//动画名字
    private String entityUrl;//网址
    private String director;//导演
    private String writer;//编剧
    private String cast;//主演
    private String type;//类型
    private String GuanWang;//官方网址
    private String countryArea;//地区
    private String language;//语言
    private String issue;//上映日期
    private String duration;//片长
    private String imdbNum;//豆瓣连接

    private String ratingNum;//打分10
    private String ranking;//排名
    private String locationName;//当地名字
    private String summariness;//简介
    private String award;//简介


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityUrl() {
        return entityUrl;
    }

    public void setEntityUrl(String entityUrl) {
        this.entityUrl = entityUrl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGuanWang() {
        return GuanWang;
    }

    public void setGuanWang(String guanWang) {
        GuanWang = guanWang;
    }

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImdbNum() {
        return imdbNum;
    }

    public void setImdbNum(String imdbNum) {
        this.imdbNum = imdbNum;
    }

    public String getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(String ratingNum) {
        this.ratingNum = ratingNum;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSummariness() {
        return summariness;
    }

    public void setSummariness(String summariness) {
        this.summariness = summariness;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "Animation{" +
                "name='" + name + '\'' +
                ", entityUrl='" + entityUrl + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", cast='" + cast + '\'' +
                ", type='" + type + '\'' +
                ", GuanWang='" + GuanWang + '\'' +
                ", countryArea='" + countryArea + '\'' +
                ", language='" + language + '\'' +
                ", issue='" + issue + '\'' +
                ", duration='" + duration + '\'' +
                ", imdbNum='" + imdbNum + '\'' +
                ", ratingNum='" + ratingNum + '\'' +
                ", ranking='" + ranking + '\'' +
                ", locationName='" + locationName + '\'' +
                ", summariness='" + summariness + '\'' +
                ", award='" + award + '\'' +
                '}';
    }
}

package Webmagic.Hahadm;

/***Created by moyongzhuo
 *On 2017/10/21  ***20:42.
 ******/
public class IIHahadmAnimation {
    private String hahawebname;// 标题
    private String director;//导演
    private String content;//剧情简介
    private float goal;//评分
    private Integer mentotal;//评分人数
    private int id;




    public String getHahawebname() {
        return hahawebname;
    }

    public void setHahawebname(String hahawebname) {
        this.hahawebname = hahawebname;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getGoal() {
        return goal;
    }

    public void setGoal(float goal) {
        this.goal = goal;
    }

    public Integer getMentotal() {
        return mentotal;
    }

    public void setMentotal(Integer mentotal) {
        this.mentotal = mentotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "IIHahadmAnimation{" +
                "hahawebname='" + hahawebname + '\'' +
                ", director='" + director + '\'' +
                ", content='" + content + '\'' +
                ", goal=" + goal +
                ", mentotal=" + mentotal +
                ", id=" + id +
                '}';
    }




}

package Webmagic.jsoup;

//import com.sun.xml.internal.txw2.DatatypeWriter;
//import com.sun.xml.internal.txw2.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;


/***Created by moyongzhuo
 *On 2017/9/29  ***18:19.
 ******/
public class simpleTest {

    @Test
    public void parser() throws Exception {

        String html = "<html><head><title>开源中国社区</title></head>" +
                "<body><p>这里是 jsoup 项目的相关文章</p></body></html>";
        Document doc = Jsoup.parse(html);
       // System.out.println(doc);

        // 从URL直接加载 HTML 文档
        Document docurl = Jsoup.connect("http://www.oschina.net/").get();
        String title = docurl.title();
        Document docurl2 = Jsoup.connect("http://www.oschina.net/")
                .data("query", "Java")   //请求参数
                .userAgent("I’m jsoup") //设置User-Agent
                .cookie("auth", "token") //设置cookie
                .timeout(3000)           //设置连接超时时间
                .post();                 //使用POST方法访问URL
      //  System.out.println("docurl:  "+docurl);
       // System.out.println("docurl2:  "+docurl2);

        // 从文件中加载 HTML 文档
        File input = new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\jsoup\\jsoup的介绍使用(转) - RY一步一个脚印 - 博客园.html");
        Document docfile = Jsoup.parse(input,"UTF-8","http://www.oschina.net/");
        System.out.println("docfile:  "+docfile);
//请大家注意最后一种 HTML 文档输入方式中的 parse 的第三个参数，为什么需要在这里指定一个网址呢（虽然可以不指定，如第一种方法）？
// 因为 HTML 文档中会有很多例如链接、图片以及所引用的外部脚本、css文件等，而第三个名为 baseURL 的参数的意思就是当 HTML 文档使用相对路径方式引用外部文件时，
// jsoup 会自动为这些 URL 加上一个前缀，也就是这个 baseURL。
    }
    @Test
   public void GetHTML()throws Exception{
       File input = new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\jsoup\\jsoup的介绍使用(转) - RY一步一个脚印 - 博客园.html");
       Document doc = Jsoup.parse(input, "UTF-8", "http://www.oschina.net/");
       Element content = doc.getElementById("content");
       Elements links = content.getElementsByTag("a");
       for (Element link : links) {
           String linkHref = link.attr("href");
           String linkText = link.text();
       }
   }

    @Test
    public void GetOneElements()throws Exception {
        File input = new File("D:\\\\workspace\\\\java\\\\myOwnModlelearing\\\\WebHTTPInterface\\\\src\\\\main\\\\java\\\\jsoup\\\\jsoup的介绍使用(转) - RY一步一个脚印 - 博客园.html");
        Document doc = Jsoup.parse(input,"UTF-8","http://www.oschina.net/");
        Elements links = doc.select("a[href]"); // 具有 href 属性的链接
        Elements pngs = doc.select("img[src$=.png]");//所有引用png图片的元素
        Element masthead = doc.select("div.masthead").first();
        // 找出定义了 class="masthead" 的元素
        Elements resultLinks = doc.select("h3.r > a"); // direct a after h3
        System.out.println(links);
        System.out.println(pngs);
        System.out.println(masthead);
        System.out.println(resultLinks);
        //修改数据，
        doc.select("div.comments a").attr("rel", "nofollow");
//为所有链接增加 rel=nofollow 属性
        doc.select("div.comments a").addClass("mylinkclass");
//为所有链接增加 class="mylinkclass" 属性
        doc.select("img").removeAttr("onclick"); //删除所有图片的onclick属性
        doc.select("input[type=text]").val(""); //清空所有文本输入框中的文本

        //jsoup 在提供强大的 API 同时，人性化方面也做得非常好。在做网站的时候，经常会提供用户评论的功能。
        // 有些用户比较淘气，会搞一些脚本到评论内容中，而这些脚本可能会破坏整个页面的行为，更严重的是获取一些机要信息，例如 XSS 跨站点攻击之类的。

        String unsafe = "<p><a href='http://www.oschina.net/' onclick='stealCookies()'>开源中国社区</a></p>";
        String safe = Jsoup.clean(unsafe, Whitelist.basic());
        // 输出:
        // <p><a href="http://www.oschina.net/" rel="nofollow">开源中国社区</a></p>
    }
















}














/***

@Test
public String Parserxml()throws Exception {
    org.w3c.dom.Node doc = null;
    StringWriter writer = new StringWriter();
    javax.xml.transform.TransformerFactory.newInstance().newTransformer().transform(new javax.xml.transform.dom.DOMSource(doc), new javax.xml.transform.stream.StreamResult(writer));
    return writer.toString();
}

@Test   //com.sun.xml.internal.txw2.Document
public static String XMLtoStr(Document document) {
    String result = null;
    if (document != null) {
        StringWriter strWtr = new StringWriter();
        StreamResult strResult = new StreamResult(strWtr);
        TransformerFactory tfac = TransformerFactory.newInstance();
        try {
            Transformer t = tfac.newTransformer();
            t.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
          //  t.transform(document,strResult);

           // t.transform(new DOMSource(document.getDocumentElement()),strResult);
        } catch (Exception e) {
            System.err.println("XML.toString(Document): " + e);
        }
        result = strResult.getWriter().toString();
    }
    return result;
}

}
 ***/
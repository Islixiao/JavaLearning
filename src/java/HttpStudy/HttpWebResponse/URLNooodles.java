package HttpStudy.HttpWebResponse;

/***Created by moyongzhuo
 *On 2017/9/21  ***17:25.
 ******/
import java.net.MalformedURLException;
import java.net.URL;

public class URLNooodles {

    public static void main(String[] args) throws MalformedURLException {

        String str_url = "http://google.com.hk/search?q=1";

        //1.把字符串封装为URL，会抛出MalformedURLException异常。
        URL url = new URL(str_url);

        System.out.println("getProtocol:"+url.getProtocol());
        System.out.println("getHost:"+url.getHost());
        System.out.println("getPort:"+url.getPort());
        System.out.println("getPath:"+url.getPath());
        //getFile() = getPath() + getQuery
        System.out.println("getFile:"+url.getFile());
        System.out.println("getQuery:"+url.getQuery());

    }

}

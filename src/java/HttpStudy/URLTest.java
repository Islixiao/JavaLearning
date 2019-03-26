/***Created by moyongzhuo
 *On 2017/9/21  ***15:24.
 ******/
/**
 * @FileName: URLTest.java
 * @Package:com.socket
 * @Description: TODO
 * @author: LUCKY
 * @date:2015年12月11日 上午8:48:34
 * @version V1.0
 */
package HttpStudy;
import java.awt.image.ImageProducer;
import java.net.URL;

/**
 * @ClassName: URLTest
 * @Description: TODO
 * @author: LUCKY
 * @date:2015年12月11日 上午8:48:34
 */
public class URLTest {
    public static void main(String[] args) {
        try {
            //根据地址创建
            URL url=new URL("http://ww2.sinaimg.cn/large/70c8c5b7gw1erdli8ka8vj21360m1ju9.jpg");
            //取得信息
            System.out.println(url.getAuthority());
            System.out.println(url.getPath());
            System.out.println(url.getPort());
            System.out.println(url.getDefaultPort());
            System.out.println(url.getFile());
            System.out.println(url.getProtocol());
            Object o=url.getContent();
            if(o instanceof ImageProducer){
                ImageProducer i=(ImageProducer)o;
                System.out.println(i);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

package HttpStudy;

/***Created by moyongzhuo
 *On 2017/9/21  ***15:27.
 ******/

        import java.io.BufferedOutputStream;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.net.URL;
        import java.net.URLConnection;

        import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

/**
 * @ClassName: URLConnectionTest
 * @Description: TODO
 * @author: LUCKY
 * @date:2015年12月11日 上午8:54:24
 */
public class URLConnectionTest {

    public static void main(String[] args) throws Exception {
        try {
            //根据地址创建URL
            URL url=new URL("http://ww2.sinaimg.cn/large/70c8c5b7gw1erdli8ka8vj21360m1ju9.jpg");
            //获得urlConnecion
            URLConnection connection=url.openConnection();
            //打开连接
            connection.connect();
            //取得属性
            System.out.println(connection.getContentLength());
            System.out.println(connection.getContentType());
            System.out.println(connection.getDate());
            System.out.println(connection.getExpiration());
            System.out.println(connection.getLastModified());
            //读取网页数据
            int c;
            InputStream is=connection.getInputStream();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("copy.png"));
            while((c=is.read())!=-1){
                out.write(c);
                System.out.println((char)c);
            }
            out.flush();
            is.close();

        } catch (MalformedURIException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
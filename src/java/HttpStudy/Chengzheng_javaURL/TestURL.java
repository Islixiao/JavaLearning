package HttpStudy.Chengzheng_javaURL;

/***Created by moyongzhuo
 *On 2017/9/21  ***16:08.
 ******/
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class TestURL {
    public static void main(String[] args) throws IOException {
        test4();
        System.out.println("!!!!!!!!!!!!!!!");
        test3();
        System.out.println("!!!!!!!!!!!!!!!");
        test2();
        System.out.println("!!!!!!!!!!!!!!!");
        test();
    }

    /**
     * 获取URL指定的资源。
     *
     * @throws IOException
     */
    public static void test4() throws IOException {
        URL url = new URL("http://lavasoft.blog.51cto.com/attachment/200811/200811271227767778082.jpg");
        //获得此 URL 的内容。
        Object obj = url.getContent();
        System.out.println(obj.getClass().getName());
    }

    /**
     * 获取URL指定的资源
     *
     * @throws IOException
     */
    public static void test3() throws IOException {
        URL url = new URL("http://gc.ditu.aliyun.com/geocoding");
        //返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
        URLConnection uc = url.openConnection();
        //打开的连接读取的输入流。
        InputStream in = uc.getInputStream();
        int c;
        while ((c = in.read()) != -1)
            System.out.println(c);
        in.close();
    }

    /**
     * 读取URL指定的网页内容
     *
     * @throws IOException
     */
    public static void test2() throws IOException {
        URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php");
        //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
        Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
        int c;
        while ((c = reader.read()) != -1) {
            System.out.println((char) c);

        }
        reader.close();
    }

    /**
     * 获取URL的输入流，并输出
     *
     * @throws IOException
     */
    public static void test() throws IOException {
        URL url = new URL("http://lavasoft.blog.51cto.com/62575/120430");
        //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
        InputStream in = url.openStream();
        int c;
        while ((c = in.read()) != -1)
            System.out.println(c);
        in.close();
    }
}
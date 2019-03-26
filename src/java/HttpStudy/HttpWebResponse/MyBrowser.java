package HttpStudy.HttpWebResponse;

/***Created by moyongzhuo
 *On 2017/9/21  ***18:33.
 ******/
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyBrowser {

    public static void main(String[] args) throws  IOException {

        //与服务器建立连接
        Socket s = new Socket("127.0.0.1",8080);

        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        //模仿浏览器向服务器发送数据，下面三行是必须的，其他消息头信息可以根据需要发送，如果不设置，会以默认值发送（未验证）。
        out.println("GET D:\\java\\tomcat8\\apache-tomcat-8.5.16\\webapps\\docs\\aamyWebApp\\mypagetest20170921.html HTTP/1.1");
        out.println("Host: localhost:8090");
        out.println();


        //读取服务器返回的数据并打印
        InputStream in = s.getInputStream();

        byte[] buf = new byte[1024];

        int len = in.read(buf);

        System.out.println(new String(buf,0,len));

        s.close();



    }

}

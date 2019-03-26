package HttpStudy.HttpWebResponse;

/***Created by moyongzhuo
 *On 2017/9/21  ***17:32.
 ******/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//在还在运行的时候访问么？
public class MyHttpServer {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(8090);

        Socket s = ss.accept();

        InputStream in = s.getInputStream();

        byte[] buf = new byte[1024];

        int len = in.read(buf);

        System.out.println(new String(buf,0,len));

        OutputStream out = s.getOutputStream();
        out.write("<font color=red>欢迎访问Http服务器！</font>".getBytes());
        s.close();
        ss.close();
    }
}

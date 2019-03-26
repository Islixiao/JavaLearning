package HttpStudy.NetWork.TCPtest;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:37.
 ******/
import java.net.*;
import java.io.*;


class TransServer {
    public static void main(String[] args) throws IOException,UnknownHostException{
        ServerSocket ss = new ServerSocket(10001);

        Socket s = ss.accept();

        String ip = s.getInetAddress().getHostAddress();

        System.out.println(ip+"........connected");

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        String line = null;

        while((line=bufIn.readLine())!=null){
            System.out.println(line);
            out.println(line.toUpperCase());
        }
        s.close();
        ss.close();
    }

}
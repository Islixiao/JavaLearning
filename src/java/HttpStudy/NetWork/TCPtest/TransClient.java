package HttpStudy.NetWork.TCPtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/***Created by moyongzhuo
 *On 2017/9/22  ***14:35.
 ******/
//客户端输入字母，服务端打印出来，然后把它转换为大写字母返回给客户端。

class TransClient {
    public static void main(String[] args) throws IOException,UnknownHostException {

        Socket s = new Socket("127.0.0.1",10001);

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String line = null;

        while((line=bufr.readLine())!=null){
            if("over".equals(line)){
                break;
            }
            out.println(line);
            String upperStr = bufIn.readLine();
            System.out.println(upperStr);
        }
        s.close();
    }
}

package HttpStudy.NetWork.TCPtest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:30.
 ******/

class TCPClientInteractive {
    public static void main(String[] args)  throws SocketException,IOException {

        //1.建立socekt服务
        Socket client = new Socket(InetAddress.getLocalHost(),10002);

        //2.返回输出数据流
        OutputStream out = client.getOutputStream();
        //PrintWriterpwOut = new PrintWriter(out,true);
        //3.创建传输数据
        String str = "tcp客户端：我来了!"+"\r\n";

        //4.通过IO流的wirte()方法写入传输数据
        out.write(str.getBytes());
        //pwOut.println(str);

        //5.获取输入流,转为缓存字符流
        InputStream in = client.getInputStream();
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));

        //4.获取服务端传输信息并打印
        String info = bufIn.readLine();
        System.out.println(info);


        //5.关闭资源
        client.close();
    }
}
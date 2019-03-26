package HttpStudy.NetWork.TCPtest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:23.
 ******/

        class TCPClientDemo {
               public static void main(String[]args)  throws SocketException,IOException {

        //1.建立socekt服务
        Socket client = new Socket(InetAddress.getLocalHost(),10001);

        //2.返回输出数据流
        OutputStream out =client.getOutputStream();

        //3.创建传输数据
        String str = "tcp客户端：我来了!";

        //4.通过IO流的wirte()方法写入传输数据
        out.write(str.getBytes());

        //5.关闭资源
        client.close();
        }
        }

package HttpStudy.NetWork.TCPtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:25.
 ******/

class TCPServerDemo {
    public static void main (String[] args) throws SocketException,IOException {

        //1.建立socekt服务,监听端口100001
        ServerSocket server = new ServerSocket(10001);

        //2.返回socekt对象
        Socket socket = server.accept();

        //3.获取输入流,转为缓存字符流
        InputStream in = socket.getInputStream();

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));

        //4.获取传输信息并打印
        String info= bufIn.readLine();
        System.out.println(info);

        //关闭资源
        server.close();
    }
}

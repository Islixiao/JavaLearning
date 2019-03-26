package HttpStudy.NetWork.TCPtest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:31.
 ******/

class TCPServerInteractive {
    public static void main (String[] args) throws SocketException,IOException {

        //1.建立socekt服务,监听端口100001
        ServerSocket server = new ServerSocket(10002);

        //2.返回socekt对象
        Socket socket = server.accept();

        //3.获取输入流,转为缓存字符流
        InputStream in = socket.getInputStream();
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));


        //4.获取传输信息并打印
        String info = bufIn.readLine();
        System.out.println(info);

        //5.获取socket的输出流,写入信息
        OutputStream out = socket.getOutputStream();
        String str = "服务端：收到信息"+"\r\n";
        out.write(str.getBytes());


        //6.关闭资源
        socket.close();
        server.close();
    }
}
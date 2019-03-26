package HttpStudy.NetWork.TCPtest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:45.
 ******/
class TextUploadServer {

    public static void main(String[] agrs) throws IOException,SocketException {

        //1.创建soceket服务
        ServerSocket server = new ServerSocket(10007);

        //2.获取socket对象
        Socket socket = server.accept();

        //3.创建上传后目的文件
        File file = new File("C:\\Users\\moyongzhuo\\Desktop\\http协议.txt");

        //4.创建目的文件的输入流
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        //5.获取socket的输入流
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //6.把socket输入流中信息写入文件
        String line = null;
        while((line=bufIn.readLine())!=null){
            //System.out.println(line);
            bw.write(line);
            bw.flush();
            bw.newLine();
        }


        //7.获取socket的输出流，返回上传成功的提示
        OutputStream out = socket.getOutputStream();
        out.write("上传成功".getBytes());

        //8.关闭资源
        bw.close();
        socket.close();
        server.close();

    }
}


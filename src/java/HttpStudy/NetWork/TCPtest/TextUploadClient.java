package HttpStudy.NetWork.TCPtest;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/***Created by moyongzhuo
 *On 2017/9/22  ***14:43.
 ******/
//上传一个文本文件。
//注意点：上传文件中的文本通过readLine()读取到输入流后，其中文本每一行的回车“\n\r“和文本最后的结束标记不会读取进去，
// 所以会造成服务端不能换行和readLine()无法结束，需要PrintWriter的println()方法加入换行和给客户端输出流加入标记，
// 即socket的shutdownOutput()方法，还有即的缓冲流需要刷新，否则无法及时写入。

class TextUploadClient {

    public static void main(String[] agrs) throws IOException,SocketException {

        //1.创建soceket服务
        Socket client = new Socket(InetAddress.getLocalHost(),10007);

        //2.读取上传源文件
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\moyongzhuo\\Desktop\\http协议.txt"));

        //3.获取socket的输出流
        OutputStream out = client.getOutputStream();
        PrintWriter pw = new PrintWriter(out,true);

        //4.读取源文件信息，写入输出流。
        String line = null;
        while((line=br.readLine())!=null){
            //out.write(line.getBytes());
            //System.out.println(line);
            pw.println(line);
        }
        client.shutdownOutput();
        //5.获取输入流，读取服务端的返回信息
        InputStream in = client.getInputStream();
        byte[]buf = new byte[1024];
        int len = in.read(buf);
        String s = new String(buf,0,len);

        System.out.println(s);
    }
}
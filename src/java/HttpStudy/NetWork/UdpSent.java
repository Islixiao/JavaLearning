package HttpStudy.NetWork;

/***Created by moyongzhuo
 *On 2017/9/22  ***11:53.
 ******/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/***Created by moyongzhuo
 *On 2017/9/22  ***11:46.
 ******/
import java.net.*;
import java.io.*;
/***
 *  UDP发送端。
 建立发送端的思路：
 1)        创建socket对象，即DatagramSocket。
 2)        创建需要传输的数据报包，即DatagramPocket对象，分别传入需要传输的数据（字节数组）、字节数组的长度、IP对象、端口。
 3)        调用socket的发送方法，即send()，传入已创建的数据报包。
 4)        关于socket流，即调用close()方法。
 */

class UdpSent {

    public static void main(String[] args) throws IOException,UnknownHostException {

        //创建socket对象
        DatagramSocket ds = new DatagramSocket(8888);

        //发送的信息
        String info= "网络编程,我来了!";

        byte[] buf =info.getBytes();

        //创建发送的数据包对象
        DatagramPacket dp = new DatagramPacket(buf,buf.length, InetAddress.getLocalHost(),10000);

        //通过socket对象发送数据包
        ds.send(dp);

        //关闭socket流对象
        ds.close();
    }
}

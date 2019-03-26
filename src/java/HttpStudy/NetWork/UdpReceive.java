package HttpStudy.NetWork;

/***Created by moyongzhuo
 *On 2017/9/22  ***11:50.
 ******/
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;

/***
 * 建立接收端的思路：
 1)        创建接收端sockect服务。
 2)        创建接收端的数据包。
 3)        通过socket服务的接收方法把数据存储到数据包中。
 4)        通过数据包的方法解析其中的数据
 5)        关闭资源。
***/

/***
 * 注意点：
 1、  DatagramPacket对象构建是需要指定长度的，一般都为发送或者接收的字节数组的长度。
 2、  在服务端中创建DatagramSocket对象，需要指定监听的端口号；接收到的数据包中getPort()方法返回的是发送端主机的端口，即发送端示例中的8888，而不是10000。
 3、  DatagramPacket的内部消息长度值在接收数据后会发生改变，变为实际接收到的数据的长度值
 4、  如果发送端的数据为大量，则可以在服务端通过循环的方式接收，暂时没有学习相关代码。
 */

class UdpReceive{

    public static void main(String[] args)throws IOException,UnknownHostException {

        //创建socket对象,指定监听端口10000
        DatagramSocket ds = new DatagramSocket(10000);
        //定义一个字节数值用来接收信息
        byte[] buf = new byte[1024];
       // System.out.println(dp.getLength());
        //创建接收的数据包对象
        DatagramPacket dp = new DatagramPacket(buf,buf.length);
        System.out.println(dp.getLength());

        //通过socket对象接收数据包
        ds.receive(dp);
        System.out.println(dp.getLength());

        //通过包的方法解析相关信息，比如IP、端口、信息
        String ip =dp.getAddress().getHostAddress();
        int port = dp.getPort();
        String info = new String(dp.getData(),0,dp.getLength());

        //打印接收到的信息
        System.out.println("ip: "+ip+",\n"+"port: "+port+",\n"+"info: "+info);

        //关闭socket流对象
        ds.close();
    }
}

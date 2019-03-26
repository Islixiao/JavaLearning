package HttpStudy.NetWork.DatagramPacketTest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/***Created by moyongzhuo
 *On 2017/9/22  ***12:03.
 ******/

class ChatServer implements Runnable{

    public void run()  {

        try{
            //创建socket对象,指定监听端口10000
            DatagramSocket ds =new DatagramSocket(10000);

            //定义一个字节数值用来接收信息
            byte[] buf = new byte[1024];

            //创建接收的数据包对象
            DatagramPacket dp =new DatagramPacket(buf,buf.length);

            String info = null;
            //通过socket对象接收数据包
            while(true){
                ds.receive(dp);

                info = new String(dp.getData(),0,dp.getLength());

                //打印接收到的信息
                System.out.println(dp.getAddress().getHostAddress()+"接受到的"+": "+info);
            }
        }catch(Exception e){

        }

    }
}

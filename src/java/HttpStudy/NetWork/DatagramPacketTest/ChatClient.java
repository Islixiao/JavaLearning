package HttpStudy.NetWork.DatagramPacketTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/***Created by moyongzhuo
 *On 2017/9/22  ***12:02.
 ******/


class ChatClient implements Runnable {

    public void run() {

        try {
            //创建socket对象
            DatagramSocket ds=new DatagramSocket(8888);
            System.out.println("请输入需要传送的： ");
            //键盘输入流
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

            //读取字符
            String line = null;
            while(true){
                line =br.readLine();


                byte[] buf= line.getBytes();

                //创建发送的数据包对象
                DatagramPacket dp = new DatagramPacket(buf,buf.length, InetAddress.getLocalHost(),10000);

                //通过socket对象发送数据包
                ds.send(dp);

                if(line.equals("886")){
                    break;
                }

            }

            //关闭socket流对象
            ds.close();

        }catch(Exception e){

        }


    }
}
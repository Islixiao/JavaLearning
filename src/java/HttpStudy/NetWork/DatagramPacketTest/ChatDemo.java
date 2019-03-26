package HttpStudy.NetWork.DatagramPacketTest;

/***Created by moyongzhuo
 *On 2017/9/22  ***12:04.
 ******/
class ChatDemo {

    public static void main(String[] main){

        new Thread(new ChatClient()).start();
        new Thread(new ChatServer()).start();

    }
}

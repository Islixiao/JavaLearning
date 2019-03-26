package HttpStudy.NetWork;

/***Created by moyongzhuo
 *On 2017/9/22  ***11:43.
 ******/
import java.net.*;

class IPDemo {

    public static void main(String[] args) throws UnknownHostException{

        //getByName()方法，接收字符串（可以为IP、主机名、域名），返回该IP对象
        InetAddress ip =InetAddress.getByName("www.uestc.edu.cn");
        //getLoaclHost()，返回本机IP
        InetAddress local_ip = InetAddress.getLocalHost();

        //getHostAddress()，返回IP地址的字符串形式，根据DNS解析规则
        String  str_ip = ip.getHostAddress();
        System.out.println(str_ip);
        //getHostName()，返回IP地址的主机名，没有，则返回IP地址
        String str_name =ip.getHostName();
        System.out.println(str_ip);

    }
}

package HttpStudy.JavaNetClass;

/***Created by moyongzhuo
 *On 2017/9/22  ***9:29.
 ******/
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
/***
 * IP地址是IP使用的32位（IPv4）或者128位（IPv6）位无符号数字，它是传输层协议TCP，UDP的基础。
 * InetAddress是Java对IP地址的封装，在java.net中有许多类都使用到了InetAddress，包括ServerSocket，Socket，DatagramSocket等等。
 InetAddress的实例对象包含以数字形式保存的IP地址，同时还可能包含主机名（如果使用主机名来获取InetAddress的实例，
 或者使用数字来构造，并且启用了反向主机名解析的功能）。InetAddress类提供了将主机名解析为IP地址（或反之）的方法。
 InetAddress对域名进行解析是使用本地机器配置或者网络命名服务（如域名系统（Domain Name System，DNS）和网络信息服务（Network Information Service，NIS））来实现。
 对于DNS来说，本地需要向DNS服务器发送查询的请求，然后服务器根据一系列的操作，返回对应的IP地址，为了提高效率，通常本地会缓存一些主机名与IP地址的映射，
 这样访问相同的地址，就不需要重复发送DNS请求了。在java.net.InetAddress类同样采用了这种策略。
 在默认情况下，会缓存一段有限时间的映射，对于主机名解析不成功的结果，会缓存非常短的时间（10秒）来提高性能。
 *
 *static InetAddress[] getAllByName(String host)
 static InetAddress getByAddress(byte[] addr)
 static InetAddress getByAddress(String host,byte[] addr)
 static InetAddress getByName(String host)
 static InetAddress getLocalHost()
在这些静态方法中，最为常用的应该是getByName(String host)方法，只需要传入目标主机的名字，InetAddress会尝试做连接DNS服务器，
并且获取IP地址的操作。代码片段如下，注意我们假设以下的代码，都是默认导入了java.net中的包，在程序的开头加上import java.net.*，
 否则需要指定类的全名java.net.InetAddress。
***/
 public class TestInetAddress    {



    public static void getHostAddressTest() throws UnknownHostException {
        // 输出IPv4地址
        InetAddress ipv4Address1 = InetAddress.getByName("1.2.3.4");
        System.out.println("ipv4Address1: " + ipv4Address1.getHostAddress());
        //ipv4Address1:1.2.3.4
        InetAddress ipv4Address2 = InetAddress.getByName("www.ibm.com");
        System.out.println("ipv4Address2: " + ipv4Address2.getHostAddress());
        //ipv4Address2:129.42.60.216

        InetAddress ipv4Address3 = InetAddress.getByName("www.mysite.com");
        System.out.println("ipv4Address3: " + ipv4Address3.getHostAddress());
        //ipv4Address3:192.168.1.105
        // 输出IPv6地址

        InetAddress ipv6Address1 = InetAddress.getByName("abcd:123::22ff");
        System.out.println("ipv6Address1: " + ipv6Address1.getHostAddress());
        //ipv6Address1:abcd:123:0:0:0:0:0:22ff
        InetAddress ipv6Address2 = InetAddress.getByName("www.uestc.edu.cn");
        System.out.println("ipv6Address2: " + ipv6Address2.getHostAddress());
        //ipv6Address2:2001:da8:9000:b255:210:5cff:fef5:ac49
        // 输出本机全部的IP地址
        InetAddress Addresses[] = InetAddress.getAllByName("XYZ");
        for (InetAddress address : Addresses)
            System.out.println("本机地址：" + address.getHostAddress());
        //本机地址：192.168.1.105
        //本机地址：0.1.0.4
        //本机地址：0:0:0:0:0:0:0:1
    }


    public static void getAddressTest() throws UnknownHostException {
        InetAddress addresslocal=InetAddress.getLocalHost();
        System.out.println("getHostName+addresslocal: "+ addresslocal.getHostName());//返回本机名
        InetAddress addressDNS = InetAddress.getByName("141.146.8.66");
        System.out.println("getHostName+addressDNS: "+ addressDNS.getHostName());//需要访问DNS服务器才能得到域名
        InetAddress addressnoip = InetAddress.getByName("1.2.3.4");//IP地址不存在
        System.out.println("getHostName+addressip: "+ addressnoip.getHostName());//直接返回IP地址
    }
    public static void getCanonicalHostNameTest() throws UnknownHostException {
        InetAddress addresslocal=InetAddress.getLocalHost();
        System.out.println("getHostame+addresslocal: "+ addresslocal.getHostName());//返回本机名
        System.out.println("getCannicalHostName+addresslocal: "+ addresslocal.getCanonicalHostName());//返回主机别名
        InetAddress address126DNS=InetAddress.getByName("ww.126.com");
        System.out.println("getHostame+addresslocal: "+ address126DNS.getHostName());//返回本机名
        System.out.println("getCannicalHostName+addresslocal: "+ address126DNS.getCanonicalHostName());//返回主机别名
    }


    public static void main(String[] args) throws Exception {
        TestInetAddress   inetaddress = new TestInetAddress();
        inetaddress.getCanonicalHostNameTest();
        inetaddress.getAddressTest();
        inetaddress.getHostAddressTest();
    }

}

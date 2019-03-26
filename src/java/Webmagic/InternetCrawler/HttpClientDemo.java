package Webmagic.InternetCrawler;

/***Created by moyongzhuo
 *On 2017/9/26  ***16:30.
 *  Java网络爬虫（一）--HttpClient的使用
 ******/
/**
 * Created by paranoid on 17-1-19.
 */
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class    HttpClientDemo {
    // HttpClient 代表Http客户端
    // HttpEntity 消息载体,发送或者接收消息的载体,可以通过客户端请求或者服务器响应获取实例

    public static void main (String[] args) throws Exception {
        // 1.创建默认的客户端实例,可以理解为我先打开了一个“浏览器”
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2.创建get请求实例,我们请求了一个页面，用的是get方法，对于请求页面使用的是哪一种方法，
        // 一般来说，只有在进行登录页面的时，我们会使用Post方法。
        //1,2.上面两个步骤就是打开一个浏览器，然后在地址栏输入了一个网址。
        HttpGet httpget = new HttpGet("http://www.baidu.com");

        System.out.println("executing request "+httpget.getURI());
        try
        {
            // 客户端执行get请求 返回响应
  // 相当于我们此时在浏览器中按下了回车，URL资源所在的服务器就会开始给你返回这个网页的数据。包括请求头，消息实体等等。
            CloseableHttpResponse response = httpClient.execute(httpget);

            //将返回来的信息进行打印,最后要强调的一点，在使用完HttpClient之后一定记得释放资源，也就是finally区块里面的东西。
            // 服务器响应状态行
            System.out.println(response.getStatusLine().toString());

            Header[] heads = response.getAllHeaders();
            System.out.println(response.getHeaders("Content-Type"));
            // 打印所有响应头
            for(Header h:heads){
                System.out.println("得到的结果为： "+h.getName()+":"+h.getValue());
            }
        } finally {
            httpClient.close();
        }
    }
}

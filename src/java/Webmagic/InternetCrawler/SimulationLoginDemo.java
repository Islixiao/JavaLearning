package Webmagic.InternetCrawler;

/***Created by moyongzhuo
 *On 2017/9/26  ***16:53.
 ******/
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by paranoid on 17-3-27.
 * 模拟登录人人网，之前一直尝试模拟登录百度，发现它需要使用两个技术，一是通过Java代码调用JS
 * 生成随机UID，二是通过抓包得到Pubkey与Rsakey密文根据加密算法得到加密后的密码，最后通过Post
 * 提交给百度服务器，而这两个知识点我还没有学到，所以先给大家模拟登录简单的人人网
 *
 * 提示：我们在模拟登录之前都要先手动登录然后通过抓包查看登录成功需要给对方服务器发送哪些参数，
 * 然后我们将这些参数进行提取，通过Post方法发送给对方服务器
 */

public class SimulationLoginDemo {
    public static void main(String[] args){
        //创建默认客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //创建Post请求实例
        HttpPost httpPost = new HttpPost("http://www.renren.com/");

        //创建参数列表
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("domain", "renren.com"));
        nvps.add(new BasicNameValuePair("isplogin", "true"));
        nvps.add(new BasicNameValuePair("submit", "登录"));
        nvps.add(new BasicNameValuePair("email", ""));
        nvps.add(new BasicNameValuePair("passwd", ""));

        //向对方服务器发送Post请求
        try {
            //将参数进行封装，提交到服务器端
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF8"));
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);

            //如果模拟登录成功
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                Header[] headers = httpResponse.getAllHeaders();
                for (Header header : headers) {
                    out.println("登录成功：  "+header.getName() + ": " + header.getValue());
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();      //释放资源
        }
    }
}

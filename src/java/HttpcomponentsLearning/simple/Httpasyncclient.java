package HttpcomponentsLearning.simple;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/***Created by moyongzhuo
 *On 2017/9/27  ***14:07.
 ******/
public class Httpasyncclient {
    @Test
    public  void oneReuest() {
        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        final HttpGet request = new HttpGet("http://www.apache.org/");
        final Future future = httpClient.execute(request, null);
        try {
            HttpResponse response = (HttpResponse) future.get();
            System.out.println("Response:" + response.getStatusLine());
            System.out.println("Shutting down");
        } catch (Exception ex) {
            Logger.getLogger(Httpasyncclient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                httpClient.close();
            } catch (IOException ex) {
                Logger.getLogger(Httpasyncclient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("执行完毕");
    }
    @Test
    public  void moreRequest(){
        final RequestConfig requestConfitg = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();

        final CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfitg)
                .build();

        httpClient.start();

        final HttpGet[] requests = new HttpGet[]{
                new HttpGet("http://www.apache.org/"),
                new HttpGet("http://www.baidu.com/"),
                new HttpGet("http://www.oschina.net/")
        };

        final CountDownLatch latch = new CountDownLatch(requests.length);
        for(final HttpGet request: requests){

            httpClient.execute(request, new FutureCallback(){
                @Override
                public void completed(Object obj) {
                    final HttpResponse response = (HttpResponse)obj;
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                }

                @Override
                public void failed(Exception excptn) {
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "->" + excptn);
                }

                @Override
                public void cancelled() {
                    latch.countDown();
                    System.out.println(request.getRequestLine() + "cancelled");
                }
            });
        }

        try {
            latch.await();
            System.out.println("Shutting Down");
        } catch (InterruptedException ex) {
            Logger.getLogger(Httpasyncclient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                httpClient.close();
            } catch (IOException ex) {
                Logger.getLogger(Httpasyncclient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Finish!");
    }





}

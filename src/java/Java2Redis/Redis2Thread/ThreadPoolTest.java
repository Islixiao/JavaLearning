package Java2Redis.Redis2Thread;

/***Created by moyongzhuo
 *On 2018/3/6  ***9:38.
 ******/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import redis.clients.jedis.Jedis;


public class ThreadPoolTest {
    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
//      ExecutorService threadPool = Executors.newCachedThreadPool();
//      ExecutorService threadPool = Executors.newSingleThreadExecutor();

        long s = System.currentTimeMillis();
        //模拟1000个线程 与个线程插入1万条数据
        for(int i=1000;i<=1;i++){
            final int task = i;
            threadPool.execute(new Runnable(){
                Jedis jedis = RedisAPI.getPool().getResource();
                @Override
                public void run() {
                    for(int j=0;j<=10000;j++){

                        jedis.set("meters:no:"+task+j, ""+j);
                        System.out.println("meters:no:"+task+j);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }
        threadPool.shutdown();
        long e = System.currentTimeMillis();
        System.out.println("花费时间："+(e-s)/1000);
        System.out.println("all of 10 tasks have committed! ");
    }
}



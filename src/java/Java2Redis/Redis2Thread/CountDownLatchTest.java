package Java2Redis.Redis2Thread;

/***Created by moyongzhuo
 *On 2018/3/6  ***9:44.
 ******/
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import redis.clients.jedis.Jedis;


public class CountDownLatchTest {


    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {
        long s = System.currentTimeMillis();

        // 开始的倒数锁 开始计数
        final CountDownLatch begin = new CountDownLatch(1);


        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(500);


        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(1000);


        for (int index = 0; index < 500; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                Jedis jedis = RedisAPI.getPool().getResource();
                public void run() {
                    try {
                        // 如果当前计数为零，则此方法立即返回。
                        // 等待
                        for(int j=0;j<=2000;j++){



                            jedis.set("meters:no:"+NO+j, ""+j);

//System.out.println("meters:no:"+task+j);
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            // System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
                        }
                        //System.out.println("meters:no:"+task+j);
                        begin.await();
                        // Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + NO + " arrived");
                    } catch (InterruptedException e) {
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        System.out.println(" Start");
        // begin减一，开始游戏
        begin.countDown();
        // 等待end变为0，即所有选手到达终点
        end.await();
        System.out.println(" Over");
        exec.shutdown();
        long e = System.currentTimeMillis();
        System.out.println("花费时间："+(e-s)/1000+" s");
    }
}

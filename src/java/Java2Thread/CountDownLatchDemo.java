package Java2Thread;

/***Created by moyongzhuo
 *On 2018/3/21  ***13:58.
 ******/
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
//CountDownLatch----很适合用来将一个任务分为n个独立的部分，等这些部分都完成后继续接下来的任务
//隶属于java.util.concurrent包。CountDownLatch类是一个同步计数器,
// 构造时传入int参数,该参数就是计数器的初始值，
// 每调用一次countDown()方法，计数器减1,
// 计数器大于0 时，await()方法会阻塞程序继续执行.
// 当多个线程达到预期时（latch.countDown()），唤醒多个其他等待中的线程，即执行latch.await()后面的代码。
// 样例是，张三、李四合作完成任务，张三5秒，李四8秒，当张三李四都完成后，总任务结束。代码如下：
public class CountDownLatchDemo {
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);//两个工人的协作
        Worker worker1 = new Worker("张三", 5000, latch);
        Worker worker2 = new Worker("李四", 8000, latch);
        worker1.start();
        worker2.start();
        latch.await();//阻塞！等待所有工人完成工作
        System.out.println("all work done at " + sdf.format(new Date()));
    }

    static class Worker extends Thread {
        String workerName;
        int workTime;
        CountDownLatch latch;

        public Worker(String workerName, int workTime, CountDownLatch latch) {
            this.workerName = workerName;
            this.workTime = workTime;
            this.latch = latch;
        }

        public void run() {
            System.out.println("Worker " + workerName + " do work begin at " + sdf.format(new Date()));
            doWork();//工作了
            System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
            latch.countDown();//工人完成工作，计数器减一

        }

        private void doWork() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

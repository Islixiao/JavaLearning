package Java2Thread.AtomicInterger;

/***Created by moyongzhuo
 *On 2018/3/29  ***15:11.
 ******/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntValueTest {

    public static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 4; j++) {
                    System.out.println(value++);
                }
            });
        }
        executorService.shutdown();
        Thread.sleep(3000);
        System.out.println("最终结果是" + value);
    }
}

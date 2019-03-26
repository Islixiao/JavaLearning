package Java2JDK_JVM.JVM;

/***Created by moyongzhuo
 *On 2018/3/29  ***13:08.
 ******/
/**
 * 测试内容：栈溢出测试（递归调用导致栈深度不断增加）
 *
 * 虚拟机参数：-Xss128k
 * Java虚拟机规范中描述了如果线程请求的栈深度太深（换句话说方法调用的深度太深），就会产生栈溢出了。
 * 那么，我们只要写一个无限调用自己的方法，自然就会出现方法调用的深度太深的场景了。
 *
 *
 * 通过不断创建线程的方式可以产生OutOfMemoryError，因为每个线程都有自己的栈空间。
 * 不过这个操作有危险就不做了，原因是Windows平台下，Java的线程是直接映射到操作系统的内核线程上的，如果写个死循环无限产生线程，那么可能会造成操作系统的假死。

 *上面无限产生线程的场景，从另外一个角度说，就是为每个线程的栈分配的内存空间越大，反而越容易产生内存溢出。
 * 其实这也很好理解，操作系统分配给进程的内存是有限制的，比如32位的Windows限制为2GB。
 * 虚拟机提供了了参数来控制Java堆和方法区这两部分内存的最大值，剩余内存为2GB-最大堆容量-最大方法区容量，
 * 程序计数器很小就忽略了，虚拟机进程本身的耗费也不算，剩下的内存就是栈的了。
 * 每个线程分配到的栈容量越大，可建立的线程数自然就越少，建立线程时就越容易把剩下的内存耗尽。

 *StackOverFlowError这个异常，有错误堆栈可以阅读，比较好定位。
 * 而且如果使用虚拟机默认参数，栈深度在大多数情况下，达到1000~2000完全没有问题，正常方法的调用这个深度应该是完全够了。
 * 但是如果建立过多线程导致的OutOfMemoryError，在不能减少线程数或者更换64位虚拟机的情况下，就只能通过减小最大堆容量和减小栈容量来换取更多的线程了。
 */
public class StackOverflowTest {
    private int stackLength = 1;

    public void stackLeak()
    {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        StackOverflowTest stackOverflow = new StackOverflowTest();
        try {
            stackOverflow.stackLeak();
        }
        catch (Throwable e) {
            System.out.println("stack length:" + stackOverflow.stackLength);
            throw e;
        }
    }
}
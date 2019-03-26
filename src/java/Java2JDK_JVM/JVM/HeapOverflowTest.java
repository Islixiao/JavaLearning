package Java2JDK_JVM.JVM;

/***Created by moyongzhuo
 *On 2018/3/29  ***13:03.
 ******/
import java.util.ArrayList;
import java.util.List;

/**
 * 测试内容：堆溢出
 *
 * 虚拟机参数：-Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * Java堆唯一的作用就是存储对象实例，只要保证不断创建对象并且对象不被回收，那么对象数量达到最大堆容量限制后就会产生内存溢出异常了。
 * 所以测试的时候把堆的大小固定住并且让堆不可扩展即可。
 */
public class HeapOverflowTest {
    public static void main(String[] args) {
        List<HeapOverflowTest> list = new ArrayList<HeapOverflowTest>();
        while (true)
        {
            list.add(new HeapOverflowTest());
        }
    }
}

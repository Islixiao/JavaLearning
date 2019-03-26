package Java2JDK_JVM.JVM;

/***Created by moyongzhuo
 *On 2018/3/29  ***13:12.
 ******/
import java.util.ArrayList;
import java.util.List;

/**
 * 测试内容：常量池溢出（这个例子也可以说明运行时常量池为方法区的一部分）
 *
 * 虚拟机参数-XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * 运行时常量池也是方法区的一部分，所以这两个区域一起看就可以了。
 * 这个区域的OutOfMemoryError可以利用String.intern()方法来产生。
 * 这是一个Native方法，意思是如果常量池中有一个String对象的字符串就返回池中的这个字符串的String对象；
 * 否则，将此String对象包含的字符串添加到常量池中去，并且返回此String对象的引用。
 *
 * 之前有讲过，对于HotSpot而言，方法区=永久代，这里看到OutOfMemoryError的区域是“PermGen space”，
 * 即永久代，那其实也就是方法区溢出了。
 * 注意一下JDK1.7下是不会有这个异常的，while循环将一直下去，因为JDK1.7之后溢出了永久代并采用Native Memory来实现方法区的规划了。
 */
public class ConstantPoolOverflowTest {
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
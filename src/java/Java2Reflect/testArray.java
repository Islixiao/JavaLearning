package Java2Reflect;

import org.junit.Test;

import java.lang.reflect.Array;

/***Created by moyongzhuo
 *On 2018/3/20  ***9:20.
 ******/
public class testArray {
    @Test
    public void testArray() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls,25);
        //往数组里添加内容
        Array.set(array,0,"hello");
        Array.set(array,1,"Java");
        Array.set(array,2,"fuck");
        Array.set(array,3,"Scala");
        Array.set(array,4,"Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array,3));
    }
}

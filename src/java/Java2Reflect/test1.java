package Java2Reflect;

/***Created by moyongzhuo
 *On 2018/3/20  ***9:08.
 ******/
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class test1 {
    @Test
    public void test() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> c = methodClass.class;
        Object object = c.newInstance();//通过反射来生成对象主要有两种方式。（1）使用Class对象的newInstance()方法来创建Class对象对应类的实例。
        Method[] methods = c.getMethods();//getMethods()方法返回某个类的所有公用（public）方法，包括其继承类的公用方法。


        Method[] declaredMethods = c.getDeclaredMethods();//获取方法
        //获取某个Class对象的方法集合，主要有以下几个方法：
        //getDeclaredMethods()方法返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
        //获取methodClass类的add方法

        Method method = c.getMethod("add", int.class, int.class);
        //getMethods()方法获取的所有方法
        System.out.println("getMethods获取的方法：");
        for(Method m:methods)
            System.out.println(m);
        //getDeclaredMethods()方法获取的所有方法
        System.out.println("getDeclaredMethods获取的方法：");
        for(Method m:declaredMethods)
            System.out.println(m);
    }
}



class methodClass {
    public final int fuck = 3;
    public int add(int a,int b) {
        return a+b;
    }
    public int sub(int a,int b) {
        return a+b;
    }
}

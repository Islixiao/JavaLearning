package Java2Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***Created by moyongzhuo
 *On 2018/3/20  ***9:17.
 ******/
public class test2 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> klass = methodClass.class;
        //创建methodClass的实例
        Object obj = klass.newInstance();
        //获取methodClass类的add方法
        Method method = klass.getMethod("add",int.class,int.class);
        //调用method对应的方法 => add(1,4)
        //调用方法,当我们从类中获取了一个方法后，我们就可以用invoke()方法来调用这个方法。invoke方法的原型为
        Object result = method.invoke(obj,1,4);
        System.out.println(result);
    }
}

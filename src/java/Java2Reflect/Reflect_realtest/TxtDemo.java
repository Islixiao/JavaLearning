package Java2Reflect.Reflect_realtest;

/***Created by moyongzhuo
 *On 2018/3/20  ***15:24.
 ******/
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/*
 * 我们利用反射和配置文件，可以使：应用程序更新时，对源码无需进行任何修改
 * 我们只需要将新类发送给客户端，并修改配置文件即可
 */
public class TxtDemo {
    public static void main(String[] args) throws Exception {
        //通过反射获取Class对象
        Class stuClass = Class.forName(getValue("className"));//"cn.fanshe.Student"
        //2获取show()方法
        Method m = stuClass.getMethod(getValue("methodName"));//show
        //3.调用show()方法
        m.invoke(stuClass.getConstructor().newInstance());

    }

    //此方法接收一个key，在配置文件中获取相应的value
    public static String getValue(String key) throws IOException{
//        String path = TxtDemo.class.getResource("TxtDemo.txt").getPath();
        Properties pro = new Properties();//获取配置文件的对象
        FileReader in = new FileReader(TxtDemo.class.getResource("/TxtDemo.txt").getPath());//获取输入流
        //"src\\main\\resources"
        pro.load(in);//将流加载到配置文件对象中
       in.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }
}

package Java2ML.CSDN_Baidu_Learning;

import org.junit.Test;

/***Created by moyongzhuo
 *On 2018/2/11  ***10:28.
 ******/
public class Changeable {
    public int change(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        return a;
    }
    @Test
    public void exchange(){
        int a = 12;
        int b = 13;
        int c = change(a,b);
        System.out.println(c);
        System.out.println(b);

    }
}

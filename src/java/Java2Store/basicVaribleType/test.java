package Java2Store.basicVaribleType;

import org.junit.Test;

import java.util.Random;

/***Created by moyongzhuo
 *On 2018/5/16  ***15:34.
 ******/
public class test {

    //    第一个例子：基本类型,string,stringBuuilder没有被改变
    public void foo(int value) {
        value = 100;
    }

    void foo1(String text) {
        text = "windows";
    }

    void foo2(StringBuilder builder) {
        builder.append("4");
    }

    @Test
    public void test1() {
        int num = 10;
        String str = "NULL110";
        String str2 = "NULL110";
        StringBuilder sb = new StringBuilder("iphone");

        foo(num); // num 没有被改变
        foo1(str); // str 也没有被改变
        foo2(sb);
        int i = 0;
    }
    @Test
    public void randomGet3() {
        int number = new Random().nextInt(1) + 1;
        System.out.println("Random().nextInt() * 10"+"\t\t"+number);
        Double numberv = Math.random() * 3;
        System.out.println("Math.random() * 10"+"\t\t"+ (int)(double)numberv);
        //10以内的随机数
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int i1 = random.nextInt(2)+1;
            System.out.println(i1);
        }
    }


}

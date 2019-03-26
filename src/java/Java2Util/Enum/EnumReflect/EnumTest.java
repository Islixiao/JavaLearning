package Java2Util.Enum.EnumReflect;

import Java2Util.Enum.Color;
import Java2Util.Enum.ColorTest;

import java.util.Arrays;

/***Created by moyongzhuo
 *On 2018/5/10  ***13:12.
 ******/
public class EnumTest {
    public static void main(String[] args) throws Exception{
        EnumBuster<Color> buster = new EnumBuster<Color>(Color.class,ColorTest.class);
        Color ANGRY = buster.make("ANGRY");
        buster.addByValue(ANGRY);
        System.out.println(Arrays.toString(Color.values()));
//        Color.ANGRY;
        ColorTest color = new ColorTest();
        color.sing(ANGRY);
    }
}

package InterfaceLearing;


//import static java.lang.Math.PI;
//Error:java: Compilation failed: internal java compiler error,项目中有人用jdk1.6  有人用jdk1.7  版本不一样  会一起这个错误
/***Created by moyongzhuo
 *On 2017/9/19  ***17:42.
 ******/
public class Cire implements CalInterface
{
    public float getArea(float r)
    {
        float area=PI*r*r;//计算圆面积并赋值给变量area
        return area;//返回计算后的圆面积
    }
    public float getCircumference(float r)
    {
        float circumference=2*PI*r;      //计算圆周长并赋值给变量circumference
        return circumference;           //返回计算后的圆周长
    }
    public static void main(String[] args)
    {
        Cire c = new Cire();
        float f = c.getArea(2.0f);
        System.out.println(Float.toString(f));
    }
}

package Java2ML.ML;


import org.junit.Test;

import static java.lang.Math.pow;

/***Created by moyongzhuo
 *On 2017/11/8  ***10:01.
 ******/
public class Linner{

    @Test
    public  void Linner(){
        double xx1 = 1,yy1 = 4;
        double xx2 = 2,yy2 = 6.5;
        double xx3 = 3,yy3 = 9;
        double a = 0, b = 0;
        double x_ = (xx1+xx2+xx3)/3;
        double y_ = (yy1+yy2+yy3)/3;
        double xx = (xx1-x_)*(yy1-y_)+(xx2-x_)*(yy2-y_)+(xx3-x_)*(yy3-y_);
        double yy = pow((xx1-x_),2)+ pow((xx2-x_),2)+ pow((xx3-x_),2);
        a =xx/yy;
        b = y_ - a*x_;
        System.out.println("a: "+a);
        System.out.println("b: "+b);
        System.out.println(a*5 + b);
    }






}


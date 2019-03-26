package Webmagic.AutoHome;

/***Created by moyongzhuo
 *On 2018/5/15  ***16:10.
 ******/

/*
按字符串长度排序

*/
import java.util.*;
class  sortLetter
{
    public static void main(String[] args)
    {
        TreeSet ts = new TreeSet(new MyStringLength());
        ts.add("abcde");
        ts.add("ab");
        ts.add("abc");
        ts.add("a");
        ts.add("abcd");
        ts.add("1");

        Iterator it = ts.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next());
        }
    }
}

class MyStringLength implements Comparator
{
    //重写 compare 方法
    public int compare(Object obj1,Object obj2)
    {
        String str1 = (String)obj1;
        String str2 = (String)obj2;
        /*
        return str1.length() - str2.length();
        如果这样直接返回长度的值，这样会把相同长度的数据简略掉。
        */
        int num =  str1.length() - str2.length();
        if(num==0)
        {
            return str1.compareTo(str2);    //如果长度相同比较大小。
        }
        return num;
    }
}
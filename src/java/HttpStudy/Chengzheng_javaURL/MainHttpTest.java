package HttpStudy.Chengzheng_javaURL;

/***Created by moyongzhuo
 *On 2017/9/21  ***16:37.
 ******/
/***
String类的方法：
        ①利用运算符"+"
        ②public String concat(String str)进行字符串的拼接操作
        StringBuffer的方法：
        ①public StringBuffer append(String str)将str添加到当前字符串缓冲区的字符序列的末尾
        ②public StringBuffer insert(int offset,String str)在当前字符串缓冲区的字符序列的下标
        索引offset插入str。如果offset等于旧长度，则str添加在字符串缓冲区的尾部
***/
import java.net.URL;
public class MainHttpTest {
    public static void main(String[] args) {
        String host = "www.java2s.com";
        String file = "/index.html";
        String[] schemes = {"http", "https", "ftp", "mailto", "telnet", "file", "ldap", "gopher",
                "jdbc", "rmi", "jndi", "jar", "doc", "netdoc", "nfs", "verbatim", "finger", "daytime",
                "systemresource"};
        //String str = host.concat(file);
      //循坏，数组用
       // for(char c:str2.toCharArray()){	//用循环把值一个一个取出
           // str3=c+str3;	//一个一个插进新的字符串中
        for (int i = 0; i < schemes.length; i++) {
            try {
                String str = schemes + host + file;
                System.out.println(str);
                URL u = new URL(str);
                System.out.println(schemes + " is supported/r/n");
            } catch (Exception ex) {
                System.out.println(schemes + " is not supported/r/n");
            }
        }
    }
}
package HttpStudy.Chengzheng_javaURL;

/***Created by moyongzhuo
 *On 2017/9/21  ***17:08.
 ******/
//转码
/***
java.net.URLDecoder.decode(String s,String enc);
        将application/x-www-form-urlencoded字符串转换成普通字符串。
        java.net.URLEncoder.decode(String s,String enc);
        将普通字符串转换成application/x-www-form-urlencoded字符串
 ***/

import java.net.URLDecoder;

import java.net.URLEncoder;

public class URLDecoderTest {

    public static void main(String[] args) throws Exception {

        //将application/x-www-form-urlencoded字符串

        //转换成普通字符串

        //必须强调的是编码方式必须正确，如baidu的是gb2312，而google的是UTF-8

        String keyWord = URLDecoder.decode("%E6%96%87%E6%A1%A3", "UTF-8");

        System.out.println("谷歌UTF-8: "+keyWord);



        //将普通字符串转换成

        //application/x-www-form-urlencoded字符串

        //必须强调的是编码方式必须正确，如baidu的是gb2312，而google的是UTF-8

        String urlStr = URLEncoder.encode("文档:  ", "gb2312");

        System.out.println("百度gb2312:  "+urlStr);

    }

}

package Webmagic.BaiduAnimation;

/***Created by moyongzhuo
 *On 2017/10/19  ***19:28.
 ******/

/**
 * 测试拼音转化结果
 * @author liuyazhuang
 *
 */
public class PinyinTest {
    public static void main(String[] args) throws Exception{
        PinyinTool tool = new PinyinTool();
        String pinyin = "}<>;'|])#@!%^&*  ><山 田君  与 7位魔女";
        String pinyin11 = pinyin.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
        String pinyin111 = pinyin11.replaceAll( " " , "");
        String pinyin1 = tool.toPinYin(pinyin11, "", PinyinTool.Type.LOWERCASE);
        System.out.println(pinyin1);
        System.out.println("刘亚壮的运行测试结果为====" + tool.toPinYin(pinyin111, "", PinyinTool.Type.LOWERCASE));
    }
}

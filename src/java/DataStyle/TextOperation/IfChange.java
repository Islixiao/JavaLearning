package DataStyle.TextOperation;

/***Created by moyongzhuo
 *On 2017/10/17  ***17:19.
 ******/
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class IfChange {
    ChangeHS changehs = new ChangeHS();
    @Test
    public void ReadChange()throws IOException{
        String filePath = "C:\\Users\\moyongzhuo\\Desktop\\newsentry.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("gbk")));
        PrintWriter out = new PrintWriter(new File("C:\\Users\\moyongzhuo\\Desktop\\test.txt"));
        String str = null;
        while((str = br.readLine())!=null) {
            String st =str;
            char c =' ';
            String st1 = changehs.RemoveSpace(st, c);
            String []ss = st1.split("》");//按照指定字符将字符串分割，并将分割后的字符串放入数组中
            for(String s:ss)
                out.write(s+"》"+"\r\n");//在子字符串后加上‘}’和换行符并输出文本
            System.out.println(ss.length);
        }
        br.close();
        out.close(); //关闭写入的文
        }


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("C:\\Users\\moyongzhuo\\Desktop\\newsentry.txt"));
        //读取该地址下的input.txt文件
        String str = in.nextLine();
        System.out.println("str: " + str);
        System.out.println(" in: "+in.toString());
        PrintWriter out = new PrintWriter(new File("C:\\Users\\moyongzhuo\\Desktop\\test.txt"));
        //将处理后的文件output.txt创建到该地址下
        while(in.hasNext()){
            System.out.println("str: " + str);
            //String str= "《西游记》  《小鲤鱼历险记》  《五子说》  《哪吒传奇》  《千千问》  《围棋少年》  《天上掉下个猪八戒》  《黑客阻击手》  《色啦》  《三毛流浪记》  《中华勤学故事》  《唐诗故事》  《大耳朵图图》  《梦里人》  《猫咪小贝》  《中华勤学故事》  《黑猫警长》  《蓝猫淘气3000问》  《乐园双宝：游游和非非》  《丁丁战猴王》  《哈哈镜花缘》  《中华美德》  《济公斗蟋蟀》  《蓝皮鼠大脸猫》  《自古英雄出少年》  《人参娃娃》  《魔方大厦》  《大英雄狄青》  《猪八戒吃西瓜》  《小胶水》  《宝莲灯》  《张飞审瓜》  《熊猫京京》  《阿凡提的故事》  《没头脑和不高兴》  《大草原上的小老鼠》  《小蝌蚪找妈妈》  《善良的夏吾冬》  《大头儿子和小头爸爸》  《鹬蚌相争》  《假如我是武松》  《果实》  《三个和尚》  《蝴蝶泉》  《海尔兄弟》  《雪孩子》  《蛐蛐》  《开心街》  《骄傲的将军》  《天上掉下个猪八戒》  《兰花花》  《渔童》  《天眼》  《麻雀选大王》  《崂山道士》  《西游漫记》  《十二生肖》  《九色鹿》  《三毛流浪记》  《舒克和贝塔》  《过猴山》  《魔豆传奇》  《太空特警》  《淘气的金丝猴》  《天降小子》  《太阳之子》  《动画迷宫》  《森林一族》  《未来百年》  《巨蛋之迷》  《神龙卫士》  《新三字经》  《猴子点鞭炮》  《神医华佗》  《音乐船》  《浑元》  《周处除三害》  《月亮街》  《夹子救鹿》  《猫咪的胡子》  《知识老人》  《娇娇和晶晶》  《阿笨猫》  《白雪公主与青蛙王子》  《快乐家家车》  《宝贝疙瘩叮呱呱》  《城市野战排》  《蚂蚁与大象》  《封神榜传奇》  《关东三宝》  《奇奇漂流记》  《积木鸡》  《倔强的凯拉班》  《强者上钩》  《杰杰熊与迪迪鸟》  《理发师》  《人参王国》  《毛毛猴》  《南极特使》  《三只小狐狸》  《哪吒闹海》  《牛牛和西西》  《抬驴》  《玩具之家》  《日月潭》  《特别车队》  《小贝流浪记》  《神马与腰刀》  《小糊涂神》  《不怕冷的大衣》  《我们的家园》  《小精灵灰豆》  《小老虎康康》  《小虎还乡》  《隐身探长》  《小神仙和小仙女》  《妖树与松鼠》  《巴布卡》  《小猪哼哼》  《蚁王火柴头》  《方脸爷爷圆脸奶奶》  《牙刷家族》  《雨中情》  《济公》  《大战千年虫》  《怪城历险记》  《老鼠嫁女》  《菲菲的行业》  《亮亮看海》  《草人》  《十二全家福的神奇世界》  《千千问》  《唐诗故事》  《小号手》  《栗子狗和香蕉狐》  《小草帽》  《小熊猫学木匠》  《宝葫芦的秘密》  《可可可心一家人》  《熊猫百货商店》  《鸭子侦探》  《西西瓜瓜历险记》  《快乐的数字》  《天才发明家》  《小不点》  《警犬救护队》  《色啦》  《鲁彪和小猫》  《太空嬉哈族》  《汪汪探长》  《梦里人》  《牛奶将军》  《围棋少年》  《斑马人的故事》  《小恐龙寻根历险记》  《无声虫》  《老箱子》  《熊猫小胖》  《父与子》  《卡通娃》  《三十六计》  《妮妮画猴》  《太空饭店》  《辣椒先生》  《星星探长》  《旱牛与牧童》  《葫芦小金刚》  《守株待兔》  《猴子钓鱼》  《七色花》  《聪明的笨小猪》  《南辕北辙》  《笨笨蛋蛋与飞机》  《孙小圣与猪小能》《笨笨蛋蛋在海上》  《快乐的小松鼠》";
            //按行读取，遇到换行符停止。将读取到的内容赋值到str中
            str.replace(" ","");
            //慎用！将文本中的空格去掉，本例操作的是css代码，单句中的空格非必要，故可去掉
            String []ss = str.split("》");//按照指定字符将字符串分割，并将分割后的字符串放入数组中
            for(String s:ss)
                out.write(s+"》"+"\r\n");//在子字符串后加上‘}’和换行符并输出文本
            System.out.println(ss.length);
        }
        in.close();
        out.close(); //关闭写入的文本
    }
}

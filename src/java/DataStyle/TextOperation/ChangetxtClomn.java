package DataStyle.TextOperation;

/***Created by moyongzhuo
 *On 2017/10/17  ***17:16.
 ******/
import java.io.FileWriter;
import java.io.IOException;
public class ChangetxtClomn {
    public static void main(String[] args) throws IOException{
        //\r\n为换行符
        FileWriter fw = new FileWriter("C:\\Users\\moyongzhuo\\Desktop\\test.txt");
        //写入第一行换行
        fw.write("\r\n");
        //或者获得系统换行符
        String str = "" + System.getProperty("line.separator");
        fw.write(str);
        fw.write("");
        fw.close();
        /*
         * windows下的文本文件换行符:\r\n linux/unix下的文本文件换行符:\r
         * Mac下的文本文件换行符:\n
         */
    }
}

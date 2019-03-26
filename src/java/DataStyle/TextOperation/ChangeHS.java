package DataStyle.TextOperation;

/***Created by moyongzhuo
 *On 2017/10/17  ***16:36.
 ******/
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ChangeHS {
    @Test
    public void reader() throws IOException {
        File file = new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\TextOperation\\urlLtRole.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("gbk")));
        List list = new ArrayList();
        String s = null;
        while ((s = br.readLine()) != null) {
            list.add(s);
        }

        List<String> list1 = list;
        for (String item : list1) {
            String[] tmp = item.split("\t");
            System.out.println("\n");
        }
        String path = "C:\\Users\\moyongzhuo\\Desktop\\test.txt";
        PrintWriter pw = new PrintWriter(new File(path));
        for (int i = 0; i < list.size(); i++)
        {
        }
    }
    @Test
   public void deleteSpace()throws IOException{
        String filePath = "C:\\Users\\moyongzhuo\\Desktop\\test.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),Charset.forName("utf-8")));
        String str = null;
        while((str = br.readLine())!=null) {
            String s =str;
            s.replace("\r","");
            s.replace("\t","");
            System.out.println(s);
        }
    }


    public String  RemoveSpace(String resource,char ch)
    {
        StringBuffer buffer=new StringBuffer();
        int position=0;
        char currentChar;
        while(position<resource.length())
        {
            currentChar=resource.charAt(position++);
            //如果当前字符不是要去除的字符，则将当前字符加入到StringBuffer中
            if(currentChar!=ch)
                buffer.append(currentChar);
        }
        return buffer.toString();
    }
    @Test
        public  void TestRemovSpace(){
            String str= "afdsfdf 了   2012   0407 Alamps 老师";
            char c =' ';
            RemoveSpace(str, c);
            System.out.println("结果为：" +  RemoveSpace(str, c));
    }

}


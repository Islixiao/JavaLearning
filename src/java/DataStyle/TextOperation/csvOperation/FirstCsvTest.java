package DataStyle.TextOperation.csvOperation;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/17  ***19:23.
 ******/
public class FirstCsvTest {
    public static void main(String[] args)
    {
        File csv = new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort.csv");  // CSV文件路径
       // File csv = new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort_cast.csv");  // CSV文件路径

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), Charset.forName("utf-8")));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数："+allString.size());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

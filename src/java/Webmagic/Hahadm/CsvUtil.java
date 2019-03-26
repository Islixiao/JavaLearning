package Webmagic.Hahadm;

import com.csvreader.CsvReader;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/22  ***6:51.
 ******/
public class CsvUtil {
    List<String> list = null;
    public String csvFilePath = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\Webmagic\\Hahadm\\15000allAnimation.csv";
    @Test
    public List readCSV() throws Exception{
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            // String csvFilePath = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort.csv";
           // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                //System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();

            // 遍历读取的CSV文件
            for (int row = 0; row < 16000; row++) {
                // 取得第row行第0列的数据
                String cell = csvFileList.get(row)[20];
                System.out.println("------------>" + cell);
                list.add(cell);
                return list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  list;
    }


    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\Webmagic\\Hahadm\\douban_animation_hot_cleancast.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分

                String last = item[item.length-(item.length-4)];//这就是你要的数据了
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.println(last);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 /*   public static void main(String[] args) throws Exception{
        List<String> list = null;
        CsvUtil mkm = new CsvUtil();
        mkm.readCSV();
    }*/


}

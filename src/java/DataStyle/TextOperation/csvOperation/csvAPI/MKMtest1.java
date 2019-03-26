package DataStyle.TextOperation.csvOperation.csvAPI;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/***Created by moyongzhuo
 *On 2017/10/17  ***20:00.
 ******/
public class MKMtest1 {
    List<String> list = null;
    public List readCSV() {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
           // String csvFilePath = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort.csv";
            String csvFilePath = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort_cast.csv";
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
             reader.close();

            // 遍历读取的CSV文件
            for (int row = 0; row < 160000; row++) {
                // 取得第row行第0列的数据
                String cell = csvFileList.get(row)[2];
                System.out.println("------------>" + cell);
                list.add(cell);
                return list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  list;
    }

@Test
    public void writeCSV(List<String> li) {
        // 定义一个CSV路径
        String csvFilePath = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\csvAPI\\name.csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
           // String[] csvHeaders = { "编号", "姓名", "年龄" };
            String[] csvHeaders = { "姓名" };
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            /***
             *

            for (int i = 0; i < 20; i++) {
              //  String[] csvContent = { i + "000000", "StemQ", "1" + i };
                String[] csvContent = { cell };
                csvWriter.writeRecord(csvContent);
            }
            ***/
            for (Object l :li){
                //  String[] csvContent = { i + "000000", "StemQ", "1" + i };
                if(l==null)continue;
                String aa = String.valueOf(l);
                String[] csvContent = { aa };
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        List<String> list = null;
        MKMtest1 mkm = new MKMtest1();
        mkm.readCSV();
        mkm.writeCSV(list);
    }



}

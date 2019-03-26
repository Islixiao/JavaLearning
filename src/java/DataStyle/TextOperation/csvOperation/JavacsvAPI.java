package DataStyle.TextOperation.csvOperation;

import java.io.IOException;
import java.nio.charset.Charset;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.Test;

/***Created by moyongzhuo
 *On 2017/10/17  ***19:35.
 ******/
public class JavacsvAPI {
    public static void read(){

        String filePath = "XXX.csv";

        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath);

            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                // 读一整行
                System.out.println(csvReader.getRawRecord());
                // 读这行的某一列
                System.out.println(csvReader.get("Link"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public static void write(){

        String filePath = "/Users/dddd/test.csv";

        try {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("GBK"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头
            String[] headers = {"编号","姓名","年龄"};
            String[] content = {"12365","张山","34"};
            csvWriter.writeRecord(headers);
            csvWriter.writeRecord(content);
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

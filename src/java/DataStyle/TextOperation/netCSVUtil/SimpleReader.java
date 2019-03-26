package DataStyle.TextOperation.netCSVUtil;

/***Created by moyongzhuo
 *On 2017/10/17  ***20:46.
 ******/
import com.csvreader.CsvWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class SimpleReader {

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new   FileReader("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort.csv"));
        BufferedReader br = new BufferedReader(new FileReader("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\MKM_Clean.clean_douban_new_animation_sort.csv"));
        List<String> list = new ArrayList<>();
        ;
        String line;
        while ((line = br.readLine()) != null) {
            String name = line.split(",")[19];
            System.out.println(line.split(",")[19]);
            list.add(name);
            String filePath = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\DataStyle\\TextOperation\\csvOperation\\csvAPI\\name.csv";
                // 创建CSV写对象
                CsvWriter csvWriter = new CsvWriter(filePath, ',', Charset.forName("utf-8"));
                // 写表头
                String[] headers = {"姓名"};
                String[] content = {name};
                csvWriter.writeRecord(headers);
                csvWriter.writeRecord(content);
                csvWriter.close();
            }

            br.close();

        }

    }
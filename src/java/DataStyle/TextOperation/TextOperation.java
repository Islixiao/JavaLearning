package DataStyle.TextOperation;

/***Created by moyongzhuo
 *On 2017/10/12  ***19:00.
 ******/
/***Created by moyongzhuo
 *On 2017/10/12  ***18:14.
 ******/

import java.io.*;

public class TextOperation {
    public static void main(String[] args)
    {
        try
        {
            // read file content from file
            StringBuffer sb = new StringBuffer("");

            FileReader reader = new FileReader("C:\\Users\\moyongzhuo\\Desktop\\changhong_doc\\Company_Learing\\Java\\文件操作\\txt文件\\One.txt");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while ((str = br.readLine()) != null)
            {
                sb.append(str + "\n");
                System.out.println(str);
            }

            br.close();
            reader.close();

            // write string to file
            FileWriter writer = new FileWriter("C:\\Users\\moyongzhuo\\Desktop\\changhong_doc\\Company_Learing\\Java\\文件操作\\txt文件\\OneTest.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            //bw.write(sb.append(str + "\n").toString());

            bw.close();
            writer.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
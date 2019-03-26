package DataStyle.TextOperation;

/***Created by moyongzhuo
 *On 2017/10/12  ***19:02.
 ******/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestOne
{
    public static double[] writeToDat(String path)
    {
        File file = new File(path);
        List<String> list = new ArrayList<String>();
        double[] nums = null;
        try
        {
            BufferedReader bw = new BufferedReader(new FileReader(file));
            String line = null;
            // 因为不知道有几行数据，所以先存入list集合中
            while ((line = bw.readLine()) != null)
            {
                list.add(line);
            }
            bw.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // 确定数组长度
        nums = new double[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            String s = list.get(i);
            nums[i] = Double.parseDouble(s);
        }
        return nums;
    }

    public static void main(String[] args)
    {

        String path = "C:\\Users\\moyongzhuo\\Desktop\\changhong_doc\\Company_Learing\\Java\\文件操作\\txt文件\\One.txt";
        double[] nums = writeToDat(path);
        for (int i = 0; i < nums.length; i++)
        {
            System.out.println(nums[i]);
        }
    }
}
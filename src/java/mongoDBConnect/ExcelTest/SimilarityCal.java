package mongoDBConnect.ExcelTest;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

/***Created by moyongzhuo
 *On 2017/10/20  ***10:03.
 ******/
public class SimilarityCal {
    public static void main(String[] args) {
        //要比较的两个字符串
        String str1 = "小明的姐姐是彩色";
        String str2 = "彩色是小明他姐";
        levenshtein(str1, str2);
    }

    protected static final int max_sheet_row = 50;
    protected static String excel_result_path = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\";
    protected static String excel_source_path = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\";
    protected List<List<String>> content_sourceJian;
    protected List<List<String>> content_sourceYan;
    protected static boolean exPass = true;

    @Before
    public void init() {
        Calendar now = Calendar.getInstance();
        excel_result_path += "自研KG中没有的影片名_20171019.xlsx";
        excel_source_path += "自研KG中没有的影片名_20171019.xls";
        // content_source= ExcelUtil.read(excel_source_path,1,0,1).get("content");
        content_sourceJian = ExcelUtil.readOneBySheetName("竹间", excel_source_path, 1, 0, 0);
        content_sourceYan = ExcelUtil.readOneBySheetName("自研", excel_source_path, 1, 0, 0);
    }
    @Test
    public void read() {
        List<String> Zhu = null;
        List<String> Yan = null;
        List<String> willAddList = null;
        Set<String> willAddNameSet = new HashSet<>();
        try {
            Zhu = FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\Zhu.txt"));
            Yan = FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\FilmNameInAllsort.txt"));
            willAddList = FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\更改1021.txt"));
                   // FileUtils.readLines(new File("D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\mongoDBConnect\\ExcelTest\\1020竹简删除后相同的名字.txt"));
            for (String willAddStr : willAddList) {
                String []array = willAddStr.split("\t");
                if (!array[0].isEmpty()) {
                    willAddNameSet.add(array[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line11 : Zhu) {
            if (willAddNameSet.contains(line11)) {
                continue;
            }
            if (StringUtils.isNotBlank(line11)) {
                String line1char = line11;
                int length1 = line1char.length();
                String maxName = null;
                int maxnotes = 0;
                String secondName = null;
                int secondsnotes = 0;
                List<String> equalList = new ArrayList<>();
                for (String line2 : Yan) {
                    if (StringUtils.isNotBlank(line2)) {
                        String line1char2 = line2;
                        String tempLine1char2 = line1char2;
                        int length2 = line1char2.length();
                        int len3 = 0;
                        for (int j = 0; j < length1; j++) {
                            char arrtest = line1char.charAt(j);
                            String tempStr = String.valueOf(arrtest);
                            boolean rr = line1char2.contains(tempStr);
                            // System.out.println(rr);
                            if (rr) {
                                line1char2 = line1char2.replace(tempStr, "");
                                len3++;
                            }
                        }
                        if (len3 == length1) {
                            equalList.add(tempLine1char2);
                            if (equalList.size() == 2) {
                                break;
                            }
                        }

                      //  Double a = 1.0 * len3 / minNum(length1, length2);
                        if (len3 > maxnotes) {
                            secondName = maxName;
                            secondsnotes = maxnotes;
                            maxnotes = len3;
                            maxName = tempLine1char2;
                        } else if (len3 > secondsnotes) {
                            secondsnotes = len3;
                            secondName = tempLine1char2;
                        }
                        // System.out.println(a);
                    }
                }
                if (equalList.size() == 2) {
                       // System.out.println(line1char + "\t" + equalList.get(0) + "\t" + 1.0 + "\t" + equalList.get(1) + "\t" + 1.0);
                } else {
                    System.out.println(line1char + "\t" + maxName + "\t" + maxnotes + "\t" + secondName + "\t" + secondsnotes);
                }
            }
        }
    }
    @Test
    public void testread() {


        for (List<String> line1 : content_sourceJian) {//zhu

            List<List<String>> contents1 = new ArrayList<>();
            if (StringUtils.isNotBlank(line1.get(0))) {
                String line1char = line1.get(0);
                int length1 = line1char.length();
                int size = line1.size();
                //String[] arr = (String[])line1.toArray(new String[size]);//使用了第二种接口，返回值和参数均为结果
                List<Integer> finaldata = null;//存储比较数据
                List<Float> d = new ArrayList<>();
                String maxName = null;
                Double maxSimilar = 0.0;
                String secondName = null;
                Double secondSimilar = 0.0;
                List<String> equalList = new ArrayList<>();
                for (List<String> line2 : content_sourceYan) {
                    if (StringUtils.isNotBlank(line2.get(0))) {
                        String line1char2 = line2.get(0);
                        String tempLine1char2 = line1char2;
                        int length2 = line1char2.length();
                        int len3 = 0;
                        for (int j = 0; j < length1; j++) {
                            char arrtest = line1char.charAt(j);
                            String tempStr = String.valueOf(arrtest);
                            boolean rr = line1char2.contains(tempStr);
                            // System.out.println(rr);
                            if (rr) {
                                line1char2 = line1char2.replace(tempStr, "");
                                len3++;
                            }
                        }
                        if (len3 == length1) {
                            equalList.add(tempLine1char2);
                            if (equalList.size() == 2) {
                                break;
                            }
                        }
                        Double a = 1.0 * len3 / minNum(length1, length2);
                        if (a > maxSimilar) {
                            secondName = maxName;
                            secondSimilar = maxSimilar;
                            maxSimilar = a;
                            maxName = tempLine1char2;
                        } else if (a > secondSimilar) {
                            secondSimilar = a;
                            secondName = tempLine1char2;
                        }
                        // System.out.println(a);
                    }
                }
                if (equalList.size() == 2) {
                    System.out.println(line1char + "\t" + equalList.get(0) + "\t" + 1.0 + "\t" + equalList.get(1) + "\t" + 1.0);
                } else {
                   // System.out.println(line1char + "\t" + maxName + "\t" + maxSimilar + "\t" + secondName + "\t" + secondSimilar);
                }


                // float Max = Collections.max(d);
                // System.out.println("最大值" + Max);
                   /* List<Integer> d1 = d;
                    for(int k=0;k<d1.size();k++){
                        Integer dd=d1.get(k);
                    if(dd<=330)d1.remove(dd);
                    }
                    MySort mysort=new MySort();
                    System.out.println(MySort.max4(d1));*/

                //MySort.max4(d);
                // List<List<String>> contents2 = new ArrayList<>();
                // for (List<String> line2:contents2){
                //       line2.get(0);
                // }
                // List<Float> flour = null;
                /// float ass = geteflour(d);
                // flour.add(ass);
                // d.remove(ass);
                // System.out.println(ass);
                /// System.out.println(finaldata);
            }
        }
    }
        //System.out.println(contents);
        //ExcelUtil.write(excel_result_path,contents,"mysheet");


    //取相似度大的四个数值
    float geteflour(List<Float> d) {
        float aa = d.get(0);
        for (int i = 0; i < d.size(); i++) {

            if (aa >= d.get(i)) {
                aa = d.get(i);
            }
        }
        return aa;
    }

    private static int minNum(int num1, int num2) {
        if (num1 > num2) {
            return num2;
        }
        return num1;
    }

    /**
     * 　　DNA分析 　　拼字检查 　　语音辨识 　　抄袭侦测
     *
     * @createTime 2012-1-12
     */
    public static double levenshtein(String str1, String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();


        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
//        System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
//        System.out.println("差异步骤："+dif[len1][len2]);

        //计算相似度


        float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
//        System.out.println("相似度："+similarity);
        return similarity;
    }


    //得到最大值
    private static int max(int... is) {
        int max = Integer.MAX_VALUE;
        for (int i : is) {
            if (i < max) {
                max = i;
            }
        }
        return max;
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

}

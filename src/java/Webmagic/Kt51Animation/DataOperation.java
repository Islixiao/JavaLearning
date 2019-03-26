package Webmagic.Kt51Animation;

/***Created by moyongzhuo
 *On 2017/10/24  ***22:18.
 ******/
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/***Created by moyongzhuo
 *On 2017/10/17  ***18:59.
 ******/
public class DataOperation {
    protected static final int max_sheet_row = 50;
    protected static String excel_result_path = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\Kt51Animation\\kt51_animationall_1024.xls";
    protected static String excel_source_path = "D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\Kt51Animation\\kt51_animationall_1024.xls";
    protected List<List<String>> content_source;
    protected static boolean exPass = true;



    @Test
    public void testread() {
        try {
            content_source = ExcelUtil.readOneBySheetName("Sheet1", excel_source_path, 1, 0, 0);
            List<List<String>> contents = new ArrayList<>();
            for (List<String> line : content_source) {
                int ii = 0;
                if (StringUtils.isNotBlank(line.get(1))) {
                    for (List<String> myline : content_source) {
                        String aa = line.get(1);
                        String bb = myline.get(1);
                        if (aa.contains(bb) && aa.length() == bb.length()) {
                            ii++;
                            if (ii >= 2) {
                                System.out.println(myline.get(1)+ii);
                            }
                        }
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
       // System.out.println(contents);
    }
}
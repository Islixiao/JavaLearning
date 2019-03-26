package googlezxing;

/***Created by moyongzhuo
 *On 2017/9/27  ***9:20.
 ******/import java.io.File;

public class TestMatrixUtil {

    /**
     * 测试函数。简单地将指定的字符串生成二维码图片。
     *
     * <br/><br/>
     * 作者：wallimn<br/>
     * 时间：2014年5月25日　　下午10:30:00<br/>
     * 联系：54871876@qq.com<br/>
     */
    public static void main(String[] args) throws Exception {
        String text = "http://wallimn.itey.com";
        String result;
        String format = "gif";
        //生成二维码
        File outputFile = new File("d:"+File.separator+"rqcode.gif");
        MatrixUtil.writeToFile(MatrixUtil.toQRCodeMatrix(text, null, null), format, outputFile);
        result = MatrixUtil.decode(outputFile);
        System.out.println(result);

        outputFile = new File("d:"+File.separator+"barcode.gif");
        MatrixUtil.writeToFile(MatrixUtil.toBarCodeMatrix(text, null, null), format, outputFile);

        result = MatrixUtil.decode(outputFile);
        System.out.println(result);
    }

}

package Java2ML.ML;/*
package ML;

*/
/***Created by moyongzhuo
 *On 2017/11/8  ***10:24.
 ******//*

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class RegressionDemo {
    */
/**
     * 将excel表中每一行的数据封装成一个对象放入list中
     * @return
     *//*

    private static List<BankDemo> readSheet(){
        List<BankDemo> sheetList = new ArrayList<BankDemo>();
        BankDemo bank = null;
        HSSFWorkbook hssfwb=null;
        HSSFSheet hssfSheet = null;
        HSSFRow hssfRow = null;
        try {
            //拿到工作簿
            InputStream input = new FileInputStream(new File("f:\\javatest\\bank.xls"));
            hssfwb = new HSSFWorkbook(input);
            //拿到sheet
            hssfSheet = hssfwb.getSheetAt(0);

            //循环行
            for(int rowNum=1; rowNum<hssfSheet.getLastRowNum()+1;rowNum++){
                bank = new BankDemo();

                //拿到行
                hssfRow = hssfSheet.getRow(rowNum);
                bank.setBankId((int) hssfRow.getCell(0).getNumericCellValue());
                bank.setBldk(hssfRow.getCell(1).getNumericCellValue());
                bank.setDkye(hssfRow.getCell(2).getNumericCellValue());
                bank.setYsdk(hssfRow.getCell(3).getNumericCellValue());
                bank.setXmgs(hssfRow.getCell(4).getNumericCellValue());
                bank.setZctze(hssfRow.getCell(5).getNumericCellValue());

                sheetList.add(bank);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sheetList;
    }

    */
/**
     * 计算ListOne均值
     *//*

    public static Double comMeanOne(List<Double> listOne){
        double sumOne=0;
        for(int x=0; x<listOne.size();x++){
            sumOne = sumOne + listOne.get(x);
        }
        return sumOne/listOne.size();
    }

    */
/**
     * 计算listTwo的均值
     * @return
     *//*

    public static Double comMeanTwo(List<Double> listTwo){
        double sumTwo=0;
        for(int x=0; x<listTwo.size();x++){
            sumTwo +=  listTwo.get(x);
        }
        return sumTwo/listTwo.size();
    }

    */
/**
     * 任意两列的相关性分析方法
     * @param args
     *//*

    public static Map<Double,Double> correAnalyse(List<Double> listOne,List<Double> listTwo){

        //计算样本均值

        double meanOne=0, meanTwo=0;
        double upValue = 0, lowValue=0, lowValueOne=0, lowValueTwo=0;
        double correValue =0, tValue=0;

        Map<Double,Double> correMap = new HashMap<Double,Double>();

        meanOne = comMeanOne(listOne);
        meanTwo = comMeanTwo(listTwo);

        //计算相关分析公式的分子部分
        for(int i =0;i<listOne.size();i++){
            upValue = upValue + ((listOne.get(i)-meanOne)*(listTwo.get(i)-meanTwo));
        }
        //计算分母部分
        for(int i=0;i<listOne.size();i++){
            lowValueOne =lowValueOne + Math.pow(listOne.get(i)-meanOne, 2);
            lowValueTwo =lowValueTwo + Math.pow(listTwo.get(i)-meanTwo, 2);
        }
        lowValue = Math.sqrt(lowValueOne*lowValueTwo);

        //计算相关系数
        correValue = upValue/lowValue;
        //计算t检验统计量的值
        tValue = Math.abs(correValue)*(Math.sqrt((listOne.size()-2)/(1-Math.pow(correValue, 2))));

        correMap.put(correValue, tValue);
        return correMap;
    }

    */
/**
     *
     * @param args
     *//*

    public static Map<Double,Double> regreAnalyse(List<Double>listX,List<Double>listY){
        //这里假设不存在补差空缺数字的问题
        double templeOne=0,templeTwo=0,templex=0,templey=0;
        double upValueOne =0, upValueTwo=0,lowValueOne=0,lowValueTwo=0;
        double meanOne=0,meanTwo=0;
        double betaOne =0, betaZero =0;

        int n = listX.size();
        Map<Double,Double> regreMap = new HashMap<Double,Double>();

        meanOne = comMeanOne(listX);
        meanTwo = comMeanTwo(listY);

        for(int i=0;i<n;i++){
            templeOne += listX.get(i)*listY.get(i);
            templex += listX.get(i);
            templey += listY.get(i);
            templeTwo += Math.pow(listX.get(i), 2);
        }
        upValueOne = n*templeOne;
        upValueTwo = templex * templey;
        lowValueOne =n*templeTwo;
        lowValueTwo = Math.pow(templex, 2);

        //计算betaOne
        betaOne = (upValueOne-upValueTwo)/(lowValueOne-lowValueTwo);
        betaZero = meanTwo - betaOne*meanOne;

        regreMap.put(betaZero,betaOne);
        return regreMap;

    }


    public static void main(String[] args) {
        List<BankDemo> list = readSheet();
        List<Double> listOne = new ArrayList<Double>(),
                listTwo = new ArrayList<Double>();
        for(BankDemo bank:list){
            listOne.add(bank.getBldk());
            listTwo.add(bank.getDkye());
        }
        Map<Double,Double> correMap = correAnalyse(listOne,listTwo),
                regreMap = regreAnalyse(listTwo,listOne);
        System.out.println("r and t of the BLDK with DKYE are:   "+ correMap.toString());
        System.out.println("betaZero and betaOne of the BLDK with DKYE are:   "+ regreMap.toString());

    }

}
*/

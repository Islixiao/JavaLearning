package mongoDBConnect.ExcelTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
    Log log = LogFactory.getLog(ExcelUtil.class);

    /**
     * @param fileStr   文件路径
     * @param headLines 头行的行数
     * @return {"head":"","content":""}
     */
    public static Map<String, List<List<String>>> read(String fileStr, int headLines, int initLine, int initColumn) {
        Map<String, List<List<String>>> result = new HashMap<String, List<List<String>>>();
        File root = new File(fileStr);
        List<File> files = new ArrayList<File>();
        if (root.isDirectory()) {
            files.addAll(Arrays.asList(root.listFiles()));
        } else {
            files.add(root);
        }
        List<List<String>> head = new ArrayList<List<String>>();
        List<List<String>> content = new ArrayList<List<String>>();
        result.put("head", head);
        result.put("content", content);
        for (File file : files) {
            readOne(0, result, file, headLines, initLine, initColumn);
        }
        return result;
    }

    public static List<List<String>> readOneBySheetIndex(int sheetIndex, String fileStr, int headLines, int initLine, int initColumn) {
        File file = new File(fileStr);
        Map<String, List<List<String>>> result = new HashMap<String, List<List<String>>>();
        List<List<String>> head = new ArrayList<List<String>>();
        List<List<String>> content = new ArrayList<List<String>>();
        result.put("head", head);
        result.put("content", content);
        readOne(sheetIndex, result, file, headLines, initLine, initColumn);
        return result.get("content");
    }

    public static List<List<String>> readOneBySheetName(String sheetName, String fileStr, int headLines, int initLine, int initColumn) {
        return readOneBySheetName(false, sheetName, fileStr, headLines, initLine, initColumn);
    }

    public static List<List<String>> readOneBySheetName(boolean exPass, String sheetName, String fileStr, int headLines, int initLine, int initColumn) {
        File file = new File(fileStr);
        Map<String, List<List<String>>> result = new HashMap<String, List<List<String>>>();
        List<List<String>> head = new ArrayList<List<String>>();
        List<List<String>> content = new ArrayList<List<String>>();
        result.put("head", head);
        result.put("content", content);
        readOneBySheetName(exPass, sheetName, result, file, headLines, initLine, initColumn);
        return result.get("content");
    }

    private static void readOneBySheetName(boolean exPass, String sheetName, Map<String, List<List<String>>> result, File file, int headLines, int initLine, int initColumn) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file.getPath()));
            HSSFWorkbook workBook = new HSSFWorkbook(fs);
            HSSFSheet sheet = workBook.getSheet(sheetName);
            if (sheet == null) return;
            Iterator<Row> it = sheet.rowIterator();
//            int lineIndex = 1196;
            while (it.hasNext()) {
                boolean fail = true;
//                if (lineIndex-- == 0) {
//                    LogUtil.printErr(lineIndex);
//                }
                Row row = it.next();
                if (row.getRowNum() < initLine) continue;
                if (row == null) break;
                List<String> line = new ArrayList<String>();
//                Iterator<Cell> itc = row.cellIterator();
                for (int i = initColumn; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);

                    if (cell == null) {
                        line.add("");
                    } else {
                        try {
                            String cellstr = getCellFormatValue(cell);
//                            是否只测试 未通过的
                            if (exPass && "PASS".equals(cellstr)) {
                                fail = false;
                            }
                            line.add(cellstr);
                        } catch (Exception e) {
                            line.add("");
                        }
                    }
                }
                if (row.getRowNum() < (initLine + headLines)) {
                    result.get("head").add(line);
                } else if (fail) {
                    result.get("content").add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readOne(int sheetIndex, Map<String, List<List<String>>> result, File file, int headLines, int initLine, int initColumn) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file.getPath()));
            HSSFWorkbook workBook = new HSSFWorkbook(fs);
            HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
            Iterator<Row> it = sheet.rowIterator();
//            int lineIndex = 1196;
            while (it.hasNext()) {
//                if (lineIndex-- == 0) {
//                    LogUtil.printErr(lineIndex);
//                }
                Row row = it.next();
                if (row.getRowNum() < initLine) continue;
                if (row == null) break;
                List<String> line = new ArrayList<String>();
//                Iterator<Cell> itc = row.cellIterator();
                for (int i = initColumn; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        line.add("");
                    } else {
                        try {
                            String cellstr = getCellFormatValue(cell);
                            line.add(cellstr);
                        } catch (Exception e) {
                            line.add("");
                        }
                    }
                }
                if (row.getRowNum() < (initLine + headLines)) {
                    result.get("head").add(line);
                } else {
                    result.get("content").add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    } else {
                        // 如果是纯数字
                        // 取得当前Cell的数值
                        BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                        cellvalue = String.valueOf(big.longValue());
                    }
                    break;
                }
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    // 假设为日期型

                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    cellvalue = cellvalue.substring(0, cellvalue.length() - 2);
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 读取一个文件
     *
     * @param file
     * @param headLines
     * @param initLine
     * @param initColumn
     * @return
     */
    public static Map<String, List<List<String>>> readOneByResource(String file, int headLines, int initLine, int initColumn) {
        Map result = new HashMap();

        List head = new ArrayList();
        List content = new ArrayList();
        result.put("head", head);
        result.put("content", content);

        readOne(result, file, headLines, initLine, initColumn);

        return result;
    }

    private static void readOne(Map<String, List<List<String>>> result, String file, int headLines, int initLine, int initColumn) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(ExcelUtil.class.getResourceAsStream(file));
            HSSFWorkbook workBook = new HSSFWorkbook(fs);
            HSSFSheet sheet = workBook.getSheetAt(0);
            Iterator<Row> it = sheet.rowIterator();
            while (it.hasNext()) {
                Row row = it.next();
                if (row.getRowNum() < initLine) continue;
                if (row == null) break;
                List<String> line = new ArrayList<String>();
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        line.add("");
                    } else {
                        String cellstr = getCellFormatValue(cell);
                        line.add(cellstr);
                    }
                }
                if (row.getRowNum() < (initLine + headLines)) {
                    result.get("head").add(line);
                } else {
                    result.get("content").add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String filePath, List<List<String>> list, String sheetName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                write1(filePath, list, sheetName);
            }
        }).start();
    }

    private static void write1(String filePath, List<List<String>> list, String sheetName) {

        if (CollectionUtils.isEmpty(list)) return;
        POIFSFileSystem fs = null;
        HSSFWorkbook workBook = null;
        try {
//            追加
            File file = new File(filePath);
            if (file.exists()) {
                fs = new POIFSFileSystem(new FileInputStream(filePath));
                workBook = new HSSFWorkbook(fs);
            } else {
                file.getParentFile().mkdirs();
                workBook = new HSSFWorkbook();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        +new SimpleDateFormat("HHmm").format(new Date())

        HSSFSheet sheet = workBook.createSheet(sheetName);
        int rowIndex = 0;
        for (List<String> line : list) {
            Row row = sheet.createRow(rowIndex++);
            sheet.autoSizeColumn(line.size() - 1);
            int cellIndex = 0;
            for (String one : line) {
                Cell cell = row.createCell(cellIndex++);
                cell.setCellValue(one);
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}




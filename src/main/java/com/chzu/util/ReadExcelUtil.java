package com.chzu.util;

import com.chzu.enums.ExcelFileType;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelUtil {
    private static String[] studentExcel = {"姓名","学号","密码","性别","电子邮件","联系电话","班级"};

    //比较两个数组内容是否一致
    public static boolean compareStringArray(String[] arrayA,String[] arrayB){
        int i = 0;
        int count = 0;
        for(String property : arrayA){
            if(property.equals(arrayB[i]))
                count ++;
            i ++;
        }
        if(count == arrayA.length){
            return true;
        }else{
            return false;
        }
    }

    //解析Excel表格，分析表格内容，确定信息类型
    public static ExcelFileType parseTypeFromInputFile(String inputFilePath) throws IOException {
        FileInputStream fileInput = new FileInputStream(inputFilePath);//创建文件输入流
        XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
        XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet

        //获取sheet里面第一行数据，据此分类Excel文件的类型
        XSSFRow row = sheet.getRow(0);
        int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
        String[] result = new String[25];//声明字符串数组存储第一行内容


        for (int i=0; i<columnNum; ++i) {
            XSSFCell cell = row.getCell(i);//获取每个单元格
            result[i] = cell.getStringCellValue();
        }

        //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
        wb.close();
        fileInput.close();
        if(compareStringArray(studentExcel,result)){
            return ExcelFileType.students;
        }else{
            //excel文件格式不正确
            return ExcelFileType.readError;
        }
    }

    //解析Excel表格内容，返回字符串二维数组
    public static String[][] parseInfoFromInputFile(String inputFilePath) throws IOException {
        FileInputStream fileInput = new FileInputStream(inputFilePath);//创建文件输入流
        XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
        XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
        int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数

        String[][] result = new String[100][25];

        //i = 1 代表要开始读取的行号为1，从第二行开始读取，下面这个循环的作用是读取每一行内容
        for (int i = 1; i <= lastRowNum; ++i) {
            XSSFRow row = sheet.getRow(i);//获取每一行
            int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
            for (int j=0; j<columnNum; ++j) {
                XSSFCell cell = row.getCell(j);//获取每个单元格
                if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
                    result[i-1][j] = String.format("%.0f",cell.getNumericCellValue());
                }else {
                    result[i-1][j] = cell.getStringCellValue();
                }
            }
            System.out.println();
        }
        wb.close();
        fileInput.close();
        return result;
    }
}

package com.mrcoder.sbmexcel.utils.excel;


import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


/**
 * Excel 工具类
 *
 * @author mrcoder
 * @version 1.0 2019.05.29
 */
public class ExcelUtil {

    /**
     * 读取excel反射实体
     *
     * @param file  MultipartFile
     * @param clazz entity
     * @return
     * @throws RuntimeException
     */
    public static <T extends Object> List<T> readExcelObject(MultipartFile file, Class<T> clazz) {

        // 存储excel数据
        List<T> list = new ArrayList<>();
        Workbook wookbook = null;
        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException("文件读取异常");
        }

        // 根据excel文件版本获取工作簿
        if (file.getOriginalFilename().endsWith(".xls")) {
            wookbook = xls(inputStream);
        } else if (file.getOriginalFilename().endsWith(".xlsx")) {
            wookbook = xlsx(inputStream);
        } else {
            throw new RuntimeException("非excel文件");
        }

        // 得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);

        // 获取行总数
        int rows = sheet.getLastRowNum() + 1;
        Row row;

        // 获取类所有属性
        Field[] fields = clazz.getDeclaredFields();

        T obj = null;
        int coumnIndex = 0;
        Cell cell = null;
        ExcelAnnotation excelAnnotation = null;
        for (int i = 1; i < rows; i++) {
            // 获取excel行
            row = sheet.getRow(i);
            //此处用来过滤空行
            Cell cell0 = row.getCell(0);
            cell0.setCellType(CellType.STRING);
            Cell cell1 = row.getCell(1);
            cell1.setCellType(CellType.STRING);
            if (cell0.getStringCellValue() == "" && cell1.getStringCellValue() == "") {
                continue;
            }
            try {
                // 创建实体
                obj = clazz.newInstance();
                for (Field f : fields) {
                    // 设置属性可访问
                    f.setAccessible(true);
                    // 判断是否是注解
                    if (f.isAnnotationPresent(ExcelAnnotation.class)) {
                        // 获取注解
                        excelAnnotation = f.getAnnotation(ExcelAnnotation.class);
                        // 获取列索引
                        coumnIndex = excelAnnotation.columnIndex();
                        // 获取单元格
                        cell = row.getCell(coumnIndex);
                        // 设置属性
                        setFieldValue(obj, f, wookbook, cell);
                    }
                }
                System.out.println(obj);
                // 添加到集合中
                list.add(obj);
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("excel文件内容出错");
            }
        }
        try {
            //释放资源
            wookbook.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 绑定实体值
     *
     * @param obj      Object
     * @param f        Field
     * @param wookbook Workbook
     * @param cell     Cell
     * @return
     * @throws RuntimeException
     */
    private static void setFieldValue(Object obj, Field f, Workbook wookbook, Cell cell) {
        try {

            cell.setCellType(CellType.STRING);

            if (f.getType() == byte.class || f.getType() == Byte.class) {

                f.set(obj, Byte.parseByte(cell.getStringCellValue()));

            } else if (f.getType() == int.class || f.getType() == Integer.class) {

                f.set(obj, Integer.parseInt(cell.getStringCellValue()));

            } else if (f.getType() == Double.class || f.getType() == double.class) {

                f.set(obj, Double.parseDouble(cell.getStringCellValue()));

            } else if (f.getType() == BigDecimal.class) {

                f.set(obj, new BigDecimal(cell.getStringCellValue()));

            } else {

                f.set(obj, cell.getStringCellValue());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对excel 2003处理
     */
    private static Workbook xls(InputStream is) {
        try {
            // 得到工作簿
            return new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对excel 2007处理
     */
    private static Workbook xlsx(InputStream is) {
        try {
            // 得到工作簿
            return new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
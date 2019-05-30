package com.mrcoder.sbmexcel.service;


import com.mrcoder.sbmexcel.mapper.ExcelMapper;
import com.mrcoder.sbmexcel.model.Excel;
import com.mrcoder.sbmexcel.utils.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExcelService {


    @Autowired
    private ExcelMapper excelMapper;


    //excel导入
    public int importExcel(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile excel = multipartRequest.getFile("filename");

        try {
            List<Excel> excelData = ExcelUtil.readExcelObject(excel, Excel.class);
            //检查每列数据
            for (int i = 0; i < excelData.size(); i++) {
                excelData.get(i).setStatus(1);
                Date time = new Date();
                excelData.get(i).setCreateTime(time);
                excelData.get(i).setUpdateTime(time);
            }
            //批量导入
            return excelMapper.insertList(excelData);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //excel导入
    public void exportExcel(HttpServletResponse response) {
        try {
            // 1.读取Excel模板
            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/export.xlsx");
            InputStream in = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(in);
            // 2.读取模板里面的所有Sheet
            XSSFSheet sheet = wb.getSheetAt(0);
            // 3.设置公式自动读取
            sheet.setForceFormulaRecalculation(true);
            // 4.向相应的单元格里面设置值
            XSSFRow row;

            // 5.得到第二行
            row = sheet.getRow(1);
            // 6.设置单元格属性值和样式
            row.getCell(1).setCellValue("张三");
            row.getCell(3).setCellValue("18");
            row.getCell(6).setCellValue("本科");
            row.getCell(8).setCellValue(new Date());

            row = sheet.getRow(2);
            row.getCell(1).setCellValue("1511xxxx234");
            row.getCell(3).setCellValue("广东");
            row.getCell(6).setCellValue("本科");

            row = sheet.getRow(3);
            row.getCell(1).setCellValue("180");
            row.getCell(3).setCellValue("已婚");
            row.getCell(6).setCellValue("深圳");
            row.getCell(8).setCellValue("2");

            row = sheet.getRow(4);
            row.getCell(1).setCellValue("60");
            row.getCell(3).setCellValue("中国");
            row.getCell(6).setCellValue("其它");
            row.getCell(8).setCellValue("备注");

            //单元格合并
            row = sheet.getRow(6);
            row.getCell(0).setCellValue("合并列");
            CellRangeAddress region = new CellRangeAddress(6, 6, 0, 5);
            sheet.addMergedRegion(region);

            //单元格设置背景色
            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            row.getCell(0).setCellStyle(style);

            //设置单元格边框
            row = sheet.getRow(8);
            XSSFCellStyle style2 = wb.createCellStyle();
            style2.setBorderBottom(BorderStyle.DOUBLE);
            style2.setBorderRight(BorderStyle.DOUBLE);
            style2.setBorderLeft(BorderStyle.DOUBLE);
            style2.setBorderTop(BorderStyle.DOUBLE);
            style2.setBottomBorderColor(IndexedColors.SKY_BLUE.getIndex());
            style2.setRightBorderColor(IndexedColors.SKY_BLUE.getIndex());
            row.getCell(0).setCellStyle(style2);

            // 7.设置sheet名称和单元格内容
            wb.setSheetName(0, "测试");

            String fileName = new String(("export-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .getBytes(), "UTF-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "No-cache");
            response.setCharacterEncoding("utf-8");
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package com.java.springboot.util;

import com.java.springboot.entity.EmpAndDept;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */

/**
 * @author Administrator
 *
 */
public class ReadExcel {
	/**
     * 读取excel中的数据
     * @param path
     * @return List<User>
     * @author zhang 2015-08-18 00:08
     */
    public  List<EmpAndDept> readExcel(String path) {

        if (path != null && !path.equals("")) {
            String ext = getExt(path);
            if (ext!=null && !ext.equals("")) {
                if (ext.equals("xls")) {
                    return readXls(path);
                } else if (ext.equals("xlsx")) {
                    return readXlsx(path);
                }
            }
        }
        return new ArrayList<EmpAndDept>();
    }

    /**
     * 读取后缀为xls的excel文件的数据
     * @param path
     * @return List<User>
     * @author zhang 2015-08-18 00:10
     */
    private List<EmpAndDept> readXls(String path) {

        HSSFWorkbook hssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmpAndDept empAndDept = null;
        List<EmpAndDept> list = new ArrayList<EmpAndDept>();
        if (hssfWorkbook != null) {
            // Read the Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // Read the Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                    	empAndDept = new EmpAndDept();
                        HSSFCell empno = hssfRow.getCell(0);
                        HSSFCell ename = hssfRow.getCell(1);
                        HSSFCell job = hssfRow.getCell(2);
                        HSSFCell mgr = hssfRow.getCell(3);
                        HSSFCell sal = hssfRow.getCell(4);
                        HSSFCell hiredate = hssfRow.getCell(5);
                        HSSFCell comm = hssfRow.getCell(6);
                        HSSFCell dname = hssfRow.getCell(7);
                        HSSFCell loc = hssfRow.getCell(8);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        empAndDept.setEmpno(Integer.parseInt(getValue(empno)));
                        empAndDept.setEname(getValue(ename));
                        empAndDept.setJob(getValue(job));
                        empAndDept.setMgr(Integer.parseInt(getValue(mgr)));
                        empAndDept.setSal(Double.parseDouble(getValue(sal)));
                        try {
							empAndDept.setHiredate(sdf.parse(getValue(hiredate)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
                        empAndDept.setComm(Double.parseDouble(getValue(comm)));
                        empAndDept.setDname(getValue(dname));
                        empAndDept.setLoc(getValue(loc));
                        list.add(empAndDept);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 读取后缀为xlsx的excel文件的数据
     * @param path
     * @return List<User>
     * @author zhang 2015-08-18 00:08
     */
    private List<EmpAndDept> readXlsx(String path) {

        XSSFWorkbook xssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(path);
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmpAndDept empAndDept = null;
        List<EmpAndDept> list = new ArrayList<EmpAndDept>();
        if(xssfWorkbook!=null){
            // Read the Sheet
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // Read the Row
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                    	empAndDept = new EmpAndDept();
                    	XSSFCell empno = xssfRow.getCell(0);
                    	XSSFCell ename = xssfRow.getCell(1);
                    	XSSFCell job = xssfRow.getCell(2);
                        XSSFCell mgr = xssfRow.getCell(3);
                        XSSFCell sal = xssfRow.getCell(4);
                        XSSFCell hiredate = xssfRow.getCell(5);
                        XSSFCell comm = xssfRow.getCell(6);
                        XSSFCell dname = xssfRow.getCell(7);
                        XSSFCell loc = xssfRow.getCell(8);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        empAndDept.setEmpno(Integer.parseInt(getValue(empno)));
                        empAndDept.setEname(getValue(ename));
                        empAndDept.setJob(getValue(job));
                        empAndDept.setMgr(Integer.parseInt(getValue(mgr)));
                        empAndDept.setSal(Double.parseDouble(getValue(sal)));
                        try {
							empAndDept.setHiredate(sdf.parse(getValue(hiredate)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
                        empAndDept.setComm(Double.parseDouble(getValue(comm)));
                        empAndDept.setDname(getValue(dname));
                        empAndDept.setLoc(getValue(loc));
                        list.add(empAndDept);
                    }
                }
            }
        }
        return list;
    }

    //读取InputStream
    public List<EmpAndDept> readIs(InputStream is) {
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EmpAndDept empAndDept = null;
        List<EmpAndDept> list = new ArrayList<EmpAndDept>();
        if(xssfWorkbook!=null){
            // Read the Sheet
            for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
                if (xssfSheet == null) {
                    continue;
                }
                // Read the Row
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if (xssfRow != null) {
                    	empAndDept = new EmpAndDept();
                    	XSSFCell empno = xssfRow.getCell(0);
                    	XSSFCell ename = xssfRow.getCell(1);
                    	XSSFCell job = xssfRow.getCell(2);
                        XSSFCell mgr = xssfRow.getCell(3);
                        XSSFCell sal = xssfRow.getCell(4);
                        XSSFCell hiredate = xssfRow.getCell(5);
                        XSSFCell comm = xssfRow.getCell(6);
                        XSSFCell dname = xssfRow.getCell(7);
                        XSSFCell loc = xssfRow.getCell(8);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        empAndDept.setEmpno(Integer.parseInt(getValue(empno)));
                        empAndDept.setEname(getValue(ename));
                        empAndDept.setJob(getValue(job));
                        empAndDept.setMgr(Integer.parseInt(getValue(mgr)));
                        empAndDept.setSal(Double.parseDouble(getValue(sal)));
                        try {
							empAndDept.setHiredate(sdf.parse(getValue(hiredate)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
                        empAndDept.setComm(Double.parseDouble(getValue(comm)));
                        empAndDept.setDname(getValue(dname));
                        empAndDept.setLoc(getValue(loc));
                        list.add(empAndDept);
                    }
                }
            }
        }
        return list;
    }
    /**
     * 获取文件扩展名
     * @param path
     * @return String
     * @author zhang 2015-08-17 23:26
     */
    private String getExt(String path) {
        if (path == null || path.equals("") || !path.contains(".")) {
            return null;
        } else {
            return path.substring(path.lastIndexOf(".") + 1, path.length());
        }
    }


    /**
     * 判断后缀为xlsx的excel文件的数据类型
     * @param xssfRow
     * @return String
     * @author zhang 2015-08-18 00:12
     */
    @SuppressWarnings({ "static-access", "deprecation" })
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    /**
     * 判断后缀为xls的excel文件的数据类型
     * @param hssfCell
     * @return String
     * @author zhang 2015-08-18 00:12
     */
    @SuppressWarnings({ "static-access", "deprecation" })
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    
}

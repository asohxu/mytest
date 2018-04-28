package excl.poi;

import exception.ErrorNumber;
import exception.LogicException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * excl读取工具类
 *
 * @author huang_kangjie
 * @create 2018-04-27 12:43
 */
public class ExcelUtil
{
    private int totalRows = 0;
    
    private int totalCells = 0;
    
    private String errorInfo;
    
    public ExcelUtil()
    {
        
    }
    
    public HSSFWorkbook getExcelBook(ExcelDataBean dataS)
    {
        if (dataS != null)
        {
            HSSFWorkbook book = new HSSFWorkbook();
            
            if (dataS.getPageSdataList() == null)
            {
                HSSFSheet sheet = book.createSheet(dataS.getPageName());
                List<List<String>> rowsDataList = dataS.getRowsDataList();
                for (int i = 0; i < rowsDataList.size(); i++)
                {
                    HSSFRow row = sheet.createRow(i);
                    List<String> cellsDataList = rowsDataList.get(i);
                    for (int j = 0; j < cellsDataList.size(); j++)
                    {
                        HSSFCell cell = row.createCell(j);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(cellsDataList.get(j));
                    }
                }
            }
            
            return book;
        }
        return null;
    }
    
    public int getTotalRows()
    {
        return totalRows;
    }
    
    public int getTotalCells()
    {
        return totalCells;
        
    }
    
    public String getErrorInfo()
    {
        return errorInfo;
    }
    
    public boolean validateExcel(String filePath)
    {
        
        if (filePath == null
                || !(isExcel2003(filePath) || isExcel2007(filePath)))
        {
            errorInfo = "";
            return false;
        }
        
        File file = new File(filePath);
        if (!file.exists())
        {
            errorInfo = "";
            return false;
        }
        
        return true;
        
    }
    
    public List<List<String>> read(String filePath) throws LogicException
    {
        
        List<List<String>> dataLst = new ArrayList<List<String>>();
        
        InputStream is = null;
        
        try
        {
            
            if (!validateExcel(filePath))
            {
                throw new LogicException(ErrorNumber.PARAMETER_ERROR, "");
            }
            
            boolean isExcel2003 = true;
            if (isExcel2007(filePath))
            {
                isExcel2003 = false;
            }
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, isExcel2003);
            is.close();
            
        }
        catch (Exception ex)
        {
            throw new LogicException(ErrorNumber.PARAMETER_ERROR, "");
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    is = null;
                }
            }
            
        }
        return dataLst;
    }
    
    public List<List<String>> read(File file) throws LogicException
    {
        if (file == null)
        {
            return null;
        }
        String filePath = file.getAbsolutePath();
        
        List<List<String>> dataLst = new ArrayList<List<String>>();
        
        InputStream is = null;
        
        try
        {
            if (!validateExcel(filePath))
            {
                throw new LogicException(ErrorNumber.PARAMETER_ERROR, "");
            }
            boolean isExcel2003 = true;
            if (isExcel2007(filePath))
            {
                isExcel2003 = false;
            }
            is = new FileInputStream(file);
            dataLst = read(is, isExcel2003);
            is.close();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new LogicException(ErrorNumber.PARAMETER_ERROR, "");
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    is = null;
                }
            }
            
        }
        return dataLst;
    }
    
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003)
            throws LogicException
    {
        List<List<String>> dataLst = null;
        try
        {
            Workbook wb = null;
            if (isExcel2003)
            {
                wb = new HSSFWorkbook(inputStream);
            }
            else
            {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = read(wb);
        }
        catch (IOException e)
        {
            throw new LogicException(ErrorNumber.PARAMETER_ERROR, "");
        }
        catch (Exception e)
        {
            throw new LogicException(ErrorNumber.PARAMETER_ERROR, "");
        }
        
        return dataLst;
        
    }
    
    private List<List<String>> read(Workbook wb)
    {
        
        List<List<String>> dataLst = new ArrayList<List<String>>();
        
        Sheet sheet = wb.getSheetAt(0);
        
        this.totalRows = sheet.getPhysicalNumberOfRows();
        
        if (this.totalRows >= 1 && sheet.getRow(0) != null)
        {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for (int r = 0; r < this.totalRows; r++)
        {
            Row row = sheet.getRow(r);
            
            if (row == null)
            {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            
            for (int c = 0; c < this.getTotalCells(); c++)
            {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell)
                {
                    
                    switch (cell.getCellType())
                    {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            BigDecimal bd = new BigDecimal(
                                    cell.getNumericCellValue());
                            cellValue = bd.toPlainString();
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            cellValue = "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            cellValue = "";
                            break;
                        default:
                            cellValue = "";
                            break;
                    }
                }
                rowLst.add(cellValue);
            }
            dataLst.add(rowLst);
        }
        return dataLst;
        
    }
    
    public boolean isExcel2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    
    public boolean isExcel2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    
}

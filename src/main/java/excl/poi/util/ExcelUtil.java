package excl.poi.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出工具，用于生成excl
 * @param <T>
 * @author huang_kangjie
 * @create 2018-04-27 12:45
 */
public class ExcelUtil<T>
{
    Class<T> clazz;
    
    public ExcelUtil(Class<T> clazz)
    {
        this.clazz = clazz;
    }
    
    public List<T> importExcel(InputStream input)
    {
        return importExcel(null, input);
    }
    
    public List<T> importExcel(String sheetName, InputStream input)
    {
        List<T> list = new ArrayList<T>();
        try
        {
            Workbook book = Workbook.getWorkbook(input);
            Sheet sheet = null;
            if (sheet == null)
            {
                sheet = book.getSheet(0);// 如果传入的sheet名不存在则默认指向第1个sheet.
            }
            else
            {
                sheet = book.getSheet(sheetName);// 如果指定sheet名,则取指定sheet中的内容.
            }
            int rows = sheet.getRows();// 得到数据的行数
            if (rows > 0)
            {// 有数据时才处理
                Field[] allFields = clazz.getDeclaredFields();// 得到类的所有field.
                Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();// 定义一个map用于存放列的序号和field.
                for (Field field : allFields)
                {
                    // 将有注解的field存放到map中.
                    if (field.isAnnotationPresent(ExcelVOAttribute.class))
                    {
                        ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                        int col = getExcelCol(attr.column());// 获得列号
                        field.setAccessible(true);// 设置类的私有字段属性可访问.
                        fieldsMap.put(col, field);
                    }
                }
                for (int i = 1; i < rows; i++)
                {// 从第2行开始取数据,默认第一行是表头.
                    Cell[] cells = sheet.getRow(i);// 得到一行中的所有单元格对象.
                    T entity = null;
                    for (int j = 0; j < cells.length; j++)
                    {
                        String c = cells[j].getContents();// 单元格中的内容.
                        if (c.equals(""))
                        {
                            continue;
                        }
                        entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                        Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if ((Integer.TYPE == fieldType)
                                || (Integer.class == fieldType))
                        {
                            field.set(entity, Integer.parseInt(c));
                        }
                        else if (String.class == fieldType)
                        {
                            field.set(entity, String.valueOf(c));
                        }
                        else if ((Long.TYPE == fieldType)
                                || (Long.class == fieldType))
                        {
                            field.set(entity, Long.valueOf(c));
                        }
                        else if ((Float.TYPE == fieldType)
                                || (Float.class == fieldType))
                        {
                            field.set(entity, Float.valueOf(c));
                        }
                        else if ((Short.TYPE == fieldType)
                                || (Short.class == fieldType))
                        {
                            field.set(entity, Short.valueOf(c));
                        }
                        else if ((Double.TYPE == fieldType)
                                || (Double.class == fieldType))
                        {
                            field.set(entity, Double.valueOf(c));
                        }
                        else if (Character.TYPE == fieldType)
                        {
                            if ((c != null) && (c.length() > 0))
                            {
                                field.set(entity, Character.valueOf(c.charAt(0)));
                            }
                        }
                    }
                    if (entity != null)
                    {
                        list.add(entity);
                    }
                }
            }
        }
        catch (BiffException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param list
     * @param sheetName
     */
    public HSSFWorkbook exportExcel(List<?> list, String sheetName)
    {
        return exportExcel(list, sheetName, null);
    }
    
    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param list
     * @param sheetName
     * @param errorVos 错误信息写批注
     */
    public HSSFWorkbook exportExcel(List<?> list, String sheetName, List<ErrorVo> errorVos)
    {
        int sheetSize = 65536;
        Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
        List<Field> fields = new ArrayList<Field>();
        // 得到所有field并存放到一个list中.
        for (Field field : allFields)
        {
            if (field.isAnnotationPresent(ExcelVOAttribute.class))
            {
                fields.add(field);
            }
        }
        HSSFWorkbook workbook = new HSSFWorkbook();// 产生工作薄对象
        double sheetNo = Math.ceil(list.size() / sheetSize);// 取出一共有多少个sheet.
        for (int index = 0; index <= sheetNo; index++)
        {

            int startNo = index * sheetSize;
            int endNo = Math.min(startNo + sheetSize, list.size());

            HSSFSheet sheet = workbook.createSheet();// 产生工作表对象
            workbook.setSheetName(index, sheetName + index);// 设置工作表的名称.
            HSSFRow row;
            HSSFCell cell;// 产生单元格
            row = sheet.createRow(0);// 产生一行
            // 写入各个字段的列头名称
            for (int i = 0; i < fields.size(); i++)
            {
                Field field = fields.get(i);
                ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                sheet.setColumnWidth(i, attr.width());//设置宽度
                //设置列样式为文本格式
                CellStyle css = workbook.createCellStyle();
                DataFormat format = workbook.createDataFormat();
                css.setDataFormat(format.getFormat("@"));
                css.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                
                int col = getExcelCol(attr.column());// 获得列号
                
                sheet.setDefaultColumnStyle(col, css);
                
                cell = row.createCell(col);// 创建列
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列中写入内容为String类型
                cell.setCellValue(attr.name());// 写入列名
                // 如果设置了提示信息则鼠标放上去提示.
                if (!attr.prompt().trim().equals(""))
                {
                    setHSSFPrompt(sheet, "", attr.prompt(), 1, endNo, col, col);// 这里默认设了列提示.
                }
                // 如果设置了combo属性则本列只能选择不能输入
                if (attr.combo().length > 0)
                {
                    setHSSFValidation(sheet, attr.combo(), 1, endNo, col, col);// 这里默认设了列只能选择不能输入.
                }
                
            }

            // 写入各条记录,每条记录对应excel表中的一行
            for (int i = startNo; i < endNo; i++)
            {
                row = sheet.createRow(i + 1 - startNo);
                T vo = (T) list.get(i); // 得到导出对象.
                for (int j = 0; j < fields.size(); j++)
                {
                    Field field = fields.get(j);// 获得field.
                    field.setAccessible(true);// 设置实体类私有属性可访问
                    ExcelVOAttribute attr = field.getAnnotation(ExcelVOAttribute.class);
                    try
                    {
                        // 根据ExcelVOAttribute中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                        if (attr.isExport())
                        {
                            cell = row.createCell(getExcelCol(attr.column()));// 创建cell
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellValue(field.get(vo) == null ? "" : String.valueOf(field.get(vo)));// 如果数据存在就填入,不存在填入空格.
                        }
                    }
                    catch (IllegalArgumentException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            if (errorVos != null && errorVos.size() > 0)
            {
                setHSSFRichTextString(sheet, errorVos);
            }
        }
        return workbook;
    }
    
    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
    public static int getExcelCol(String col)
    {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++)
        {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }
    
    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle, String promptContent, int firstRow,
            int endRow, int firstCol, int endCol)
    {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }
    
    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textList 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textList, int firstRow, int endRow,
            int firstCol, int endCol)
    {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textList);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }
    
    /**
     * 设置单元格上错误提示
     *
     * @param sheet    要设置的sheet.
     * @param errorVos 要设置的错误消息.
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFRichTextString(HSSFSheet sheet, List<ErrorVo> errorVos)
    {
        disposeErrorVoList(errorVos);
        //是由于cell styles太多create造成，故一般可以把HSSFCellStyle设置放到循环外面
        //否则会 抛出异常：java.lang.IllegalStateException: The maximum number of cell styles was exceeded. You can define up to 4000 styles in a .xls workbook
        HSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        for (ErrorVo vo : errorVos)
        {
            //获取单元格对象
            HSSFCell cell = sheet.getRow(vo.getRow()).getCell(vo.getCell());
            if (cell != null)
            {
                //前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 10, 6);
                //创建绘图对象
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                HSSFComment comment = patriarch.createCellComment(anchor);
                //输入批注信息
                comment.setString(new HSSFRichTextString(vo.getText()));
                //添加作者,选中B5单元格,看状态栏
                comment.setAuthor("toad");
                //将批注添加到单元格对象中
                cell.setCellComment(comment);
                cell.setCellStyle(style);
            }
        }
        return sheet;
    }
    
    /**
     * 处理List里面相同的行，列错误列表
     *
     * @param errorVos
     * @return
     */
    public static List<ErrorVo> disposeErrorVoList(List<ErrorVo> errorVos)
    {
        for (int i = 0; i < errorVos.size() - 1; i++)
        {
            for (int j = errorVos.size() - 1; j > i; j--)
            {
                if (errorVos.get(j).getRow() == (errorVos.get(i)).getRow()
                        && errorVos.get(j).getCell() == (errorVos.get(i)).getCell())
                {
                    errorVos.get(i).setText(errorVos.get(i).getText() + "，" + errorVos.get(j).getText());
                    errorVos.remove(j);
                }
            }
        }
        return errorVos;
    }
}

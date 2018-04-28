package excl.poi;

import java.util.List;

public class ExcelDataBean
{
    
    private String pageName;
    
    private List<List<String>> rowsDataList;
    
    private List<ExcelDataBean> pageSdataList;
    
    /**
     * @return 返回 pageName
     */
    public String getPageName()
    {
        return pageName;
    }
    
    /**
     * @param 对pageName进行赋值
     */
    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }
    
    /**
     * @return 返回 rowsDataList
     */
    public List<List<String>> getRowsDataList()
    {
        return rowsDataList;
    }
    
    /**
     * @param 对rowsDataList进行赋值
     */
    public void setRowsDataList(List<List<String>> rowsDataList)
    {
        this.rowsDataList = rowsDataList;
    }
    
    /**
     * @return 返回 pageSdataList
     */
    public List<ExcelDataBean> getPageSdataList()
    {
        return pageSdataList;
    }
    
    /**
     * @param 对pageSdataList进行赋值
     */
    public void setPageSdataList(List<ExcelDataBean> pageSdataList)
    {
        this.pageSdataList = pageSdataList;
    }
}

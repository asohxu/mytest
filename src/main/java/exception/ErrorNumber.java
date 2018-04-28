package exception;

public interface ErrorNumber
{
    /**
     * 数据库内部错误
     */
    public String DATABASE_ERROR = "1";
    
    /**
     * 空指针错误
     */
    public String NULL_ERROR = "2";
    
    /**
     * 参数错误
     */
    public String PARAMETER_ERROR = "3";
    
    /**
     * 格式错误
     */
    public String FORMAT_ERROR = "4";
    
    
    /**
     * 调用接口异常
     */    
    public String ERROR_INTERFACE = "10";
    
    /**
     * 成功
     */
    public String SUCCESS = "0";
    
    /**
     * 数据转换错误
     */    
    public String ERROR_JSON_TO_OBJECT = "11";

    public String ERROR_OBJECET_TO_JSON = "12";
    
    
}

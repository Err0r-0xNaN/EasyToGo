package cc.nanoic.servereasytogo.exception;

/**
 * @Description:
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: ServiceException
 **/
public class ServiceException extends RuntimeException{

    private String code;

    public ServiceException(String msg){
        super(msg);
        this.code = "500";
    }
    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
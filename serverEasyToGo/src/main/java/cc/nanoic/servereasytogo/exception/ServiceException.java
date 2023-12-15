package cc.nanoic.servereasytogo.exception;

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

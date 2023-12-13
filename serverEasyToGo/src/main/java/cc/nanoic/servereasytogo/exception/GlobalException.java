package cc.nanoic.servereasytogo.exception;

import cc.nanoic.servereasytogo.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceExcepiton(ServiceException e){
        return Result.error(e.getCode(), e.getMessage());
    }

}

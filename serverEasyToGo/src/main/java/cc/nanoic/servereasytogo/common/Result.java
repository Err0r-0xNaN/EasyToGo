package cc.nanoic.servereasytogo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:统一返回信息
 * @Author: Nanoic
 * @Date: 2023-12-25
 * @FileName: Result
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    public static final String CODE_SUCCESS = "200";/* 200 - 成功 */
    public static final String CODE_AUTH_ERROR = "401";/* 401 - 认证错误 */
    public static final String CODE_SYS_ERROR = "500";/* 500 - 系统错误 */
    public static final String CODE_SQL_CONNECT_ERROR = "501";/* 501 - SQL数据库连接错误 */
    public static final String CODE_SQL_ERROR = "502";/* 502 - SQL数据库操作错误 */
    public static final String CODE_TYPE_ERROR = "503";

    private String statusCode;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(CODE_SUCCESS, "请求成功", null);
    }
    public static Result success(String msg){
        return new Result(CODE_SUCCESS, msg, null);
    }
    public static Result success(Object data){
        return new Result(CODE_SUCCESS, "请求成功", data);
    }
    public static Result error(String msg){
        return new Result(CODE_SYS_ERROR, msg, null);
    }
    public static Result error(String code,String msg){
        return new Result(code, msg, null);
    }
    public static Result error(){
        return new Result(CODE_SYS_ERROR, "系统错误", null);
    }
}


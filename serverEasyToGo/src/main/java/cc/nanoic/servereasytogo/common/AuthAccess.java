package cc.nanoic.servereasytogo.common;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Nanoic
 * @Date: 2023-12-28
 * @FileName: AuthAccess
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}

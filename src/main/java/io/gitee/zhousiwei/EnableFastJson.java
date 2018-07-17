package io.gitee.zhousiwei;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启FastJson
 *
 * @author 試毅-思伟
 * @Email 2434387555@qq.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({FastJsonAutoConfiguration.class})
public @interface EnableFastJson {

}

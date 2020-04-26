package io.gitee.zhousiwei;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启FastJson
 *
 * @author 試毅-思伟
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({FastJsonAutoConfigurationV1_X.class})
@Deprecated
public @interface EnableFastJson {

}

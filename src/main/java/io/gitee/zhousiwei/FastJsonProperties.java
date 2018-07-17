package io.gitee.zhousiwei;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;

/**
 * FastJsonp配置
 *
 * @author 試毅-思伟
 * @Email 2434387555@qq.com
 */
@Data
@ConfigurationProperties(FastJsonProperties.FASTJSON_PREFIX)
public class FastJsonProperties {

    /**
     * FastJson配置文件前缀
     */
    public static final String FASTJSON_PREFIX = "fastjson";

    /**
     * 默认日期格式
     */
    public static final String FASTJSON_DEFAULT_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 媒体类型(默认为:application/json;charset=UTF-8)
     */
    private String[] mediaType = new String[]{MediaType.APPLICATION_JSON_UTF8_VALUE};

    /**
     * 日期格式(默认为:yyyy-MM-dd HH:mm:ss)
     */
    private String dateFormat = FASTJSON_DEFAULT_DATEFORMAT;

}



package io.gitee.zhousiwei;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;

import java.io.Serializable;

/**
 * FastJson配置
 *
 * @author Created by 思伟 on 2019/4/26
 */
@Data
@ConfigurationProperties(prefix = FastJsonProperties.FASTJSON_PREFIX)
public class FastJsonProperties implements Serializable {

    /**
     * FastJson配置文件前缀
     */
    public static final String FASTJSON_PREFIX = "fastjson";

    /**
     * 是否开启 LOGO
     */
    private boolean banner = true;

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



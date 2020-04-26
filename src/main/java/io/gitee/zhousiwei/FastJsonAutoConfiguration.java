package io.gitee.zhousiwei;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * FastJson自动化配置
 *
 * @author Created by 思伟 on 2019/4/26
 */
@Configuration
@ConditionalOnProperty(name = FastJsonProperties.FASTJSON_PREFIX + ".enabled", matchIfMissing = true)
public class FastJsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FastJsonProperties fastJsonProperties() {
        return new FastJsonProperties();
    }

    /**
     * Fixme HttpMessageConverters由于spring-boot-starter-parent版本更新问题 2.0.0.RELEASE之后使用
     * org.springframework.boot.autoconfigure.http.HttpMessageConverters
     *
     * @param fastJsonProperties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters fastJsonHttpMessageConverters(FastJsonProperties fastJsonProperties) {
        if (fastJsonProperties.isBanner()) {
            // http://patorjk.com/software/taag/#p=display&f=Ogre&t=fastjson
            System.out.println("  __           _    _                 ");
            System.out.println(" / _| __ _ ___| |_ (_)___  ___  _ __  ");
            System.out.println("| |_ / _` / __| __|| / __|/ _ \\| '_ \\ ");
            System.out.println("|  _| (_| \\__ \\ |_ | \\__ \\ (_) | | | |");
            System.out.println("|_|  \\__,_|___/\\__|/ |___/\\___/|_| |_|");
            System.out.println("                 |__/               " + FastJsonVersion.getVersion());
            // System.err.println("starter for fastJson-----fastJson init success.");
        }
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        // 处理中文乱码问题
        for (String mediaType : fastJsonProperties.getMediaType()) {
            supportedMediaTypes.add(MediaType.parseMediaType(mediaType));
        }
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat(fastJsonProperties.getDateFormat());
        // SerializerFeature.WriteEnumUsingToString 表示对enum使用toString进行输出，
        // enum中对toString进行了重写，直接返回key
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteEnumUsingToString);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

}

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
 * @author 試毅-思伟
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
        System.err.println("starter for fastJson-----fastJson init success.");
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        // 处理中文乱码问题
        for (String mediaType : fastJsonProperties.getMediaType()) {
            supportedMediaTypes.add(MediaType.parseMediaType(mediaType));
        }
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat(fastJsonProperties.getDateFormat());
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

}

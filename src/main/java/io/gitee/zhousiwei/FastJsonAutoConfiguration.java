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
 * FastJson配置
 *
 * @author 試毅-思伟
 * @Email 2434387555@qq.com
 */
@Configuration
@ConditionalOnProperty(name = "fastjson.enabled", matchIfMissing = true)
public class FastJsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FastJsonProperties fastJsonProperties() {
        return new FastJsonProperties();
    }

    @Bean
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

package moe.tristan.harudex;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import moe.tristan.harudex.util.AuthClientHttpRequestInterceptor;
import moe.tristan.harudex.util.HaruDexClientRequestsLogger;
import moe.tristan.harudex.util.HaruDexErrorHandler;

@ComponentScan
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HaruDexProperties.class)
public class HaruDexAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HaruDexContext.class)
    public HaruDexContext haruDexContext(HaruDexProperties haruDexProperties) {
        return new HaruDexContext(haruDexProperties);
    }

    @Configuration(proxyBeanMethods = false)
    @Import(JacksonAutoConfiguration.class)
    @ConditionalOnMissingBean(value = ObjectMapper.class, annotation = HaruDexJacksonAutoConfiguration.HaruDexObjectMapper.class)
    public static class HaruDexJacksonAutoConfiguration {

        @Target({METHOD, TYPE})
        @Retention(RUNTIME)
        @Inherited
        public @interface HaruDexObjectMapper {
        }

    }

    @Configuration(proxyBeanMethods = false)
    @Import(RestTemplateAutoConfiguration.class)
    @ConditionalOnMissingBean(value = RestTemplate.class, annotation = HaruDexRestTemplateConfiguration.HaruDexRestTemplate.class)
    public static class HaruDexRestTemplateConfiguration {

        @Bean
        public RestTemplate haruDexRestTemplate(RestTemplateBuilder restTemplateBuilder, HaruDexContext context) {
            return restTemplateBuilder
                .additionalInterceptors(
                    new HaruDexClientRequestsLogger(),
                    new AuthClientHttpRequestInterceptor(context)
                )
                .errorHandler(new HaruDexErrorHandler())
                .build();
        }

        @Target({METHOD, TYPE})
        @Retention(RUNTIME)
        @Inherited
        public @interface HaruDexRestTemplate {
        }

    }

}

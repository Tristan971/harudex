package moe.tristan.harudex;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import moe.tristan.harudex.auth.AuthClientHttpRequestInterceptor;
import moe.tristan.harudex.common.HaruDexClientRequestsLogger;
import moe.tristan.harudex.common.HaruDexErrorHandler;
import moe.tristan.harudex.common.HaruDexRestTemplate;

@ComponentScan
@EnableConfigurationProperties(HaruDexProperties.class)
public class HaruDexAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HaruDexContext.class)
    public HaruDexContext haruDexContext(HaruDexProperties haruDexProperties) {
        return new HaruDexContext(haruDexProperties);
    }

    @Bean
    @ConditionalOnMissingBean(value = RestTemplate.class, annotation = HaruDexRestTemplate.class)
    public RestTemplate haruDexRestTemplate(RestTemplateBuilder restTemplateBuilder, HaruDexContext context) {
        return restTemplateBuilder
            .additionalInterceptors(
                new HaruDexClientRequestsLogger(),
                new AuthClientHttpRequestInterceptor(context)
            )
            .errorHandler(new HaruDexErrorHandler())
            .build();
    }

}

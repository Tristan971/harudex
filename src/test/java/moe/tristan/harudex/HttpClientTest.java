package moe.tristan.harudex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited

@SpringBootTest
@Import(value = {
    JacksonAutoConfiguration.class,
    RestTemplateAutoConfiguration.class,
    ClientRequests.class
})
@EnableConfigurationProperties(HaruDexProperties.class)
@AutoConfigureMockRestServiceServer
public @interface HttpClientTest {

    @AliasFor(annotation = SpringBootTest.class, attribute = "classes")
    Class<?>[] value() default {};

}

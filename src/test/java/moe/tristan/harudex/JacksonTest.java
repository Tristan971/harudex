package moe.tristan.harudex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited

@SpringBootTest(classes = JacksonAutoConfiguration.class)
@Import(value = {})
public @interface JacksonTest {

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default {};

}

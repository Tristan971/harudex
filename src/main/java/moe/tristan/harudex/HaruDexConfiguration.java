package moe.tristan.harudex;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HaruDexProperties.class)
public class HaruDexConfiguration {

}

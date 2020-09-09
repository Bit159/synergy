package conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "user", "controller", "aop", "crawl", "notifier" })
@Configuration
public class Config {

}

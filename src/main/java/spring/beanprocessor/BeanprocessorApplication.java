package spring.beanprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BeanprocessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanprocessorApplication.class);
    }

}

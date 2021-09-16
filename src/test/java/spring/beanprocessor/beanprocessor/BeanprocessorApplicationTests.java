package spring.beanprocessor.beanprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import spring.beanprocessor.Greeter;

@SpringBootTest
class BeanprocessorApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Greeter greeter = applicationContext.getBean(Greeter.class);
        System.out.println("sout greeter.getClass(): " + greeter.getClass());
        System.out.println("sout greeter.getRandomNumber(): " + greeter.getRandomNumber());
        System.out.println("sout greeter.getRandomNumberWithIncrease(): " + greeter.getRandomNumberWithIncrease());
        System.out.println("sout greeter.get5(): " + greeter.get5());
        System.out.println("sout greeter.get5withIncrease(): " + greeter.get5withIncrease());
    }
}

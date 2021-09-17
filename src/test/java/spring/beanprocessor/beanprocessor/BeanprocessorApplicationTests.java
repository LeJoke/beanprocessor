package spring.beanprocessor.beanprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import spring.beanprocessor.NumberWrapper;

@SpringBootTest
class BeanprocessorApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        NumberWrapper numberWrapper = applicationContext.getBean(NumberWrapper.class);
        System.out.println("sout greeter.getClass(): " + numberWrapper.getClass());
        System.out.println("sout greeter.getRandomNumber(): " + numberWrapper.getRandomNumber());
        System.out.println("sout greeter.getRandomNumberWithIncrease(): " + numberWrapper.getRandomNumberWithIncrease());
        System.out.println("sout greeter.get5(): " + numberWrapper.get5());
        System.out.println("sout greeter.get5withIncrease(): " + numberWrapper.get5withIncrease());
    }
}

package spring.beanprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GreeterImpl implements Greeter {

    private final Logger log = LoggerFactory.getLogger(GreeterImpl.class);

    @RandomNumber(min = 5, max = 20)
    private int randomNumber;

    @PostConstruct
    private void init() {
        log.info("{}, randomNumber = {}", "2 init method", randomNumber);
    }

    public GreeterImpl() {
        log.info("{}, randomNumber = {}", "1 default", randomNumber);
    }

    @Override
    public int getRandomNumber() {
        return randomNumber;
    }

    @Override
    @GetIncreasedNumber(increaseValue = 5)
    public int getRandomNumberWithIncrease() {
        return randomNumber;
    }

    @Override
    public int get5() {
        return 5;
    }

    @Override
    @GetIncreasedNumber(increaseValue = 5)
    public int get5withIncrease() {
        return 5;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    @EventListener
    private void contextRefresh(ContextRefreshedEvent contextRefreshedEvent) {
        this.randomNumber += 5;
        log.info("{}, randomNumber = {}", "3 context event refresh", randomNumber);
    }
}



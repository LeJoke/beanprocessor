package spring.beanprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NumberWrapperImpl implements NumberWrapper {

    private final Logger log = LoggerFactory.getLogger(NumberWrapperImpl.class);

    @RandomNumber(min = 5, max = 20)
    private int randomNumber;

    public NumberWrapperImpl() {
        log.info("{}, randomNumber = {}", "1 default", randomNumber);
    }

    @PostConstruct
    private void init() {
        log.info("{}, randomNumber = {}", "2 init method", randomNumber);
    }

    @Override
    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
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

    @Override
    @IncreaseRandomNumberWith5
    public void contextRefresh() {
        this.randomNumber += 5;
        log.info("{}, randomNumber = {}", "3 context event refresh", randomNumber);
    }
}



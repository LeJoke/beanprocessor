package spring.beanprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class RandomNumberBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NumberWrapperImpl) {
            Field[] fields = NumberWrapperImpl.class.getDeclaredFields();
            for (Field field : fields) {
                RandomNumber annotation = field.getAnnotation(RandomNumber.class);
                if (annotation != null) {
                    ReflectionUtils.makeAccessible(field);
                    try {
                        field.setInt(bean, annotation.min() + new Random().nextInt(annotation.max() - annotation.min()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

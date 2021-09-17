package spring.beanprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class ApplicationRefreshEventProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = beanFactory.getBean(beanDefinitionName);
            try {
                Class<?> baseClass = Class.forName(beanFactory.getBeanDefinition(beanDefinitionName).getBeanClassName());
                Method[] baseClassMethods = baseClass.getDeclaredMethods();
                for (Method baseClassMethod : baseClassMethods) {
                    if (baseClassMethod.isAnnotationPresent(IncreaseRandomNumberWith5.class)) {
                        ReflectionUtils.invokeMethod(bean.getClass().getMethod(baseClassMethod.getName(), baseClassMethod.getParameterTypes()), bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package spring.beanprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class IncreaseRandomNumberValueBeanPostProcessor implements BeanPostProcessor {

    private final Logger log = LoggerFactory.getLogger(IncreaseRandomNumberValueBeanPostProcessor.class);

    private final Map<String, Object> baseObjectsMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(GetIncreasedNumber.class)) {
                baseObjectsMap.put(beanName, bean);
                break;
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object baseObject = baseObjectsMap.get(beanName);
        if (baseObject != null) {
            Class<?> baseClass = baseObject.getClass();
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    baseClass.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            ReflectionUtils.makeAccessible(method);
                            Method baseClassDeclaredMethod = baseClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
                            Method currentDeclaredMethod = bean.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
                            if (baseClassDeclaredMethod.isAnnotationPresent(GetIncreasedNumber.class)) {
                                Object value = currentDeclaredMethod.invoke(bean, args);
                                return (int) value + baseClassDeclaredMethod.getAnnotation(GetIncreasedNumber.class).increaseValue();
                            }
                            return currentDeclaredMethod.invoke(bean, args);
                        }
                    });
        }
        return bean;
    }
}

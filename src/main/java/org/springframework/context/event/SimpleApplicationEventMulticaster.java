package org.springframework.context.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 20:41
 * @Version: 1.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }
    
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : super.applicationListeners) {
            if (supportsEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }
    
    /**
     * 监听器是否对该事件感兴趣
     *
     * @Param applicationListener
     * @Param event
     * @Return boolean
     * @Author Cai 🥬
     * @Date 2022/7/5 21:15
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        //获得实现该监听器接口的 Type 数组的第一个元素，包含泛型信息
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        //获得其中泛型变量的确切值，即该泛型的实际 Class
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        //得到类名字
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}

package org.springframework.context.support;

import org.springframework.beans.BeansException;

/**
 * xml 文件的应用上下文
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-10 15:08
 * @Version: 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    
    private String[] configLocations;
    
    /**
     * 从 xml 文件加载 BeanDefinition，并且自动刷新上下文
     *
     * @Param: configLocations
     * @ReturnType:
     * @Author: Cai 🥬
     * @Date: 2022/6/10 15:12
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }
    
    /**
     * 如果是一个配置文件，转换为数组形式
     *
     * @Param: configLocation
     * @ReturnType:
     * @Author: Cai 🥬
     * @Date: 2022/6/10 15:39
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }
    
    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}

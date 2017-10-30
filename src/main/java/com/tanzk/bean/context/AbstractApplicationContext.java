package com.tanzk.bean.context;

import com.tanzk.bean.BeanPostProcessor;
import com.tanzk.bean.beanFactory.AbstractBeanFactory;

import java.util.List;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/28
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
        this.beanFactory = abstractBeanFactory;
    }


    public void refresh() throws Exception{
        //加载资源
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception ;

    public void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception{

        //将BeanPostProcessor的类放在beanPostProcessors容器中
        List<BeanPostProcessor> beanPostProcessors=beanFactory.getBeansForType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor:beanPostProcessors){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

    }
}

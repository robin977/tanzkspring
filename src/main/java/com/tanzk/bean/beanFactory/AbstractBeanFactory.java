package com.tanzk.bean.beanFactory;

import com.tanzk.bean.BeanDefinition;
import com.tanzk.bean.BeanPostProcessor;
import com.tanzk.bean.util.ClassUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    //bean名称与bean定义类
    private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String,BeanDefinition>();

    private List<String> beanDefinitionNames=new ArrayList<String>();

    private ClassLoader beanClassLoader = ClassUtil.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition= beanDefinitionMap.get(name);
        if(beanDefinition==null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean=beanDefinition.getBean();
        //如果bean为空的话
        if(bean==null) {
            bean = doCreateBean(beanDefinition);
        }
            //添加属性后，再设置回去
            bean = initializeBean(bean, name);
            applyPropertyValues(name,bean,beanDefinition);
            beanDefinition.setBean(bean);

        return bean;
    }

    protected Object initializeBean(Object bean, String name) throws Exception {

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        // TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    //分析属性
    public void applyPropertyValues(final String beanName,Object bean,BeanDefinition beanDefinition) throws Exception {

    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }


    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }


    //将新加入的beanDefinition注册到beanDefinitionMap里
    public  void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        this.beanDefinitionMap.put(name,beanDefinition);
        beanDefinitionNames.add(name);
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
         Object bean= createBeanInstance(beanDefinition);
         beanDefinition.setBean(bean);
         return bean;
    }



    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception{
        Object obj= beanDefinition.getBeanClass().newInstance();
        return  obj;
    }

    public List getBeansForType(Class type) throws Exception {
        List list=new ArrayList<Object>();
        for(String  beanDefinitionName:beanDefinitionNames){
            //判断type类是否是beanclass的接口或父类
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
                list.add(getBean(beanDefinitionName));
            }
        }
        return list;
    }


}

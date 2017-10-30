package com.tanzk.bean.aware;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public interface BeanClassLoaderAware {
   public void setBeanClassLoader(ClassLoader classLoader);
}

package com.tanzk.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class PropertyValues {
    private List<PropertyValue> propertyValueList=new ArrayList<PropertyValue>();

    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}

package com.tanzk.entity;

import com.tanzk.entity.IUser;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class User implements IUser {

    private String name;
    private String    age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public void desc(){
        System.out.println(this.getName()+",age:"+this.getAge());
    }
}

package com.tanzk.bean.io;

import java.net.URL;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public class ResourceLoader {

    public UrlResource getResource(String location){
        URL resource=this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}

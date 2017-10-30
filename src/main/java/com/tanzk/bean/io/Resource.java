package com.tanzk.bean.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>功能描述：</p>
 *
 * @author robin
 * @date 2017/10/27
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}

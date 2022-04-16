package com.cz.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 定义Resource接口，提供获取InputStream流的方法，接下来再分布实现三种不同的流文件操作：classPath、FileSystem、URL
 *
 *
 * @author ChangZhen
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}

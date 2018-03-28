package com.peierlong.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器和instanceof关键字延时
 *
 * @author elong
 * @version V1.0
 * @date 2018/3/28
 */
public class ClassloaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] bytes = new byte[0];
                    try {
                        bytes = new byte[is.available()];
                        is.read(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (ClassNotFoundException e) {
                    throw new ClassNotFoundException(name);
                }

            }
        };


        Object obj = myClassLoader.loadClass("com.peierlong.classloader.ClassloaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.peierlong.classloader.ClassloaderTest);
    }

    /*
    output:

    class com.peierlong.classloader.ClassloaderTest
    false

    */

}

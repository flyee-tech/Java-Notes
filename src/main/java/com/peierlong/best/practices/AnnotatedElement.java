package com.peierlong.best.practices;

import java.lang.annotation.Annotation;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/3 下午2:18
 * 描述 : 使用asSubclass方法在编译时读取类型未知的注解
 */
public class AnnotatedElement {

    static Annotation getAnnotation(java.lang.reflect.AnnotatedElement element, String annotationTypeName) {
        Class<?> annotationType = null;
        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert annotationType != null;
        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }


}

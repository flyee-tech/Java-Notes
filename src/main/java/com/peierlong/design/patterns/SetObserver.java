package com.peierlong.design.patterns;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/8 上午11:22
 * 描述 :
 */
public interface SetObserver<E> {

    void added(ObservableSet<E> set, E element);

}

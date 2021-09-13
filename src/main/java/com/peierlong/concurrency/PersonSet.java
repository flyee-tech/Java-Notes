package com.peierlong.concurrency;

import java.util.HashSet;
import java.util.Set;

/**
 * 包名: com.elong.concurrency
 * 创建人 : Elong
 * 时间: 2016/12/24 下午1:30
 * 描述 : 通过实例封闭和加锁机制使一个类成为线程安全的(类的状态变量并不是线程安全的)
 */
public class PersonSet {
    private final Set<Person> personSet = new HashSet<>();  //HashSet不是线程安全的

    public synchronized void addPerson(Person person) {
        personSet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return personSet.contains(person);
    }

    class Person{}
}


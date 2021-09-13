package com.peierlong.design.patterns;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/12/8 上午11:10
 * 描述 : 观察者模式
 */
public class ObservableSet<E> extends ForwardingSet<E> {

    private final List<SetObserver<E>> observers = new ArrayList<>();

    public ObservableSet(Set<E> s) {
        super(s);
    }

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
        }
        return added;
    }

    private void notifyElementAdded(E element) {
        List<SetObserver<E>> snapshot;
        synchronized (observers) {
            snapshot = new ArrayList<>(observers);
        }
        for (SetObserver<E> observer : snapshot) {
            observer.added(this, element);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c) {
            result |= add(element); //calls notifyElementAdded
        }
        return result;
    }

    public static void main(String[] args) {
        ObservableSet<Integer> observableSet = new ObservableSet<>(new HashSet<Integer>());
        observableSet.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(final ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
//                    set.removeObserver(this);   //notifyElementAdded正在遍历, 报异常java.util.ConcurrentModificationException
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        executorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                set.removeObserver(observer);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executorService.shutdown();
                    }
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            observableSet.add(i);
        }
    }
}









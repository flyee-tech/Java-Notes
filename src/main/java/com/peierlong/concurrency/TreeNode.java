package com.peierlong.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名: com.elong.concurrency.ifeve
 * 创建人 : Elong
 * 时间: 16/9/20 上午11:49
 * 描述 : 死锁的演示
 */
public class TreeNode {
    TreeNode parent   = null;
    List children = new ArrayList();

    public synchronized void addChild(TreeNode child){
        System.out.println(Thread.currentThread().getName() + ": addChild()");
        if(!this.children.contains(child)) {
            this.children.add(child);
            child.setParentOnly(this);
        }
    }

    public synchronized void addChildOnly(TreeNode child){
        System.out.println(Thread.currentThread().getName() + ": addChildOnly()");
        if(!this.children.contains(child)){
            this.children.add(child);
        }
    }

    public synchronized void setParent(TreeNode parent){
        System.out.println(Thread.currentThread().getName() + ": setParent()");
        this.parent = parent;
        parent.addChildOnly(this);
    }

    public synchronized void setParentOnly(TreeNode parent){
        System.out.println(Thread.currentThread().getName() + ": setParentOnly()");
        this.parent = parent;
    }


    public static void main(String[] args) {
        final TreeNode parent = new TreeNode();
        final TreeNode child = new TreeNode();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                parent.addChild(child);
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                child.setParent(parent);
            }
        };

        t1.start();
        t2.start();

    }
}


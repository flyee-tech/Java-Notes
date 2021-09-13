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
    private TreeNode parent = null;
    private List children = new ArrayList();

    private synchronized void addChild(TreeNode child) {
        System.out.println(Thread.currentThread().getName() + ": addChild()");
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParentOnly(this);
        }
    }

    private synchronized void addChildOnly(TreeNode child) {
        System.out.println(Thread.currentThread().getName() + ": addChildOnly()");
        if (!this.children.contains(child)) {
            this.children.add(child);
        }
    }

    private synchronized void setParent(TreeNode parent) {
        System.out.println(Thread.currentThread().getName() + ": setParent()");
        this.parent = parent;
        parent.addChildOnly(this);
    }

    private synchronized void setParentOnly(TreeNode parent) {
        System.out.println(Thread.currentThread().getName() + ": setParentOnly()");
        this.parent = parent;
    }


    public static void main(String[] args) {
        final TreeNode parent = new TreeNode();
        final TreeNode child = new TreeNode();

        Thread t1 = new Thread(() -> parent.addChild(child), "thread-1");
        Thread t2 = new Thread(() -> child.setParent(parent), "thread-2");

        t1.start();
        t2.start();

    }
}


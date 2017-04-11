package com.peierlong.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名: com.peierlong.test
 * 创建人 : Elong
 * 时间: 11/04/2017 2:38 PM
 * 描述 : ArrayList实现
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println(getLastNumber(5, 3));
        System.out.println(getLastNumber(9, 3));
        System.out.println(getLastNumber(100, 11));
    }

    public static int getLastNumber(int m, int n) {
        //初始化集合元素
        List<Element> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            list.add(new Element(true, i));
        }
        //循环删除元素, 直到未被删除元素剩一个
        int notRemoveCount = list.size();
        int countNum = 0, index = 0;
        for (; notRemoveCount > 1; ) {
            if (list.get(index).isRemove()) {
                countNum++;
                if (countNum == n) {
                    countNum = 0;
                    list.get(index).setRemove();
                    notRemoveCount--;
                }
            }
            index++;
            if (index == list.size()) {
                index = 0;
            }
        }
        //返回结果
        int result = 0;
        for (Element e : list) {
            if (e.isRemove()) {
                result = e.getId();
            }
        }
        return result;
    }

    static class Element {
        private boolean isRemove;
        private int id;

        Element(boolean isRemove, int id) {
            this.isRemove = isRemove;
            this.id = id;
        }

        boolean isRemove() {
            return isRemove;
        }

        void setRemove() {
            isRemove = false;
        }

        int getId() {
            return id;
        }
    }

}
